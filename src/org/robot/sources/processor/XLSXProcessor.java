package org.robot.sources.processor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.robot.resources.exceptions.ProcessorException;
import org.robot.resources.model.Archive;
import org.robot.resources.model.Data;
import org.robot.resources.model.Field;
import org.robot.resources.model.constants.Direction;
import org.robot.resources.model.constants.FieldType;
import org.robot.resources.model.constants.GeneralConstants;
import org.robot.resources.utils.FormatUtil;
import org.robot.resources.utils.PropertiesUtil;
import org.robot.sources.AbstractConverter;

/**
 * @author Ulisses P S Neto
 */
public class XLSXProcessor extends AbstractConverter implements IProcessor {

	/**
	 * @param PROPERTIES
	 */
	public XLSXProcessor(PropertiesUtil PROPERTIES) {
		super(PROPERTIES);
	}

	@SuppressWarnings("resource")
	@Override
	public List<Data> extractData(final Archive ARCHIVE, final String NAME) throws ProcessorException {
		LOGGER.debug("     EXECUTANDO METODO: XLSXProcessor.extractData");
		List<Data> rows = new ArrayList<>();
		
		// salvar arquivo excel no formato 97-2003
		FileInputStream stream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		
		try {
			stream = new FileInputStream(ARCHIVE.getFile());
			//Workbook workbook = new HSSFWorkbook(stream);
			workbook = new XSSFWorkbook(stream);
			sheet = workbook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			throw new ProcessorException(e);
		} catch (IOException e) {
			throw new ProcessorException(e);
		}
		
		List<Row> list = IteratorUtils.toList(sheet.iterator());
			
		int rowIndex = 0;			
		try {
			int rowStart   = Integer.parseInt( super.properties.getValue(GeneralConstants.FILE.LAYOUT.PROP_BASE + NAME + ".start"));
			int collNumber = Integer.parseInt( super.properties.getValue(GeneralConstants.FILE.LAYOUT.PROP_BASE + NAME + ".size"));
			
			for (Row row : list) {
				
				Data data = new Data();
				
				if(row.getCell(0) == null || rowStart > rowIndex) {
					continue;
				}
				
				for (int i = 0; i < collNumber; i++) {
					// Parametros
					String[] collunParam = super.properties.getValue(GeneralConstants.FILE.LAYOUT.PROP_BASE + NAME + "." + i).split(",");
					int cellIndex = Integer.parseInt(collunParam[0]);
					FieldType type = FieldType.getBy(collunParam[1]);
					int size = Integer.parseInt(collunParam[2]);
					
					String charDefault = type.equals(FieldType.CHAR) ? " " : "0";
					Direction directionDefault = type.equals(FieldType.CHAR) ? Direction.RIGHT : Direction.LEFT;
					
					String formatedField = FormatUtil.format(row.getCell(cellIndex),  charDefault, size, directionDefault);
					
					data.addCollumn(new Field(formatedField, type, size));
				}
				
				rows.add(data);
	
				rowIndex++;
			};
		} catch (Exception e) {
			throw new ProcessorException(" ->-> ERRO NA LINHA: " + rowIndex + 1, e);
		}

		try {
			workbook.close();
		} catch (IOException e) {
			throw new ProcessorException(e);
		}
		
		LOGGER.debug("     FIM DA EXECUCAO COM SUCESSO");
		return rows;
	}

}
