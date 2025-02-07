package br.edu.ifsp.encurtador.model.entity;

public class Access {
	private String link;
	private String ipAddress;
	private int count;
	
	public Access() {}
	
	public Access(String link, String ipAddress) {
		this.link = link;
		this.ipAddress = ipAddress;
		this.count = 0;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	 public int getCount() {
	        return count;
	    }

	    public void setCount(int count) {
	        this.count = count;
	    }
}
