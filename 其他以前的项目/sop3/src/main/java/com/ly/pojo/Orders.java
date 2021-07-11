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
@Table(indexes={@Index(name="id",columnList="id")})
public class Orders implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String exchange_type;
	private String secu_code;
    private String order_amt;
    private String entrust_status;
    private String withdrawn_qty;
    private String trd_id;
    private String matched_amt;
    private String account;
    private String qty;
    private String op_remark;
    private String secu_acc;
    private String valid_flag;
    private String market;
    private String price;
    private String matched_price;
    private String matched_qty;
    private String secu_name;
    private String recall_tag;
    private String entrust_status_num;
    private String trd_name;
    private String order_id;
    private String trd_dire_num;
    private String market_name;
    private String is_withdraw;
    private String valid_flag_name;
    private String order_time;
	private String pinyin;
	private String createTime;
	
	
	@Column
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column
    public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Column
	public String getAccount() {
		return account;
	}

	@Column
	public String getEntrust_status() {
		return entrust_status;
	}

	@Column
	public String getEntrust_status_num() {
		return entrust_status_num;
	}

	@Column
	public String getExchange_type() {
		return exchange_type;
	}


	@Id
	@GeneratedValue(generator = "myId")    
	@GenericGenerator(name = "myId", strategy = "uuid")  
	public String getId() {
		return id;
	}

	@Column
	public String getIs_withdraw() {
		return is_withdraw;
	}

	@Column
	public String getMarket() {
		return market;
	}

	@Column
	public String getMarket_name() {
		return market_name;
	}

	@Column
	public String getMatched_amt() {
		return matched_amt;
	}

	@Column
	public String getMatched_price() {
		return matched_price;
	}

	@Column
	public String getMatched_qty() {
		return matched_qty;
	}

	@Column
	public String getOp_remark() {
		return op_remark;
	}

	@Column
	public String getOrder_amt() {
		return order_amt;
	}

	@Column
	public String getOrder_id() {
		return order_id;
	}

	@Column
	public String getOrder_time() {
		return order_time;
	}

	@Column
	public String getPrice() {
		return price;
	}

	@Column
	public String getQty() {
		return qty;
	}

	@Column
	public String getRecall_tag() {
		return recall_tag;
	}

	@Column
	public String getSecu_acc() {
		return secu_acc;
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
	public String getTrd_dire_num() {
		return trd_dire_num;
	}

	@Column
	public String getTrd_id() {
		return trd_id;
	}

	@Column
	public String getTrd_name() {
		return trd_name;
	}

	@Column
	public String getValid_flag() {
		return valid_flag;
	}

	@Column
	public String getValid_flag_name() {
		return valid_flag_name;
	}

	@Column
	public String getWithdrawn_qty() {
		return withdrawn_qty;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public void setEntrust_status(String entrust_status) {
		this.entrust_status = entrust_status;
	}


	public void setEntrust_status_num(String entrust_status_num) {
		this.entrust_status_num = entrust_status_num;
	}


	public void setExchange_type(String exchange_type) {
		this.exchange_type = exchange_type;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setIs_withdraw(String is_withdraw) {
		this.is_withdraw = is_withdraw;
	}


	public void setMarket(String market) {
		this.market = market;
	}


	public void setMarket_name(String market_name) {
		this.market_name = market_name;
	}


	public void setMatched_amt(String matched_amt) {
		this.matched_amt = matched_amt;
	}


	public void setMatched_price(String matched_price) {
		this.matched_price = matched_price;
	}


	public void setMatched_qty(String matched_qty) {
		this.matched_qty = matched_qty;
	}


	public void setOp_remark(String op_remark) {
		this.op_remark = op_remark;
	}


	public void setOrder_amt(String order_amt) {
		this.order_amt = order_amt;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public void setQty(String qty) {
		this.qty = qty;
	}


	public void setRecall_tag(String recall_tag) {
		this.recall_tag = recall_tag;
	}


	public void setSecu_acc(String secu_acc) {
		this.secu_acc = secu_acc;
	}


	public void setSecu_code(String secu_code) {
		this.secu_code = secu_code;
	}


	public void setSecu_name(String secu_name) {
		this.secu_name = secu_name;
	}


	public void setTrd_dire_num(String trd_dire_num) {
		this.trd_dire_num = trd_dire_num;
	}


	public void setTrd_id(String trd_id) {
		this.trd_id = trd_id;
	}


	public void setTrd_name(String trd_name) {
		this.trd_name = trd_name;
	}


	public void setValid_flag(String valid_flag) {
		this.valid_flag = valid_flag;
	}


	public void setValid_flag_name(String valid_flag_name) {
		this.valid_flag_name = valid_flag_name;
	}


	public void setWithdrawn_qty(String withdrawn_qty) {
		this.withdrawn_qty = withdrawn_qty;
	}
	

}
