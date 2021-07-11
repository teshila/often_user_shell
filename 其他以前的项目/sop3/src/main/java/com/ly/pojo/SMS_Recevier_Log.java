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
public class SMS_Recevier_Log  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String phone;
	private String msg;
	private String time;
	private String flag;
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column
	public String getFlag() {
		return flag;
	}
	@Column
	public String getMsg() {
		return msg;
	}
	@Column
	public String getPhone() {
		return phone;
	}
	@Column
	public String getTime() {
		return time;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}
