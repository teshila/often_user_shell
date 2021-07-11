package com.ly.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@Table(indexes = { @Index(name = "code", columnList = "code") })
public class Sell implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String yuSheSellPrice;
	private String code;
	private String name;
	private String exchangeType;
	private String marketType;
	private String pinyin;
	private String buyPrice;
	private String weiTuoTodaySellPrice;
	@Column
	public String getWeiTuoTodaySellPrice() {
		return weiTuoTodaySellPrice;
	}
	public void setWeiTuoTodaySellPrice(String weiTuoTodaySellPrice) {
		this.weiTuoTodaySellPrice = weiTuoTodaySellPrice;
	}
	@Column
	public String getBuyPrice() {
		return buyPrice;
	}
	@Id
	public String getCode() {
		return code;
	}
	@Column
	public String getExchangeType() {
		return exchangeType;
	}
	@Column
	public String getMarketType() {
		return marketType;
	}
	@Column
	public String getName() {
		return name;
	}
	@Column
	public String getPinyin() {
		return pinyin;
	}
	@Column
	public String getYuSheSellPrice() {
		return yuSheSellPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public void setYuSheSellPrice(String yuSheSellPrice) {
		this.yuSheSellPrice = yuSheSellPrice;
	}
	
	
}
