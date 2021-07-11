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
	private String currency;
	private String secu_code;
	private String seat;
	private String curr_price;
	private String day_profit_loss;
	private String share_avl;
	private String total_profit_loss_prop;
	private String mkt_qty;
	private String secu_name;
	private String day_profit_loss_prop;
	private String total_profit_loss;
	private String secu_cls;
	private String secu_acc;
	private String cost_price;
	private String mkt_val;
	private String account;
	private String accountname;
	
	private String pinyin;
	private String hangye;
	private String diQu;
	private String liuTongGu;
	private String shiyinglvJing;
	private String zongGuBen;
	private String prefix;
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
	public String getCurr_price() {
		return curr_price;
	}
	@Column
	public String getCurrency() {
		return currency;
	}
	@Column
	public String getDay_profit_loss() {
		return day_profit_loss;
	}
	@Column
	public String getDay_profit_loss_prop() {
		return day_profit_loss_prop;
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
	public String getMkt_qty() {
		return mkt_qty;
	}
	@Column
	public String getMkt_val() {
		return mkt_val;
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
	public String getPrefix() {
		return prefix;
	}
	@Column
	public String getSeat() {
		return seat;
	}
	@Column
	public String getSecu_acc() {
		return secu_acc;
	}
	@Column
	public String getSecu_cls() {
		return secu_cls;
	}
	@Column
	public String getSecu_code() {
		return secu_code;
	}
	@Column
	public String getSecu_name() {
		return secu_name;
	}
	@Column
	public String getShare_avl() {
		return share_avl;
	}
	@Column
	public String getShiyinglvJing() {
		return shiyinglvJing;
	}
	@Column
	public String getTotal_profit_loss() {
		return total_profit_loss;
	}
	@Column
	public String getTotal_profit_loss_prop() {
		return total_profit_loss_prop;
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
	public void setCurr_price(String curr_price) {
		this.curr_price = curr_price;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setDay_profit_loss(String day_profit_loss) {
		this.day_profit_loss = day_profit_loss;
	}
	public void setDay_profit_loss_prop(String day_profit_loss_prop) {
		this.day_profit_loss_prop = day_profit_loss_prop;
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
	public void setMkt_qty(String mkt_qty) {
		this.mkt_qty = mkt_qty;
	}
	public void setMkt_val(String mkt_val) {
		this.mkt_val = mkt_val;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public void setSecu_acc(String secu_acc) {
		this.secu_acc = secu_acc;
	}
	public void setSecu_cls(String secu_cls) {
		this.secu_cls = secu_cls;
	}
	public void setSecu_code(String secu_code) {
		this.secu_code = secu_code;
	}
	public void setSecu_name(String secu_name) {
		this.secu_name = secu_name;
	}
	public void setShare_avl(String share_avl) {
		this.share_avl = share_avl;
	}
	public void setShiyinglvJing(String shiyinglvJing) {
		this.shiyinglvJing = shiyinglvJing;
	}
	public void setTotal_profit_loss(String total_profit_loss) {
		this.total_profit_loss = total_profit_loss;
	}
	public void setTotal_profit_loss_prop(String total_profit_loss_prop) {
		this.total_profit_loss_prop = total_profit_loss_prop;
	}
	public void setZongGuBen(String zongGuBen) {
		this.zongGuBen = zongGuBen;
	}
	
	
}
