package org.robot.resources.exceptions;

public class ReaderException extends GenericException {

	/**Serial */
	private static final long serialVersionUID = -3188343940429982032L;
	
	public ReaderException(final String message) {
		super(message);
	}
	
	public ReaderException(final String message, final Throwable throwable){
		super(message, throwable);
	}
	
	public ReaderException(final Throwable throwable){
		super(throwable);
	}

	@Override
	public String getDefaultMessage() {
		return "ERRO NA LEITURA DO ARQUIVO!";
	}

	@Override
	public int getErrorCode() {
		return 2;
	}

}
