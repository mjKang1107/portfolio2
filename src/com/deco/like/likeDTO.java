package com.deco.like;

public class likeDTO {

	private int idx;
	private int user_num;
	private int content_num;
	private int content_type;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getContent_num() {
		return content_num;
	}
	public void setContent_num(int content_num) {
		this.content_num = content_num;
	}
	public int getContent_type() {
		return content_type;
	}
	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}
	
	
	@Override
	public String toString() {
		return "likeDTO [idx=" + idx + ", user_num=" + user_num + ", content_num=" + content_num + ", content_type=" + content_type + "]";
	}
	
	
	
}
