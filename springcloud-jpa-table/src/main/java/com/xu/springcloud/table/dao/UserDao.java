//package com.xu.springcloud.table.dao;
//
//import com.xu.springcloud.table.shop.entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
///**
// * @Auther: 徐亮亮
// * @Date: 2018/11/2 15:04
// * @Description:
// */
//@Repository
//public interface UserDao extends JpaRepository<UserEntity, String> {
//
//   /* //自定义repository。手写sql
//    @Query(value = "update user set name=?1 where id=?4",nativeQuery = true)   //占位符传值形式
//    @Modifying
//    int updateById(String name,int id);
//
//    @Query("from User u where u.username=:username")   //SPEL表达式
//    UserEntity findUser(@Param("username") String username);// 参数username 映射到数据库字段username*/
//}
