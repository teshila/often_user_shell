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
public class Special_Buy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String buyPrice;
	private String code;
	private String exchangeType;
	private String marketType;
	private String name;
	private String pinyin;
	private String hangye;
	private String diQu;
	private String liuTongGu;
	private String shiyinglvJing;
	private String zongGuBen;
	private String yuSheBuyPrice;
	private String weiTuoPrice;
	private String addtime;
	private String prefix;
	
	@Column
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Column
	public String getAddtime() {
		return addtime;
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
	public String getDiQu() {
		return diQu;
	}

	@Column
	public String getExchangeType() {
		return exchangeType;
	}
	@Column
	public String getHangye() {
		return hangye;
	}

	@Column
	public String getLiuTongGu() {
		return liuTongGu;
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
	public String getShiyinglvJing() {
		return shiyinglvJing;
	}
	@Column
	public String getWeiTuoPrice() {
		return weiTuoPrice;
	}
	@Column
	public String getYuSheBuyPrice() {
		return yuSheBuyPrice;
	}

	@Column
	public String getZongGuBen() {
		return zongGuBen;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDiQu(String diQu) {
		this.diQu = diQu;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public void setHangye(String hangye) {
		this.hangye = hangye;
	}

	public void setLiuTongGu(String liuTongGu) {
		this.liuTongGu = liuTongGu;
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

	public void setShiyinglvJing(String shiyinglvJing) {
		this.shiyinglvJing = shiyinglvJing;
	}

	public void setWeiTuoPrice(String weiTuoPrice) {
		this.weiTuoPrice = weiTuoPrice;
	}

	public void setYuSheBuyPrice(String yuSheBuyPrice) {
		this.yuSheBuyPrice = yuSheBuyPrice;
	}

	public void setZongGuBen(String zongGuBen) {
		this.zongGuBen = zongGuBen;
	}


}
