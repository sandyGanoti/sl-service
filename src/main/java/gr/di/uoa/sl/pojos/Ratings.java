package gr.di.uoa.sl.pojos;

public class Ratings {

	String listProductItemId = null;
	int value = 0;
	String note = null;
	
	public Ratings(String listProductItemId, int value, String note) {
		this.listProductItemId = listProductItemId;
		this.value = value;
		this.note = note;
	}
	
	public String getListProductItemId() {
		return listProductItemId;
	}
	public void setListProductItemId(String listProductItemId) {
		this.listProductItemId = listProductItemId;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
