package org.robot.resources.model.constants;

public final class GeneralConstants {

	public static final String PATH_SEPARATOR = System.getProperty("file.separator");
	
	public final class APP {
		public final class LOG4J {
			public static final String TARGET = "app.config.log4j.target";
			public static final String LEVEL = "app.config.log4j.level";
		}
	}
	
	public final class FILE {
		
		public static final String DIRECTORY_ORIGIN = "file.config.directory.origin";
		public static final String DIRECTORY_DESTINY = "file.config.directory.destiny";

		public final class LAYOUT {
			public static final String PROP_BASE = "file.config.layout."; 
			public static final String NAMES = "file.config.layout.names";
			public static final String ORIGIN_TYPES = "file.config.layout.origin.types";
			public static final String DESTINY_TYPE = "file.config.layout.destiny.type";
		}

	}
}
