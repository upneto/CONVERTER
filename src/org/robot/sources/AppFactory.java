package org.robot.sources;

import org.robot.resources.model.constants.FileType;
import org.robot.resources.utils.PropertiesUtil;
import org.robot.sources.processor.IProcessor;
import org.robot.sources.processor.XLSProcessor;
import org.robot.sources.processor.XLSXProcessor;
import org.robot.sources.reader.IReader;
import org.robot.sources.reader.Reader;
import org.robot.sources.writer.IWriter;
import org.robot.sources.writer.TxtWriter;

/**
 * @author Ulisses P S Neto
 */
public class AppFactory {

	/**
	 * readers
	 * @param PROPERTIES
	 * @return
	 */
	public static IReader getReaderInstance(final PropertiesUtil PROPERTIES) {
		return new Reader(PROPERTIES);
	}
	
	/**
	 * Processors
	 * @param PROPERTIES
	 * @param TYPE
	 * @return
	 */
	public static IProcessor getProcessorInstance(final PropertiesUtil PROPERTIES, final FileType TYPE) {
		if(TYPE.equals(FileType.XLS)) {
			return new XLSProcessor(PROPERTIES);
		}
		else if(TYPE.equals(FileType.XLSX)) {
			return new XLSXProcessor(PROPERTIES);
		}			
		return null;
	}
	
	/**
	 * Writers 
	 * @param type
	 * @return
	 */
	public static IWriter getWriterInstance(final PropertiesUtil PROPERTIES, final FileType destinyType) {
		if(destinyType.equals(FileType.TXT)) {
			return new TxtWriter(PROPERTIES);
		}
		return null;
	}
}
