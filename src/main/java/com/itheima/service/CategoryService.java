package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    //添加文章
    void add(Category category);

    //获取当前用户名下创建的文章列表
    List<Category> list();


    //获取详情
    Category findById(Integer id);

    //修改分类
    void update(Category category);
}
