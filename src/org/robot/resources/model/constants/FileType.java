package org.robot.resources.model.constants;

public enum FileType {
	
	XLS,
	XLSX,
	TXT;

	public static FileType getBy(String charr) {
		FileType type = null;
		for(FileType value : values()) {
			if(value.name().equalsIgnoreCase(charr)) {
				 type = value;
				 break;
			}
		}
		return type;
	}
}
