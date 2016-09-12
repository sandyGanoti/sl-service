package gr.di.uoa.sl.messenger;

public class AppResponse {

	public enum Status {
		Success, Failure, NotFound
	}
	
	private Status status = Status.Failure;
	private Object data = null;
	private String message = null;
	
	public AppResponse(Status status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}