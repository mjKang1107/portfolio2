package com.deco.team.member;

public class teamMemberDTO {

	private int idx;
	private int team_idx;
	private int member;
	private int submit;
	private String create_at;
	
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
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getSubmit() {
		return submit;
	}
	public void setSubmit(int submit) {
		this.submit = submit;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	
	@Override
	public String toString() {
		return "teamMemberDTO [idx=" + idx + ", team_idx=" + team_idx + ", member=" + member + ", submit=" + submit
				+ ", create_at=" + create_at + "]";
	}
}
