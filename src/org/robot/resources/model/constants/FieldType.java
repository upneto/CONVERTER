package org.robot.resources.model.constants;

public enum FieldType {

	DECIMAL,
	NUMBER,
	CHAR;
	
	public static FieldType getBy(String charr) {
		FieldType type = null;
		for(FieldType value : values()) {
			if(value.name().equalsIgnoreCase(charr)) {
				 type = value;
				 break;
			}
		}
		return type;
	}
}
