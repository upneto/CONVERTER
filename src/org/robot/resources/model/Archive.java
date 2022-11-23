package org.robot.resources.model;

import java.io.File;

import org.robot.resources.model.constants.FileType;

public class Archive {

	private String name;
	private FileType type;
	private File file;
	
	public Archive () {
		super();
	}

	public Archive(String name, FileType type, File file) {
		super();
		this.name = name;
		this.type = type;
		this.file = file;
	}
	// -------------- Getters and Setters --------------  
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FileType getType() {
		return type;
	}
	public void setType(FileType type) {
		this.type = type;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
}
