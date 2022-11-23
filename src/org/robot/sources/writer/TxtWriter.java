package org.robot.sources.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.robot.resources.exceptions.WriterException;
import org.robot.resources.model.Data;
import org.robot.resources.model.Field;
import org.robot.resources.model.constants.GeneralConstants;
import org.robot.resources.utils.PropertiesUtil;
import org.robot.sources.AbstractConverter;

/**
 * @author Ulisses P S Neto
 */
public class TxtWriter extends AbstractConverter implements IWriter {

	/**
	 * @param PROPERTIES
	 */
	public TxtWriter(PropertiesUtil PROPERTIES) {
		super(PROPERTIES);
	}

	/**
	 * @param ROWS
	 * @param FILE_NAME
	 * @throws WriterException
	 */
	@Override
	public void write(final List<Data> ROWS, final String FILE_NAME) throws WriterException {
		try {
			final String path = super.properties.getValue(GeneralConstants.FILE.DIRECTORY_DESTINY);
			new File(path).mkdirs();
			
			final String nomeTratado = FILE_NAME.replace(".xls", "").replace(".xlsx", "");
			final String fileName = path + (path.endsWith(GeneralConstants.PATH_SEPARATOR) 
							? "" 
							: GeneralConstants.PATH_SEPARATOR ) + nomeTratado + ".txt";
			
			FileWriter arq = new FileWriter(fileName);
			PrintWriter filePrinter = new PrintWriter(arq);
	
			for (Data row : ROWS) {
				StringBuilder stringRow = new StringBuilder();
				for(Field collumn : row.getCollumns()) {					
					stringRow.append(collumn.getValue());
				}
				filePrinter.println(stringRow.toString());
			}
	
			arq.close();
		} catch (Exception e) {
			throw new WriterException(e);
		}
	}
}
