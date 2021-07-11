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
public class Chi_Cang {

	private String code;
	private String name;
	private String marketType;
	private String exchangeType;
	private String cost_price;
	private String income_balance_ratio;
	private String market_value;
	private String current_amount;
	private String enable_amount;
	private String last_price;
	private String income_balance;
	private String enroute_amount;
	private String account;
	private String accountname;
	
	private String pinyin;
	private String hangye;
	private String diQu;
	private String liuTongGu;
	private String shiyinglvJing;
	private String zongGuBen;
	
	@Column
	public String getAccount() {
		return account;
	}
	@Column
	public String getAccountname() {
		return accountname;
	}
	@Id
	public String getCode() {
		return code;
	}
	@Column
	public String getCost_price() {
		return cost_price;
	}
	@Column
	public String getCurrent_amount() {
		return current_amount;
	}
	public String getDiQu() {
		return diQu;
	}
	@Column
	public String getEnable_amount() {
		return enable_amount;
	}
	
	
	
	@Column
	public String getEnroute_amount() {
		return enroute_amount;
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
	public String getIncome_balance() {
		return income_balance;
	}
	@Column
	public String getIncome_balance_ratio() {
		return income_balance_ratio;
	}
	@Column
	public String getLast_price() {
		return last_price;
	}
	@Column
	public String getLiuTongGu() {
		return liuTongGu;
	}
	@Column
	public String getMarket_value() {
		return market_value;
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
	public String getZongGuBen() {
		return zongGuBen;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCost_price(String cost_price) {
		this.cost_price = cost_price;
	}
	public void setCurrent_amount(String current_amount) {
		this.current_amount = current_amount;
	}
	public void setDiQu(String diQu) {
		this.diQu = diQu;
	}
	public void setEnable_amount(String enable_amount) {
		this.enable_amount = enable_amount;
	}
	public void setEnroute_amount(String enroute_amount) {
		this.enroute_amount = enroute_amount;
	}
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}
	public void setHangye(String hangye) {
		this.hangye = hangye;
	}
	public void setIncome_balance(String income_balance) {
		this.income_balance = income_balance;
	}
	public void setIncome_balance_ratio(String income_balance_ratio) {
		this.income_balance_ratio = income_balance_ratio;
	}
	public void setLast_price(String last_price) {
		this.last_price = last_price;
	}
	public void setLiuTongGu(String liuTongGu) {
		this.liuTongGu = liuTongGu;
	}
	public void setMarket_value(String market_value) {
		this.market_value = market_value;
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
	public void setZongGuBen(String zongGuBen) {
		this.zongGuBen = zongGuBen;
	}
	
}
