package org.robot;

import java.io.FileNotFoundException;
import java.rmi.server.ExportException;
import java.util.List;

import org.apache.log4j.Logger;
import org.robot.resources.exceptions.ConfigurationException;
import org.robot.resources.exceptions.ProcessorException;
import org.robot.resources.exceptions.ReaderException;
import org.robot.resources.exceptions.WriterException;
import org.robot.resources.model.Archive;
import org.robot.resources.model.Data;
import org.robot.resources.model.constants.FileType;
import org.robot.resources.model.constants.GeneralConstants;
import org.robot.resources.utils.PropertiesUtil;
import org.robot.sources.AppFactory;
import org.robot.sources.processor.IProcessor;
import org.robot.sources.reader.IReader;
import org.robot.sources.writer.IWriter;

/**
 * @author Ulisses P S Neto
 */
public class Main {
	
	/** Arquivo de log */
	private final static Logger LOGGER = Logger.getLogger(Main.class);	
	
	/**
	 * executa o processamento da funcionalidade
	 * @throws ReaderException 
	 * @throws ConfigurationException 
	 * @throws ProcessorException 
	 * @throws WriterException 
	 * @throws UploadException
	 * @throws ProcessException
	 * @throws FileNotFoundException 
	 * @throws ExportException
	 */
	private void execute(PropertiesUtil properties, String basePath) throws ConfigurationException, ReaderException, ProcessorException, WriterException {
		LOGGER.info("Inicia processamento");
		
		IReader reader = null;	 
		IProcessor processor = null;
		IWriter writer = null;
		
		LOGGER.info("Obtem arquivos do diretório origem");
		reader = AppFactory.getReaderInstance(properties);		
		final List<Archive> files = reader.getFiles(basePath);
		for(Archive fileOrigin : files){
			LOGGER.info("inicia leitura do arquivo origem: " + fileOrigin.getName());			
			processor = AppFactory.getProcessorInstance(properties, fileOrigin.getType());
			List<Data> extractedData = processor.extractData(fileOrigin, basePath);
			
			LOGGER.info("inicia criação do arquivo destino: " + fileOrigin.getName());
			FileType destinyType = FileType.getBy(properties.getValue(GeneralConstants.FILE.LAYOUT.DESTINY_TYPE));
			writer = AppFactory.getWriterInstance(properties, destinyType);
			writer.write(extractedData, basePath);
		}
	}

	/**
	 * App Bootstrap
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String basePath = args[0];
			String configFileName = null;
			PropertiesUtil properties = null;
			if(args.length > 1) {
				configFileName = args[1];
				properties = new PropertiesUtil(basePath, configFileName);
			}			
			else {
				properties = new PropertiesUtil(basePath);
			}
			
			// Inicia processamento
			new Main().execute(properties, basePath);
			
			System.exit(0);
		} catch (ConfigurationException | ReaderException | ProcessorException | WriterException e) {
			LOGGER.error(e.getMessage(), e);
			System.exit(e.getErrorCode());
		} finally {
			LOGGER.info("Fim do processamento");
		}		
	}
}
