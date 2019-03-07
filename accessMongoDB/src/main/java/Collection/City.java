package Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class City {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        String filePath = "C:\\Users\\Dongming\\collectionFiles\\updating collection\\City\\Popular_Cities.xlsx";
        File excelFile = new File(filePath);
        readCityExcelFile(excelFile);
    }


    public static void readCityExcelFile(File ExcelFile) throws IOException, InvalidFormatException {

        Workbook workbook;
        workbook = WorkbookFactory.create(ExcelFile);

        extractEnName(workbook);


    }

    public static void extractEnName(Workbook workbook){

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
