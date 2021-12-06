package com.deco;

public class ActionForward {

	private String URL;
	private boolean isRedirect;
	
	public ActionForward(){}
	public ActionForward(String URL, boolean isRedirect) {
		this.URL = URL;
		this.isRedirect = isRedirect;
	}

	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String toString() {
		return "ActionForward [URL=" + URL + ", isRedirect=" + isRedirect + "]";
	}
}
