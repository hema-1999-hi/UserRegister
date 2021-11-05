package userRegistration.model;

public class SaveOrUpdateException extends RuntimeException {
	
	private String appMessage;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveOrUpdateException(String exMsg, String appMsg) {
		super(exMsg);
		this.appMessage = appMsg;
	}
	
	public String getAppMessage(){ return appMessage;}

}
