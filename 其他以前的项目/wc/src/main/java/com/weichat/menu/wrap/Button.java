package com.weichat.menu.wrap;

/**
 * 
 * @desc : 按钮的基类 * https://www.cnblogs.com/shirui/p/7825762.html
 *       https://www.cnblogs.com/jerehedu/p/7058353.html
 *       https://blog.csdn.net/fengyan5022/article/details/79399590
 * @author: shirayner
 * @date : 2017年11月13日 上午11:36:16
 */
public class Button {
	private String type;
	private String name;
	private Button[] sub_button;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}