package com.deco.team;

public class teamDTO {

	private int idx;
	private String title;
	private String content;
	private String location;
	private int master;
	private String limit_p;
	private String create_at;
	private String deadline;
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getMaster() {
		return master;
	}
	public void setMaster(int master) {
		this.master = master;
	}
	public String getLimit_p() {
		return limit_p;
	}
	public void setLimit_p(String limit_p) {
		this.limit_p = limit_p;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	@Override
	public String toString() {
		return "teamDTO [idx=" + idx + ", title=" + title + ", content=" + content + ", location=" + location
				+ ", master=" + master + ", limit_p=" + limit_p + ", create_at=" + create_at + ", deadline=" + deadline
				+ "]";
	}
	
	
	
	
}
