package org.robot.sources;

import org.apache.log4j.Logger;
import org.robot.Main;
import org.robot.resources.utils.PropertiesUtil;

public abstract class AbstractConverter {

	protected final static Logger LOGGER = Logger.getLogger(Main.class);	
	protected PropertiesUtil properties = null;

	/**
	 * Constructor
	 * @param PROPERTIES
	 */
	public AbstractConverter (final PropertiesUtil PROPERTIES) {		
		this.properties = PROPERTIES;
	}
}
