package outsourcing.model;

public class Paging {

	int cursor;
	int record_per_page;
	
	public Paging(){
		
	}
	
	public Paging(int record_per_page,int cursor){
		this.cursor = cursor;
		this.record_per_page = record_per_page;
	}
	
	public int getCursor() {
		return cursor;
	}
	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
	public int getRecord_per_page() {
		return record_per_page;
	}
	public void setRecord_per_page(int record_per_page) {
		this.record_per_page = record_per_page;
	}
	
}
