package org.robot.sources.reader;

import java.io.FileNotFoundException;
import java.util.List;

import org.robot.resources.exceptions.ConfigurationException;
import org.robot.resources.exceptions.ReaderException;
import org.robot.resources.model.Archive;

/**
 * @author Ulisses P S Neto
 */
public interface IReader {

	/**
	 * Obtem lista de arquivos
	 * @param BASE_PATH
	 * @return
	 * @throws ConfigurationException 
	 * @throws ReaderException 
	 * @throws FileNotFoundException
	 * @throws ProcessException 
	 */
	List<Archive> getFiles(final String BASE_PATH) throws ConfigurationException, ReaderException;

	
}
