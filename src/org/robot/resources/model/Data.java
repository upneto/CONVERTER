package org.robot.resources.model;

import java.util.ArrayList;
import java.util.List;

public class Data {

	private List<Field> collumns = new ArrayList<>();

	public Data addCollumn(Field field) {
		this.collumns.add(field);
		return this;
	}

	// ------ Getters and Setters ----------

	public List<Field> getCollumns() {
		return collumns;
	}

	public void setCollumns(List<Field> collumns) {
		this.collumns = collumns;
	}
}