package org.robot.resources.exceptions;

public abstract class GenericException extends Exception {

	/** Serial */
	private static final long serialVersionUID = 1L;
	
	public GenericException(final String message) {
		super(message);
	}
	
	public GenericException(final String message, final Throwable throwable){
		super(message, throwable);
	}
	
	public GenericException(final Throwable throwable){
		super(throwable);
	}

	public abstract String getDefaultMessage();
	public abstract int getErrorCode();
	
	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder(this.getDefaultMessage());
		if(super.getMessage() != null) {
			message.append(" - ").append(super.getMessage());
		}
		return message.toString();
	}
}
