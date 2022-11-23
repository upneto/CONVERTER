package org.robot.resources.exceptions;

public class WriterException extends GenericException {

	/** Serial */
	private static final long serialVersionUID = -3188343940429982032L;
	
	public WriterException(final String message) {
		super(message);
	}
	
	public WriterException(final String message, final Throwable throwable){
		super(message, throwable);
	}
	
	public WriterException(final Throwable throwable){
		super(throwable);
	}


	@Override
	public String getDefaultMessage() {
		return "ERRO NA CRIACAO DO ARQUIVO FINAL!";
	}

	@Override
	public int getErrorCode() {
		return 4;
	}

}
