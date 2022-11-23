package org.robot.sources.writer;

import java.util.List;

import org.robot.resources.exceptions.WriterException;
import org.robot.resources.model.Data;

public interface IWriter {

	/**
	 * @param ROWS
	 * @param FILE_NAME
	 * @throws WriterException
	 */
	void write(List<Data> ROWS, String FILE_NAME) throws WriterException;

}
