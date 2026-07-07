package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController

@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username,String password)
    {

        if(username!=null&&username.length()>=5&&username.length()<=16&&
        password!=null&&password.length()>=5&&password.length()<=16)
        {
            //查询用户是否存在
            User u=userService.findByUserName(username);

            if(u==null)
            {
                //说明没有相应的用户
                //注册

                userService.register(username,password);
                return Result.success();
            }
            else{

                //存在
                return Result.error("用户名已存在");
            }

        }
        else {
            return Result.error("参数不合法");
        }



    }


    @PostMapping("/login")
    public Result<String> login(String username,String password)
    {

        // 加这句！！！
        System.out.println("前端传过来的用户名：" + username);
        //根据用户名查询用户，判断是否存在

        User byUserName = userService.findByUserName(username);
        if(byUserName==null)
        {
            return Result.error("用户名错误");
        }
        //判断密码是否正确
        if(Md5Util.getMD5String(password).equals(byUserName.getPassword()))
        {
            //登录成功
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",byUserName.getId());
            claims.put("username",byUserName.getUsername());

            String token = JwtUtil.genToken(claims);
            return Result.success(token);

        }
        return Result.error("密码错误");

    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/)
    {
        //根据用户名查询用户
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String)map.get("username");
        Map<String,Object> map  = ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User byUserName = userService.findByUserName(username);
        return Result.success(byUserName);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user)
    {
        userService.update(user);
        return  Result.success();
    }
    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl)
    {
        userService.updateAvatar(avatarUrl);
        return Result.success();

    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params)
    {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        //参数校验
        Map<String,Object>  map= ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User byUserName = userService.findByUserName(username);
        if(!byUserName.getPassword().equals(Md5Util.getMD5String(oldPwd)))
        {
            return Result.error("原密码错误");
        }

        if(!rePwd.equals(newPwd))
        {
            return Result.error("密码不相同");
        }


        userService.updatePwd(newPwd);
        return Result.success();
    }

}
