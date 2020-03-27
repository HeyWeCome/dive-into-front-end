package com.kang.controller;

import com.alibaba.fastjson.JSONObject;
import com.kang.pojo.User;
import com.kang.pojo.UserDiary;
import com.kang.service.user.UserService;
import com.kang.service.user.UserServiceImpl;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 用户的控制类
 * @author: HeyWeCome
 * @createDate: 2020/3/25 21:45
 * @version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService =  new UserServiceImpl();

    // 发布用户的个人日志
    @RequestMapping(value = "/postDairy",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String postDairy(String userId,String content){
        // UUID生成ID,同时去掉"-"
        String id = UUID.randomUUID().toString().replace("-","");
        //设置日期格式 并转化为字符串
        SimpleDateFormat datetime = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String createTime = datetime.format((new Date()));

        UserDiary userDiary = new UserDiary(id,userId,content,createTime);

        // 执行新增日志的操作
        userService.addDairy(userDiary);

        return JSONObject.toJSONString(userDiary.getId());
    }

    // 发布用户的个人日志
    @RequestMapping(value = "/loadDairy",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String loadDairy(String userId){
        List<UserDiary> userDiaries = userService.queryAlldiary(userId);
        return JSONObject.toJSONString(userDiaries);
    }

    // 上传头像

    @RequestMapping(value ="uploadHead",method = RequestMethod.POST)
    public String addUser(HttpServletRequest request , User user, MultipartFile pictureFile) throws Exception{
        System.out.println("上传进来了");
        //使用UUID给图片重命名，并去掉四个“-”
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        //获取文件的扩展名
        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        System.out.println("文件拓展名："+ext);
        //设置图片上传路径
        String url = "E:\\Workspace\\IDEAWorkspace\\wecode\\web\\upload";
        System.out.println("保存路径"+url);

        //以绝对路径保存重名命后的图片
        pictureFile.transferTo(new File(url+"/"+name + "." + ext));
        //把图片存储路径保存到数据库

//        user.setImageURL("upload/"+name + "." + ext);
//
//        userService.addUser(user);
//        //重定向到查询所有用户的Controller，测试图片回显
        return "redirect:/index.html";
    }


}