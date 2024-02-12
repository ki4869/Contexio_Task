package crawl;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class flipkartcrawl {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Product Details");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\crawldata\\flipkartinput.txt"));
            String url;
            int rowNum = 1;
            while ((url = reader.readLine()) != null) { //until url in file is null it will read
                driver.get(url);

                WebElement titleelement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span"));
                String title = titleelement.getText(); //title
                System.out.println(title);

                WebElement priceelement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div[4]/div[1]/div/div[1]"));
                String price = priceelement.getText(); //price
                System.out.println(price);
                
                List<WebElement> imageElements = driver.findElements(By.xpath("//img[contains(@class, 'q6DClP')]")); //image class
                int imageCount = imageElements.size();
                System.out.println(imageCount);

                LocalDateTime currentTime = LocalDateTime.now(); //timestamp
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                String timestamp = currentTime.format(formatter);

                Row headerRow = sheet.createRow(0);  //heading for excel file
                headerRow.createCell(0).setCellValue("Title");
                headerRow.createCell(1).setCellValue("price");
                headerRow.createCell(2).setCellValue("timestamp");
                headerRow.createCell(3).setCellValue("inputUrl");
                
                int headerCount = 1;
                int currentCell = headerRow.getLastCellNum(); // index of last cell   
                for (int i = 0; i < imageCount; i++) {   //setting header title according to img count
                    headerRow.createCell(currentCell + i).setCellValue("img_url" + headerCount);
                    headerCount++;
                }

                // Create a new row
                Row dataRow = sheet.createRow(rowNum++);  //setting value 
                dataRow.createCell(0).setCellValue(title);
                dataRow.createCell(1).setCellValue(price);
                dataRow.createCell(2).setCellValue(timestamp);
                dataRow.createCell(3).setCellValue(url);
                
                int urlrow = dataRow.getLastCellNum();        //setting image src into cell
                for (WebElement imgElement : imageElements) {
                    String imgUrl = imgElement.getAttribute("src");
                    dataRow.createCell(urlrow++).setCellValue(imgUrl);
                }
            }

            // Write data to Excel file
            FileOutputStream outputStream = new FileOutputStream("D:\\crawldata\\flipkartoutput.xlsx");
            workbook.write(outputStream);
            System.out.println("Data written successfully");

            // Close the output stream
            outputStream.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
            if (workbook != null) {
                workbook.close();
            }
            
            
        }
    }
}
