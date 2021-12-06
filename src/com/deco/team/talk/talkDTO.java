package com.deco.team.talk;

public class talkDTO {

	private int idx;
	private int team_idx;
	private String filename;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "talkDTO [idx=" + idx + ", team_idx=" + team_idx + ", filename=" + filename + "]";
	}
}
