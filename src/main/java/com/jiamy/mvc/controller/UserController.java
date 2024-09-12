package com.jiamy.mvc.controller;

import com.jiamy.mvc.GetMapping;
import com.jiamy.mvc.ModelAndView;
import com.jiamy.mvc.dto.User;

/**
 * @description:
 * @author: jiamy
 * @create: 2024/9/5 14:16
 **/
public class UserController {

    @GetMapping("/user/getByName")
    public ModelAndView getByName(String userName){
        User user = new User();
        user.setName(userName);
        user.setAge(30);

        return new ModelAndView().setView("/user").addModel("user", user);
    }

    @GetMapping("/user/getById")
    public ModelAndView getById(String userId){
        User user = new User();
        user.setId(userId);
        user.setName("张三");
        user.setAge(30);

        return new ModelAndView().setView("/user").addModel("user", user);
    }
}
