package com.deco.notice.db;

import java.util.Date;

public class noticeDTO {

	private int idx;
	private int user_num;
	private String title;
	private String content;
	private String file;
	private Date create_at;
	private int count;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "noticeDTO [idx=" + idx + ", user_num=" + user_num + ", title=" + title + ", content=" + content
				+ ", file=" + file + ", create_at=" + create_at + ", count=" + count + "]";
	}
	
	
	
	
}
