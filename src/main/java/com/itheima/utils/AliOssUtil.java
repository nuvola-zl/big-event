package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 阿里云 OSS 工具类
 * 风格：严格按照官方 SDK 原生写法，类似你截图中的 Demo
 */
public class AliOssUtil {


    @Value("${aliyun.oss.endpoint}")
    private static String ENDPOINT;

    @Value("${aliyun.oss.access-key-id}")
    private static String ACCESS_KEY_ID;

    @Value("${aliyun.oss.access-key-secret}")
    private static String ACCESS_KEY_SECRET;

    @Value("${aliyun.oss.bucket-name}")
    private static String BUCKET_NAME;
    /**
     * 上传文件到 OSS
     *
     * @param objectName  上传后的路径+文件名（例如：big-event/2026/03/24/abc.png）
     * @param inputStream 文件流
     * @return 上传结果
     * @throws Exception 异常
     */
    public static String uploadFile(String objectName, InputStream inputStream) throws Exception {
        // 2. 创建 OSS 客户端实例（和你截图一模一样）
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        String url = "";
        try {
            // 3. 创建上传请求对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, inputStream);

            // 4. 执行上传
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            url="https://"+BUCKET_NAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;

            // 打印结果（可以查看 ETag 等信息）
            System.out.println("上传成功, ETag: " + result.getETag());
        } catch (Exception e) {
            System.out.println("上传失败: " + e.getMessage());
            throw e; // 抛出异常让上层处理
        } finally {
            // 5. 关闭客户端
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return url;
    }
}


