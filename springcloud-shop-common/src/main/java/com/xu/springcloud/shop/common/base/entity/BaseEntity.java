
package com.xu.springcloud.shop.common.base.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
//@Entity
public class BaseEntity implements Serializable {

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

}
