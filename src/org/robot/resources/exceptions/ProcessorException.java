package org.robot.resources.exceptions;

public class ProcessorException extends GenericException {

	/** Serial */
	private static final long serialVersionUID = -3188343940429982032L;
	
	public ProcessorException(final String message) {
		super(message);
	}
	
	public ProcessorException(final String message, final Throwable throwable){
		super(message, throwable);
	}
	
	public ProcessorException(final Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getDefaultMessage() {
		return "ERRO NO PROCESSAMENTO DO ARQUIVO!";
	}

	@Override
	public int getErrorCode() {		
		return 3;
	}

}
