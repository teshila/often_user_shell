package com.ly.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@Table(indexes={@Index(name="appId",columnList="appId")})
public class SMS_Sender implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String appId;
	private String appKey;
	private String sign;
	private String templateIdForBuy;
	private String templateIdForSell;
	private String times;
	@Id
	public String getAppId() {
		return appId;
	}
	@Column
	public String getAppKey() {
		return appKey;
	}
	@Column
	public String getSign() {
		return sign;
	}
	@Column
	public String getTemplateIdForBuy() {
		return templateIdForBuy;
	}
	@Column
	public String getTemplateIdForSell() {
		return templateIdForSell;
	}
	@Column
	public String getTimes() {
		return times;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setTemplateIdForBuy(String templateIdForBuy) {
		this.templateIdForBuy = templateIdForBuy;
	}
	public void setTemplateIdForSell(String templateIdForSell) {
		this.templateIdForSell = templateIdForSell;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	

}
