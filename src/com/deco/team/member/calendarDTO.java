package com.deco.team.member;

public class calendarDTO {

	private int idx;
	private int team_idx;
	private int user_idx;
	private String title;
	private String description;
	private String start;
	private String end;
	private String type;
	private String backgroundcolor;
	private String textcolor;
	private boolean allday;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getTeam_idx() {
		return team_idx;
	}
	public void setTeam_idx(int team_idx) {
		this.team_idx = team_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBackgroundcolor() {
		return backgroundcolor;
	}
	public void setBackgroundcolor(String backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}
	public String getTextcolor() {
		return textcolor;
	}
	public void setTextcolor(String textcolor) {
		this.textcolor = textcolor;
	}
	public boolean isAllday() {
		return allday;
	}
	public void setAllday(boolean allday) {
		this.allday = allday;
	}
	@Override
	public String toString() {
		return "calendarDTO [idx=" + idx + ", team_idx=" + team_idx + ", user_idx=" + user_idx + ", title=" + title
				+ ", description=" + description + ", start=" + start + ", end=" + end + ", type=" + type
				+ ", backgroundcolor=" + backgroundcolor + ", textcolor=" + textcolor + ", allday=" + allday + "]";
	}
	
}
