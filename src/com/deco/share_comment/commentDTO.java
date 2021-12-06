package com.deco.share_comment;

public class commentDTO {

	private int comment_idx;
	private int share_idx;
	private int user_num;
	private String content;
	private int re_lev;
	private int re_seq;
	private String create_at;
	
	public int getComment_idx() {
		return comment_idx;
	}
	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}
	public int getShare_idx() {
		return share_idx;
	}
	public void setShare_idx(int share_idx) {
		this.share_idx = share_idx;
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
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	
	@Override
	public String toString() {
		return "commentDTO [comment_idx=" + comment_idx + ", share_idx=" + share_idx + ", user_num=" + user_num
				+ ", content=" + content + ", re_lev=" + re_lev + ", re_seq=" + re_seq + ", create_at=" + create_at
				+ "]";
	}

	
	
}