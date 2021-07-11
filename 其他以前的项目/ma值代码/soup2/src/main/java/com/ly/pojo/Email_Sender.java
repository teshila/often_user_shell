package com.ly.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@Table(indexes={@Index(name="address",columnList="address")})
public class Email_Sender  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String address;
	private String pwd;
	private String port;
	private String protocol;
	private String host;
	private String flag;
	@Id
	public String getAddress() {
		return address;
	}
	@Column
	public String getFlag() {
		return flag;
	}
	@Column
	public String getHost() {
		return host;
	}
	@Column
	public String getPort() {
		return port;
	}
	@Column
	public String getProtocol() {
		return protocol;
	}
	@Column
	public String getPwd() {
		return pwd;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
	
}