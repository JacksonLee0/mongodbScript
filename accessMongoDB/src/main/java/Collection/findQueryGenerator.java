package Collection;

import Controller.FolderReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class findQueryGenerator {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InvalidFormatException {

        String folderPath = "C:\\Users\\Dongming\\collectionFiles\\updating collection\\findQueryFile\\";
        String fileName;

        FolderReader folderReader = new FolderReader(folderPath);
        folderReader.listFileNamesFromFolder();
        System.out.println("enter the file name:");
        fileName =  scanner.nextLine();

        File excelFile = new File(folderPath+fileName+".xlsx");
        readExcelFile(excelFile);

    }


    private static void readExcelFile(File ExcelFile) throws IOException, InvalidFormatException {
        Workbook workbook;
        workbook = WorkbookFactory.create(ExcelFile);
        extractEnName(workbook);
    }

    private static void extractEnName(Workbook workbook) {

        ArrayList<String> EnNameList = new ArrayList<>();

        Sheet currentSheet =  workbook.getSheetAt(0);
        for(int i = 0; i < currentSheet.getPhysicalNumberOfRows() ;i++){

            //
            System.out.println("The rows of sheet is "+currentSheet.getPhysicalNumberOfRows());

            String text = currentSheet.getRow(i).getCell(0).getStringCellValue();
            //

            String EnName = "";
            for(int j = 0; j <text.length();j++){

                if(text.charAt(j) == '='){
                    EnNameList.add(EnName);
                }else{
                    EnName+=text.charAt(j);
                }

            }// loop char of string to extract city name

        }// loop each row of file

        for (String Name: EnNameList) {
            System.out.println('"'+Name+'"'+",");

        }
    }
}
