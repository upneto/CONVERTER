package org.robot.resources.exceptions;

public class ConfigurationException extends GenericException {

	/** Serial */
	private static final long serialVersionUID = -3188343940429982032L;

	public ConfigurationException(final String message) {
		super(message);
	}
	
	public ConfigurationException(final String message, final Throwable throwable){
		super(message, throwable);
	}
	
	public ConfigurationException(final Throwable throwable){
		super(throwable);
	}
		
	@Override
	public String getDefaultMessage() {
		return "ERRO NO CARREGAMENTO DOS PARAMETROS DA APLICACAO!";
	}

	@Override
	public int getErrorCode() {		
		return 1;
	}

}
