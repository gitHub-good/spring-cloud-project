//package com.xu.springcloud.table.service;
//
//import com.xu.springcloud.table.dao.UserDao;
//import com.xu.springcloud.table.shop.entity.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.Iterator;
//import java.util.Optional;
//
///**
// * @Auther: 徐亮亮
// * @Date: 2018/11/2 15:09
// * @Description:
// */
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserDao userDao;
//
//    /**
//     * 删除
//     *
//     * @param id
//     */
//    @Override
//    public void delete(String id) {
//        userDao.deleteById(id);
//    }
//
//    /**
//     * 增加
//     *
//     * @param user
//     */
//    @Override
//    public void insert(UserEntity user) {
//        userDao.save(user);
//    }
//
//    /**
//     * 更新
//     *
//     * @param user
//     */
//    @Override
//    public int update(UserEntity user) {
//        userDao.save(user);
//        return 1;
//    }
//
//    /**
//     * 查询单个
//     *
//     * @param id
//     */
//    @Override
//    public UserEntity selectById(String id) {
//        Optional<UserEntity> optional = userDao.findById(id);
//        UserEntity user = optional.get();
//        return user;
//    }
//
//    /**
//     * 查询全部列表,并做分页
//     *
//     * @param pageNum  开始页数
//     * @param pageSize 每页显示的数据条数
//     */
//    @Override
//    public Iterator<UserEntity> selectAll(int pageNum, int pageSize) {
//        //将参数传给这个方法就可以实现物理分页了，非常简单。
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
//        Page<UserEntity> users = userDao.findAll(pageable);
//        Iterator<UserEntity> userIterator = users.iterator();
//        return userIterator;
//    }
//}