package cz.francmatyas.primenumber;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrimeNumber {
    public static void main(String[] args) throws IOException
    {
        //System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("Paste the full file path into .xlsx document.");
        System.out.flush();
        Scanner scanner = new Scanner(System.in);

        //String filePath = scanner.nextLine();

        String filePath = "C:\\Users\\franc\\Desktop\\sample data.xlsx";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row cells : sheet) {
            XSSFRow row = (XSSFRow) cells;
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) cellIterator.next();

                if (cell.getCellType() != CellType.BLANK) {
                    new Thread(() -> {
                        try {
                            double nextNumber = Double.parseDouble(cell.getStringCellValue());
                            if (isInteger(nextNumber) && nextNumber > 0) {
                                if (isPrimeNumber((int) nextNumber)) {
                                    System.out.println(nextNumber);
                                }
                            }
                        } catch (Exception ignored) {
                        }
                    }).start();
                }
            }
        }
    }

    static boolean isInteger(double number)
    {
        return number % 1 == 0;
    }

    static boolean isPrimeNumber(int number)
    {
        boolean result = true;
        if (number <= 1)
        {
            result = false;
        }
        else {
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}

