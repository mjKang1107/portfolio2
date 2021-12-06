package com.deco.share;

public class shareDTO {

	private int idx;
	private int user_num;
	private String title;
	private String content;
	private String file;
	private String category;
	private int read_cnt;
	private int like_;
	private String create_at;
	private String tag;
	private int anony;
	private int repo_cnt;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}

	public int getLike_() {
		return like_;
	}

	public void setLike_(int like_) {
		this.like_ = like_;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getAnony() {
		return anony;
	}

	public void setAnony(int anony) {
		this.anony = anony;
	}
	
	public int getRepo_cnt() {
		return repo_cnt;
	}

	public void setRepo_cnt(int repo_cnt) {
		this.repo_cnt = repo_cnt;
	}

	@Override
	public String toString() {
		return "shareDTO [idx=" + idx + ", user_num=" + user_num + ", title=" + title + ", content=" + content
				+ ", file=" + file + ", category=" + category + ", read_cnt=" + read_cnt + ", like=" + like_
				+ ", create_at=" + create_at + ", tag=" + tag + ", anony=" + anony + ", repo_cnt=" + repo_cnt + "]";
	}

}
