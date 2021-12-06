package com.deco.bookmark.db;

public class BookmarkDTO {

	private int idx;
	private int user_num;
	private int content_num;
	private int type;
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "BookmarkDTO [idx=" + idx + ", user_num=" + user_num + ", content_num=" + content_num + ", type=" + type
				+ "]";
	}
	
	
	
	
}
