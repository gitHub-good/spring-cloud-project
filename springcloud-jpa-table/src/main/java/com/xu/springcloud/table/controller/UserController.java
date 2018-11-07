//package com.xu.springcloud.table.controller;
//
//import com.xu.springcloud.table.service.UserService;
//import com.xu.springcloud.table.shop.entity.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * @Auther: 徐亮亮
// * @Date: 2018/11/2 15:11
// * @Description:
// */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
//    public void delete(@PathVariable("id") String id) {
//        userService.delete(id);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/insert")
//    public void insert(UserEntity user) {
//        userService.insert(user);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
//    public void update(@RequestParam UserEntity user) {
//        userService.update(user);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/{id}/select")
//    public UserEntity select(@PathVariable("id") String id) {
//        return userService.selectById(id);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/selectAll/{pageNum}/{pageSize}")
//    public List<UserEntity> selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
//        Iterator<UserEntity> userIterator = userService.selectAll(pageNum, pageSize);
//        List<UserEntity> list = new ArrayList<>();
//        while (userIterator.hasNext()) {
//            list.add(userIterator.next());
//        }
//        return list;
//    }
//
//}