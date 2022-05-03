package cz.francmatyas.primenumber;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrimeNumber {
    public static void main(String[] args) throws IOException
    {
        String filePath = "C:\\Users\\franc\\Desktop\\sample data.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                try
                {
                    if(!isPrimeNumber(Integer.parseInt(cell.getStringCellValue())))
                    {
                        System.out.println(cell.getStringCellValue());
                    }
                }
                catch (Exception e)
                {
                }
            }
        }
    }

    public static boolean isPrimeNumber(int number)
    {
        boolean result = false;
        if (number <= 1)
        {
            result = true;
        }
        else {
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}

