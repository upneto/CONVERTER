package org.robot.resources.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.robot.resources.model.constants.Direction;

/**
 * @author Ulisses P S Neto
 */
public class FormatUtil {

	/**
	 * Format
	 * @param cell
	 * @param charr
	 * @param size
	 * @param direction
	 * @return
	 */
	public static String format(Cell cell, String charr, int size, Direction direction) {

		if (cell.getCellType().equals(CellType.NUMERIC)){	
			return format(cell.toString(), size);
		} 
		
		String field = cell.toString();
		
		field.replaceAll("[^\\x0A\\x0D\\x20-\\x7E]", "");

		if (field.toUpperCase().contains("NÃO POSSUI") || field.toUpperCase().contains("ESPELHO") || field.toUpperCase().contains("NÃO ENCONTRADO")) {
			return " " + buildChar(charr, size, direction, charr);
		}

		return buildChar(charr, size, direction, field.replace("%", " "));

	}
	
	/**
	 * Format
	 * @param field
	 * @param tipoDado
	 * @param size
	 * @param direction
	 * @return
	 */
	public static String format(String field, String tipoDado, int size, Direction direction) {
		
		field.replaceAll("[^\\x0A\\x0D\\x20-\\x7E]", "");

		if (field.toUpperCase().contains("NÃO POSSUI") || field.toUpperCase().contains("ESPELHO") || field.toUpperCase().contains("NÃO ENCONTRADO")) {
			field = tipoDado;
			return " " + buildChar(tipoDado, size, direction, field);
		}

		return buildChar(tipoDado, size, direction, field);

	}
	
	/**
	 * Format
	 * @param cell
	 * @return
	 */
	public static String formatarfield(Cell cell) {
		if(cell.toString().trim().isEmpty()) {
			return "";
		}
		return new BigDecimal(cell.getNumericCellValue()).toString();
	}
	
	/**
	 * Format
	 * @param field
	 * @param size
	 * @return
	 */
    public static String format(String field, int size) {
		
		try {
			if(field == null || field.isEmpty()) {
				return " " + buildChar("0", size, Direction.LEFT, "");
			} else if (!field.matches("-?\\d+(\\.\\d+)?")) {
				return " " + buildChar("0", size, Direction.LEFT, "");
			}
		
			String fieldFormat = "";
			String sinal = " ";
			if(field != null && !field.isEmpty()) {
				String var = field.replace(",", ".");
				sinal = var.startsWith("-") ? "-" : " ";
				String valueMult = new BigDecimal( var.replace("-", "") ).multiply(new BigDecimal(100)).toString();
				String fieldBase = valueMult.substring(0, valueMult.indexOf(".")); 
				String fieldDeci = valueMult.substring(valueMult.indexOf(".") + 1, valueMult.length()); 
				fieldFormat = fieldBase + ( fieldDeci.length() > 2 ? fieldDeci.substring(0, 2) : buildChar("0", 2, Direction.RIGHT, fieldDeci));  		
			}
			/*
				fieldFormat = new BigDecimal(var).abs().round(new MathContext(2, RoundingMode.DOWN)).toString();
			}
			*/
			return sinal + buildChar("0", size, Direction.LEFT, fieldFormat.replace(".", ""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * Format
     * @param field
     * @param size
     * @return
     */
	public static String formatarfieldPerf(String field, int size) {
		
		try {
			if(field == null || field.isEmpty()) {
				return buildChar(" ", size, Direction.RIGHT, "");
			} else if (!field.matches("-?\\d+(\\.\\d+)?")) {
				return buildChar(" ", size, Direction.RIGHT, field.replace("%", " "));
			}
		
			String fieldFormat = "";
			String sinal = " ";
			if(field != null && !field.isEmpty()) {
				String var = field.replace(",", ".");
				sinal = var.startsWith("-") ? "-" : " ";
				fieldFormat = new BigDecimal(var).abs().round(new MathContext(2, RoundingMode.DOWN)).toString();
			}
			return sinal + buildChar(" ", size, Direction.LEFT, fieldFormat.replace(".", ""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param charr
	 * @param size
	 * @param direction
	 * @param field
	 * @return
	 */
	private static String buildChar(String charr, int size, Direction direction, String field) {
		StringBuffer sb = new StringBuffer();		
		if(field.length() > size) {
			sb.append(field.substring(field.length() - size, field.length()));
		} else {
			sb.append(field);
		}
		if (direction.equals(Direction.LEFT)) {
			for (int i = sb.length(); i < size; i++) {
				sb.insert(0, charr);
			}
		} else {
			for (int i = sb.length(); i < size; i++) {
				sb.append(charr);
			}
		}
		return sb.toString();
	}
}
