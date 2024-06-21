package beans;

public class MenuList {
	private String menuItem;
	private String url;
	
	
	public MenuList(String menuItem, String url){
		this.menuItem = menuItem;
		this.url = url;
	}
	
	
	public String getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
}
