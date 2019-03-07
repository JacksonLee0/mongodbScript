import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class fuzzyCommandsGenerator {

    static ArrayList<String> verbFor2Char = new ArrayList<>();
    static ArrayList<String> verbFor1Char = new ArrayList<>();
    static ArrayList<String> Commands = new ArrayList<>();

    public static void main(String[] args) throws IOException, InvalidFormatException {

        String filePath = "C:\\Users\\Dongming\\collectionFiles\\excel file\\fuzzyCommand.xlsx";
        String filePath_2 = "C:\\Users\\Dongming\\collectionFiles\\excel file\\Verb.xlsx";
        File ExcelFile_1 = new File(filePath);
        File ExcelFile_2 = new File(filePath_2);

        initialize(ExcelFile_2);

        readExcelFile(ExcelFile_1);

    }

    private static void initialize(File ExcelFile_2) throws IOException, InvalidFormatException {
        Workbook workbook;
        workbook = WorkbookFactory.create(ExcelFile_2);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            String text = sheet.getRow(i).getCell(0).getStringCellValue();

            if (text.length() == 2) {
                verbFor2Char.add(text);
            }
            if (text.length() == 1) {
                verbFor1Char.add(text);
            }
        }

    }

    private static void readExcelFile(File ExcelFile) throws IOException, InvalidFormatException {
        Workbook workbook;
        workbook = WorkbookFactory.create(ExcelFile);
        CommandsGenerator(workbook);
    }


    private static void CommandsGenerator(Workbook workbook) {

        Random random = new Random();

        //read the file
        Sheet currentSheet = workbook.getSheetAt(0);
        System.out.println("The rows of sheet is " + currentSheet.getPhysicalNumberOfRows());

        for (int i = 0; i < currentSheet.getPhysicalNumberOfRows(); i++) {

            String noiseForVerb = "";
            String noiseForNoVerb = "";
            int selectionForVerb = random.nextInt(3);
            int selectionForNoVerb = random.nextInt(2);

            switch (selectionForVerb) {
                case 0:
                    noiseForVerb = "请现在帮我";
                    break;
                case 1:
                    noiseForVerb = "我现在想";
                    break;
                case 2:
                    noiseForVerb = "能不能现在帮我";
                    break;
                case 3:
                    noiseForVerb = "我现在打算";
                    break;
            }

            switch (selectionForNoVerb) {
                case 0:
                    noiseForNoVerb = "请现在帮我查一下";
                    break;
                case 1:
                    noiseForNoVerb = "我现在想要";
                    break;
                case 2:
                    noiseForNoVerb = "我现在打算看一下";
                    break;
            }


            String text = currentSheet.getRow(i).getCell(0).getStringCellValue();
            String temp = text.substring(0, 2);
            boolean flagFor2 = false;
            boolean flagFor1 = false;

            for (int j = 0; j < verbFor2Char.size(); j++) {
                if (verbFor2Char.get(j).equals(temp)) {
                    Commands.add(noiseForVerb + text);
                    flagFor2 = true;
                    break;
                }
            }

            if (flagFor2 == false) {
                for (int k = 0; k < verbFor1Char.size(); k++) {
                    if (verbFor1Char.get(k).equals(temp.substring(0, 1))) {
                        Commands.add(noiseForVerb + text);
                        flagFor1 = true;
                        flagFor2 = true;
                        break;
                    }
                }
            }

            //no matching phrase
            if (flagFor1 == false && flagFor2 == false) {
                Commands.add(noiseForNoVerb + text);
            }

        }

        //output the result
        for (String fuzzy : Commands) {
            System.out.println(fuzzy);
        }
    }


}
