package com.cy.otw.vo;


import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 好友信息
 * @author chenyi_info@126.com
 */
public class FriendVo {
	
	private Long id;
	
	private String name;
	
	private String showImg;
	
	private String motto;
	
	private String describe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
