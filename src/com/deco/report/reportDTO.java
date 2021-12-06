package com.deco.report;

import org.json.simple.JSONObject;

public class reportDTO {
    private int idx;
    private int user_num;
    private int content_num;
    private int re_type;
    private int content_type;
    private String re_comment;
    private String re_date;
    
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
	public int getRe_type() {
		return re_type;
	}
	public void setRe_type(int re_type) {
		this.re_type = re_type;
	}
	public int getContent_type() {
		return content_type;
	}
	public void setContent_type(int content_type) {
		this.content_type = content_type;
	}
	public String getRe_comment() {
		return re_comment;
	}
	public void setRe_comment(String re_comment) {
		this.re_comment = re_comment;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	
	@Override
    public String toString() {
        return "reportDTO [idx=" + idx + ", user_num=" + user_num + ", content_num=" + content_num + ", re_type="
                + re_type + ", content_type=" + content_type + ", re_comment=" + re_comment + ", re_date=" + re_date
                + "]";
    }
    
    public void setBody(JSONObject jsonObj) {
    	try{
    		content_num = Integer.parseInt(jsonObj.get("contentNum").toString());
    		content_type = Integer.parseInt(jsonObj.get("contentType").toString());        
    		re_type = Integer.parseInt((String) jsonObj.get("type").toString());
    		re_comment = (String) jsonObj.get("content");
    	}catch(Exception e){
    		System.out.println("reportDTO 셋팅 중 이상 발생!");
    		e.printStackTrace();
    	}
    }
}
