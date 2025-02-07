package br.edu.ifsp.encurtador.model.entity;

public class Access {
	private String link;
	private String ipAddress;
	
	public Access() {}
	
	public Access(String link, String ipAddress) {
		this.link = link;
		this.ipAddress = ipAddress;
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
}
