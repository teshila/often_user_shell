package com.ly.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicUpdate(true)
@Table(indexes={@Index(name="stock_code",columnList="stock_code")})
public class Orders implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String entrust_no;
	private String stock_code;
	private String stock_name;
	private String bs_name;
	private String status_name;
	private String entrust_price;
	private String business_price;
	private String entrust_amount;
	private String business_amount;
	private String entrust_bs;
	private String entrust_status;
	private String entrust_time;
	private String entrust_date;
	private String business_balance;
	private String exchange_type;
	private String entrust_banlance;
	private String pinyin;
	
	@Id
	@GeneratedValue(generator = "myId")    
	@GenericGenerator(name = "myId", strategy = "uuid")  
	public String getId() {
		return id;
	}
	
	@Column
	public String getBs_name() {
		return bs_name;
	}
	@Column
	public String getBusiness_amount() {
		return business_amount;
	}
	@Column
	public String getBusiness_balance() {
		return business_balance;
	}
	@Column
	public String getBusiness_price() {
		return business_price;
	}
	@Column
	public String getEntrust_amount() {
		return entrust_amount;
	}
	@Column
	public String getEntrust_banlance() {
		return entrust_banlance;
	}
	@Column
	public String getEntrust_bs() {
		return entrust_bs;
	}
	@Column
	public String getEntrust_date() {
		return entrust_date;
	}
	@Column
	public String getEntrust_no() {
		return entrust_no;
	}
	@Column
	public String getEntrust_price() {
		return entrust_price;
	}
	@Column
	public String getEntrust_status() {
		return entrust_status;
	}
	@Column
	public String getEntrust_time() {
		return entrust_time;
	}
	@Column
	public String getExchange_type() {
		return exchange_type;
	}
	
	@Column
	public String getPinyin() {
		return pinyin;
	}
	@Column
	public String getStatus_name() {
		return status_name;
	}
	@Column
	public String getStock_code() {
		return stock_code;
	}
	@Column
	public String getStock_name() {
		return stock_name;
	}
	public void setBs_name(String bs_name) {
		this.bs_name = bs_name;
	}
	public void setBusiness_amount(String business_amount) {
		this.business_amount = business_amount;
	}
	public void setBusiness_balance(String business_balance) {
		this.business_balance = business_balance;
	}
	public void setBusiness_price(String business_price) {
		this.business_price = business_price;
	}
	public void setEntrust_amount(String entrust_amount) {
		this.entrust_amount = entrust_amount;
	}
	public void setEntrust_banlance(String entrust_banlance) {
		this.entrust_banlance = entrust_banlance;
	}
	public void setEntrust_bs(String entrust_bs) {
		this.entrust_bs = entrust_bs;
	}
	public void setEntrust_date(String entrust_date) {
		this.entrust_date = entrust_date;
	}
	public void setEntrust_no(String entrust_no) {
		this.entrust_no = entrust_no;
	}
	public void setEntrust_price(String entrust_price) {
		this.entrust_price = entrust_price;
	}
	public void setEntrust_status(String entrust_status) {
		this.entrust_status = entrust_status;
	}
	public void setEntrust_time(String entrust_time) {
		this.entrust_time = entrust_time;
	}
	public void setExchange_type(String exchange_type) {
		this.exchange_type = exchange_type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

}
