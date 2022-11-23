package org.robot.resources.model;

import org.robot.resources.model.constants.FieldType;

/**
 * @author Ulisses P S Neto
 */
public class Field {

	private String value;
	private FieldType type;
	private int size;
	
	/**
	 * Default Constructor
	 */
	public Field() {
		super();
	}
	
	/**
	 * @param value
	 * @param type
	 * @param size
	 */
	public Field(String value, FieldType type, int size) {
		super();
		this.value = value;
		this.type = type;
		this.size = size;
	}
	
	
	/** Getters and Setters **/
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
