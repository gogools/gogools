package com.gogools.utils.file;

import org.apache.poi.ss.usermodel.Row;


public class ExcelUtil {

	public static Boolean hasNullInAnyColumns( Row row ) {
		
		short minColIx = row.getFirstCellNum();
		short maxColIx = row.getLastCellNum();
		
		for (short colIx = minColIx; colIx < maxColIx; colIx++) {
			
			if (row.getCell(colIx) == null) {
				
				return true;
			}

		}
		
		return false;
	}
}
