package gr.di.uoa.sl.pojos;

public class ProductShop {

	String id = null;
	String name = null;
	int value = 0;
	
	public ProductShop(String id, String name, int value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}