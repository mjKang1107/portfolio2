package com.deco.team.comment;

public class Team_commentDTO {

	private int idx;
	private int team_idx;
	private int user_num;
	private String content;
	private String create_at;
	private int secret;
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
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
	
	@Override
	public String toString() {
		return "Team_commentDTO [idx=" + idx + ", team_idx=" + team_idx + ", user_num=" + user_num + ", content="
				+ content + ", create_at=" + create_at + ", secret=" + secret + "]";
	}
	
	
	
	
	
	
}
