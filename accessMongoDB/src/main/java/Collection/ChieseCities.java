package Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChieseCities {

    public static void main(String[] args) throws IOException, InvalidFormatException {

        String filePath = "C:\\Users\\Dongming\\collectionFiles\\updating collection\\City\\Chinese_Cities.xlsx";
        File file = new File(filePath);

        FileReader(file);


    }


    public static void FileReader(File file) throws IOException, InvalidFormatException {

        Workbook workbook;
        workbook = WorkbookFactory.create(file);

        ChineseCitiesExtractor(workbook);

    }


    public static void ChineseCitiesExtractor(Workbook workbook) {

        ArrayList<String> NameList = new ArrayList<>();

        Sheet currentSheet = workbook.getSheetAt(0);
        for (int i = 0; i < currentSheet.getPhysicalNumberOfRows(); i++) {

            //System.out.println("The rows of sheet is " + currentSheet.getPhysicalNumberOfRows());

            String text = currentSheet.getRow(i).getCell(0).getStringCellValue();

            String Name = "";

            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(j) == ',') {
                    NameList.add(Name);
                    break;
                } else {
                    Name += text.charAt(j);
                }// loop char of string to extract name
            }// loop each row of file

        }



        for (String name : NameList) {
            System.out.println('"' + name + '"' + ",");

        }

    }
}
