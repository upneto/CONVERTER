package org.robot.sources.processor;

import java.util.List;

import org.robot.resources.exceptions.ProcessorException;
import org.robot.resources.model.Archive;
import org.robot.resources.model.Data;

/**
 * @author Ulisses P S Neto
 */
public interface IProcessor {

	List<Data> extractData(final Archive ARCHIVE, final String NAME) throws ProcessorException;
}
