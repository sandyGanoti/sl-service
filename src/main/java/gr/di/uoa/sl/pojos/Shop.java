package gr.di.uoa.sl.pojos;

public class Shop {

	String id = null;
	String name = null;
	double latitude = 0.0;
	double logtitude = 0.0;
	
	public Shop(String id, String name) {
		this.id = id;
		this.name = name;
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

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLogtitude() {
		return logtitude;
	}
	public void setLogtitude(double logtitude) {
		this.logtitude = logtitude;
	}
}