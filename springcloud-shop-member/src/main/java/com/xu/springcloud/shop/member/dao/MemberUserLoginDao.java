
package com.xu.springcloud.shop.member.dao;

import com.xu.springcloud.shop.common.base.myabtis.BaseDao;
import com.xu.springcloud.shop.member.entity.MemberUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

/**
 * @author 徐亮亮
 * @date 2018/10/31 12:33
 * @Description: dao数据库的访问操作处理层
 */
@Mapper
public interface MemberUserLoginDao extends BaseDao {

	/**
	 * @date 2018/11/2 13:20
	 * @Description: 功能描述:(使用条件查询用户信息)
	 */
	@Select("select * from tbl_member_user where phone=${memberUser.phone};")
	MemberUser getMbUser(@Param("memberUser") MemberUser memberUser);


	@Select("select * from tbl_member_user  where phone=#{phone} and password=#{password}")
	MemberUser getUserPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

	@Select("select * from tbl_member_user  where id=#{id}")
	MemberUser getUserInfo(@Param("id") String id);

	@Select("select * from tbl_member_user  where openid=#{openId}")
	MemberUser findUserOpenId(@Param("openId") String openId);

	@Update("update tbl_member_user set open_id=#{openId} ,updated=#{updated} where id=#{id}")
	int updateUserOpenId(@Param("openId") String openId, @Param("updated") Timestamp updated, @Param("id") String id );
}
