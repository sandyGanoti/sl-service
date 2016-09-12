package gr.di.uoa.sl.messenger;

public class Messages {

	public static String getNotFoundMessage(String whoNotFound) {
		return whoNotFound + " not found!";
	}
	
	public static String getNotFoundMessage(String whoNotFound, String whoNotFoundId) {
		return whoNotFound + " (" + whoNotFoundId +")" + " not found!";
	}
	
	public static String getFoundMessage() {
		return "found!";
	}
	
	public static String getInsertedMessage(String whoInserted, String whoInsertedId) {
		return whoInserted + " (" + whoInsertedId +")" + " not inserted!";
	}
	
	public static String getNotInsertedMessage(String whoNotInserted, String whoNotInsertedId) {
		return whoNotInserted + " (" + whoNotInsertedId +")" + " not inserted!";
	}
	
	public static String getNotInsertedMessage(String whoNotInserted) {
		return whoNotInserted + " not inserted!";
	}
	
	public static String getInputNotFoundMessage() {
		return "Input not found!";
	}
	
}