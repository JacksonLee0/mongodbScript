package Controller;

import Database.MongoDocument;
import Database.MongoField;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WorkBookReader {

    private Workbook workbook;

    private String locale;
    private MongoDocument mongoDocument = new MongoDocument();
    private Set<String> alternativeTerms;

    public  WorkBookReader(File ExcelFile) throws IOException, InvalidFormatException{

       workbook = WorkbookFactory.create(ExcelFile);
       createMongoDocement(workbook);
    }

    private void createMongoDocement(Workbook workbook) {
        setFieldName(workbook);
        loopEachSheet(workbook);
    }

    //go through and loop each sheets in the file
    private void loopEachSheet(Workbook workbook) {
        Sheet currentSheet;
        for(int i = 0;i<workbook.getNumberOfSheets();i++){
            currentSheet =workbook.getSheetAt(i);
            locale = currentSheet.getSheetName();

            //
            System.out.println(locale);
            loopEachRows(currentSheet);
        }
    }

    //go through and loop each rows in the file
    private void loopEachRows(Sheet currentSheet) {
        Row currentRow;
        for(int i = 1 ; i < currentSheet.getPhysicalNumberOfRows(); i++){
            currentRow = currentSheet.getRow(i);
            if(currentRow.getCell(0)!=null){
                alternativeTerms = new HashSet<>();
                loopEachCell(currentSheet.getRow(i));
            }else{
                break;
            }
            loopThroughDocumentField(currentRow.getCell(0).getStringCellValue());
        }
    }


    //go through and loop each cell of rows in the file
    private void loopEachCell(Row currentRow) {
        Cell currentCell;
        for(int i = 1; i < currentRow.getPhysicalNumberOfCells();i++){
            currentCell = currentRow.getCell(i);
            formatAlternateTerms(currentCell.getStringCellValue());
        }
    }



    private void loopThroughDocumentField(String fieldToPopulate) {
        MongoField mongoFieldToAdd;
        for(int i = 0; i < mongoDocument.getNumberOfFields(); i++){
            mongoFieldToAdd =  mongoDocument.getFields().get(i);
            if(fieldToPopulate.equals(mongoFieldToAdd.getFieldName())){
                System.out.println("FIELD " + fieldToPopulate);
                for (String alternativeTerm: alternativeTerms) {
                    mongoFieldToAdd.addToAlternativeTerms(alternativeTerm, locale);
                }//this loop lists through all alternative terms
            }
            //mongoFieldToAdd.setLocale("global");
        }
    }


    //add each value
    private void formatAlternateTerms(String altTermRow) {
        String formatTermRow = altTermRow.trim().replace("\n","");
        for(String altTerm: formatTermRow.split("/")){
            System.out.println("ROW OF TERMS : "+ altTermRow);
            if(!altTerm.equals(""))
                alternativeTerms.add(altTerm.trim());
        }
    }


    //set the file name
    private void setFieldName(Workbook workbook) {
        Sheet currentSheet = workbook.getSheetAt(0);
        Row currentRow;

        for(int i = 1;i<currentSheet.getPhysicalNumberOfRows();i++){
            currentRow = currentSheet.getRow(i);
            if(currentRow.getCell(0)!=null){
                addField(currentRow.getCell(0).getStringCellValue());
            }else{
                break;
            }
        }
    }

    //create and add field to mongo document
    private void addField(String fieldName){
        MongoField field = new MongoField(fieldName);
        mongoDocument.addToField(field);

        System.out.println("FieldName: " + fieldName);
    }


    public MongoDocument getMongoDocument() {
        return mongoDocument;
    }

}
