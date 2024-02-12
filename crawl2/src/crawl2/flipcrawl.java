package crawl2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Date;
import java.text.SimpleDateFormat;

public class flipcrawl {

	
			public static void main(String[] args) {
				System.setProperty("webdriver.chrome.driver",
						"D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		 
				WebDriver driver = new ChromeDriver();
		 
				try {
					File file = new File("D:\\crawldata\\CrawlingOutput.xlsx");
					Workbook workbook;
					if (file.exists()) {
						FileInputStream fileIn = new FileInputStream(file);
						workbook = new XSSFWorkbook(fileIn);
					} else {
						workbook = new XSSFWorkbook();
					}
		 
					Sheet sheet;
					if (workbook.getNumberOfSheets() > 0) {
						sheet = workbook.getSheetAt(0);
					} else {
						sheet = workbook.createSheet("Employee Data");
					}
					Row headerRow = sheet.createRow(0);
					String[] headers = { "title", "price", "color", "rating", "timestamp" };
					for (int i = 0; i < headers.length; i++) {
						Cell cell = headerRow.createCell(i);
						cell.setCellValue(headers[i]);
					}
		 
					int lastRowNum = sheet.getLastRowNum();
					int rowNum = lastRowNum + 1;
		 
					String filePath = "D:\\crawldata\\flipkartinput.txt";
					BufferedReader reader = new BufferedReader(new FileReader(filePath));
					String url;
					while ((url = reader.readLine()) != null) {
		 
						driver.get(url);
		 
						Row row = sheet.createRow(rowNum);
						WebElement element = driver
								.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span"));
						String title = element.getText();
						System.out.println("Title of product: " + title);
						row.createCell(0).setCellValue(title);
		 
						WebElement element2 = driver.findElement(
								By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[4]/div[1]/div/div[1]"));
						String price = element2.getText();
						System.out.println("Price of product: " + price);
						row.createCell(1).setCellValue(price);
		 
						WebElement element3 = driver.findElement(By.xpath(
								"/html/body/div[1]/div/div[3]/div[1]/div[2]/div[8]/div[4]/div/div[2]/div/div[1]/table/tbody/tr[2]/td[2]/ul/li"));
						String color = element3.getText();
						System.out.println("Color of product: " + color);
						row.createCell(2).setCellValue(color);
		 
						WebElement element4 = driver.findElement(
								By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[2]/div/div/span[1]/div"));
						String rating = element4.getText();
						System.out.println("Rating of Product: " + rating);
						row.createCell(3).setCellValue(rating);
						
						
		                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
		                String timestamp = dateFormat.format(new Date());
		                System.out.println("TimeStamp: " + timestamp);
		                row.createCell(4).setCellValue(timestamp);
		 
						FileOutputStream fileOut = new FileOutputStream(file);
						workbook.write(fileOut);
						fileOut.close();
						workbook.close();
						System.out.println("Data written to file successfully.");
						rowNum++;
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					driver.quit();
				}
			}
		}


	


