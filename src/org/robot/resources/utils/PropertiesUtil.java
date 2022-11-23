package org.robot.resources.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.robot.resources.exceptions.ConfigurationException;
import org.robot.resources.model.constants.GeneralConstants;

/**
 * @author Ulisses P S Neto
 */
public class PropertiesUtil {

	private static final String FILE_PROPERTIES_NAME = "config.properties";
	private static final Properties FILE_PROPERTIES = new Properties();
	
	/**
	 * Construtor
	 * @throws ProcessException
	 */
	public PropertiesUtil(final String PATH) throws ConfigurationException {
		try {
			StringBuilder path = new StringBuilder(PATH)
					.append(GeneralConstants.PATH_SEPARATOR)
					.append("config")
					.append(GeneralConstants.PATH_SEPARATOR)
					.append(FILE_PROPERTIES_NAME);
			final InputStream loader = new FileInputStream(path.toString());
			FILE_PROPERTIES.load(loader);
		} catch (IOException ex) {
			throw new ConfigurationException("Arquivo 'Properties' não encontrado!", ex);
		}
	}
	
	/**
	 * Construtor
	 * @throws ProcessException
	 */
	public PropertiesUtil(final String PATH, final String FILE_NAME) throws ConfigurationException {
		try {
			StringBuilder path = new StringBuilder(PATH)
					.append(GeneralConstants.PATH_SEPARATOR)
					.append("config")
					.append(GeneralConstants.PATH_SEPARATOR)
					.append(FILE_NAME);
			final InputStream loader = new FileInputStream(path.toString());
			FILE_PROPERTIES.load(loader);
		} catch (IOException ex) {
			throw new ConfigurationException("Arquivo 'Properties' não encontrado!", ex);
		}
	}
	
	/**
	 * Obtem o valor pela chave
	 * @param key
	 * @return
	 * @throws ProcessException
	 */
	public String getValue(final String KEY) throws ConfigurationException {
		final String value = FILE_PROPERTIES.getProperty(KEY);
		if(value == null){
			throw new ConfigurationException("Chave '" + KEY + "' não encontrada no arquivo 'properties'!");
		}
		return value;			
	}
	
	/**
	 * Verifica se h� a chave
	 * @param key
	 * @return
	 */
	public boolean contains(final String KEY) {
		return (null != FILE_PROPERTIES.getProperty(KEY));
	}
}
