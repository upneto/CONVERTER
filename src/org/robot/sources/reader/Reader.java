package org.robot.sources.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.robot.resources.exceptions.ConfigurationException;
import org.robot.resources.exceptions.ReaderException;
import org.robot.resources.model.Archive;
import org.robot.resources.model.constants.FileType;
import org.robot.resources.model.constants.GeneralConstants;
import org.robot.resources.utils.PropertiesUtil;
import org.robot.sources.AbstractConverter;

/**
 * @author Ulisses P S Neto
 */
public class Reader extends AbstractConverter implements IReader {

	/**
	 * @param PROPERTIES
	 */
	public Reader(PropertiesUtil PROPERTIES) {
		super(PROPERTIES);
	}

	/**
	 * Obtem lista de arquivos
	 * @param pathType
	 * @return
	 * @throws ConfigurationException 
	 * @throws ReaderException 
	 * @throws FileNotFoundException
	 * @throws ProcessException 
	 */
	@Override
	public final List<Archive> getFiles(final String BASE_PATH) throws ConfigurationException, ReaderException {
		LOGGER.debug("     EXECUTANDO METODO: XLSReader.getFiles");
		final String LAYOUTS = this.properties.getValue(GeneralConstants.FILE.LAYOUT.NAMES);
		final String TYPES = this.properties.getValue(GeneralConstants.FILE.LAYOUT.ORIGIN_TYPES);
		final File DIRECTORY = new File(BASE_PATH + this.properties.getValue(GeneralConstants.FILE.DIRECTORY_ORIGIN));
		
		final List<Archive> FILES = new ArrayList<Archive>();
		if(LAYOUTS.contains(",")){
			String[] splitedNames = LAYOUTS.split(","); 
			for(String layout : splitedNames) {
				for(File file : DIRECTORY.listFiles()){
					String[] splitedTypes = TYPES.split(",");
					for(String type : splitedTypes){						
						if(file.getName().contains(layout) 
								&& (file.getName().endsWith(type) || file.getName().endsWith(type.toLowerCase())) ){
							FILES.add(new Archive(file.getName(), FileType.getBy(type), file));
						}
					}
				}
			}
		} else {
			for(File file : DIRECTORY.listFiles()){
				String[] splitedTypes = TYPES.split(",");
				for(String type : splitedTypes){
					if(file.getName().endsWith(type) ){
						FILES.add(new Archive(file.getName(), FileType.getBy(type), file));
					}
				}
			}
		}
		if(FILES.isEmpty()) {
			throw new ReaderException("Nenhum arquivo encontrado!");
		}
		LOGGER.debug("     FORAM ENCONTRADOS " + FILES.size() + " ARQUIVOS.");
		return FILES;
	}
}
