package cz.francmatyas.primenumber;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

        if(isExcelFile(filePath)) {
            Sheet sheet = parseExcelFile(filePath);

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

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
        else {
            System.out.println("Invalid file type. Expected .xlsx (Entered: ."
                    + FilenameUtils.getExtension(filePath) + ")");
        }
    }

    static Sheet parseExcelFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        return workbook.getSheetAt(0);
    }

    static boolean isExcelFile(String filePath){
        return Objects.equals(FilenameUtils.getExtension(filePath), "xlsx");
    }

    static boolean isInteger(double number) {
        return number % 1 == 0;
    }

    static boolean isPrimeNumber(int number) {
        boolean result = true;
        if (number == 0 || number == 1) {
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

