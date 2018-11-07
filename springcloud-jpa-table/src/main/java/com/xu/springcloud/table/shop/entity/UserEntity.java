
package com.xu.springcloud.table.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class UserEntity {

	/**
	 * 主键ID
	 */
	@NotEmpty(message = "id不能为空")
	@Id
	@Column(name = "id", length = 32)
	private String id;
	/**
	 * 创建时间
	 */
	@Column(name = "created")
	private Timestamp created;
	/**
	 * 修改时间
	 */
	@Column(name = "updated")
	private Timestamp updated;

	@NotEmpty(message = "用户名不能为空")
	@Column(name = "user_name", length = 50)
	private String userName;

	@NotEmpty(message = "密码不能为空")
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "电话不能为空")
	@Column(name = "phone")
	private String phone;

	@NotEmpty(message = "邮件账户不能为空")
	@Column(name = "email")
	private String email;
}
