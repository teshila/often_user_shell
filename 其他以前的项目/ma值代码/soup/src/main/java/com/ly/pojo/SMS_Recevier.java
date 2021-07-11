package com.ly.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@Table(indexes={@Index(name="phone",columnList="phone")})
public class SMS_Recevier  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String addtime;
	private String isSend;
	private String name;
	private String phone;
	@Column
	public String getAddtime() {
		return addtime;
	}
	@Column
	public String getIsSend() {
		return isSend;
	}
	@Column
	public String getName() {
		return name;
	}

	@Id
	public String getPhone() {
		return phone;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
