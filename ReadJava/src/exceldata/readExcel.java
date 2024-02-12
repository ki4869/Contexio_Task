package exceldata;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;


public class readExcel {

	public static void main(String[] args) {
		
		try {
			
          String excelfile="D:\\exceldata\\empdata.xlsx";
          FileInputStream inputStream = new FileInputStream(new String(excelfile));

         
          Workbook workbook = WorkbookFactory.create(inputStream);

          
          Sheet sheet = workbook.getSheetAt(0);
          
//         

          
          for (Row row : sheet) {
//            
              for (Cell cell : row) { 
            	  
            	  
            	  
            	  
            	  if (cell.getCellType() == CellType.STRING) {  // if cell contains string it retrieves string
                      System.out.print(cell.getStringCellValue() + "\t");
                      
                  }
            	  else if (cell.getCellType() == CellType.NUMERIC) {  //if cell contains numeric
            		  
                	  if (DateUtil.isCellDateFormatted(cell)) { //checks if it is date
                		  DataFormatter formatter = new DataFormatter();
                          String formattedValue = formatter.formatCellValue(cell);
                          System.out.print(formattedValue + "\t\t");
                		  
//                      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//                       System.out.print(dateFormat.format(cell.getDateCellValue()) + "\t\t");   
                       
                      
              }
                	  else {    //it prints as numeric value
                		  
                      System.out.print(String.valueOf((long)cell.getNumericCellValue()) + "\t");
                  } 
               }
            	  
      
                  else {    
                     // System.out.print(cell.toString() + "\t");
                  }
                                 
              }
              System.out.println();
          }

          
          workbook.close();
          inputStream.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
		
		
		
	}

}
