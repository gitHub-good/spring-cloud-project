package com.xu.springcloud.shop.member.entity;

import com.xu.springcloud.shop.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
//@Entity
//@Table(name = "tbl_member_user")
public class MemberUser extends BaseEntity {

	@NotEmpty(message = "用户名不能为空")
	@Column(name = "user_name", length = 50)
	private String userName;

	@NotEmpty(message = "密码不能为空")
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "电话不能为空")
	@Column(name = "phone",length = 15)
	private String phone;

	@NotEmpty(message = "邮件账户不能为空")
	@Column(name = "email", length = 32)
	private String email;
	//用户外界服务关联ID
	private String openId;

}