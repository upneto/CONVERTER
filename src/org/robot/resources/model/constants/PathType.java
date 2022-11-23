package org.robot.resources.model.constants;

public enum PathType {

	origin,
	destiny;
	
	public static PathType getBy(String charr) {
		PathType type = null;
		for(PathType value : values()) {
			if(value.name().equalsIgnoreCase(charr)) {
				 type = value;
				 break;
			}
		}
		return type;
	}
}
