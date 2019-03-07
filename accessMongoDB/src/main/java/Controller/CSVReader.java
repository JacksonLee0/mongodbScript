package Controller;

import Database.MongoDocument;
import Database.MongoField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class CSVReader {

    private File csvFile;

    private final String fieldSplitter = ",";
    private final String variationSplitter = "/";

    private String locale;

    ReadFile readFile;

    private MongoDocument mongoDocument = new MongoDocument();

    public CSVReader(File[] file, String fileName){
        for(int i = 0; i < file.length; i++){
            csvFile = file[i];
            readFile = new ReadFile(csvFile.getPath());
            openFile();
            if (i == 0){
                createFields();
            }
            locale = csvFile.getName().split(fileName+" - ")[1].replaceAll(".csv", "");
            populateFields(locale);
        }
    }


    private void openFile(){
        try {
            readFile.OpenFile();
            // readFile.getLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFields(){
        ArrayList<String> lines = readFile.getLines();
        String[] splitLines;
        MongoField field;
        for (int i = 1; i < lines.size(); i++){
            splitLines = lines.get(i).split(fieldSplitter);
            field = new MongoField(splitLines[0]);
            mongoDocument.addToField(field);
        }
    }


    public void populateFields(String locale){
        ArrayList<String> lines = readFile.getLines();
        String[] lineList;
        Set<String> alternativeTerms;
        String fieldToPopulate;
        MongoField mongoFieldToAdd;

        for(int j = 1 ; j < lines.size(); j++){
            lineList = TextCleaner.removeQuotes(lines.get(j)).split(fieldSplitter);
            fieldToPopulate = lineList[0];
            System.out.println("Field : " + fieldToPopulate);
            alternativeTerms = new HashSet<>();
            for(int i = 1; i< lineList.length; i++){
                for(String altTerm : lineList[i].split(variationSplitter)){
                    System.out.println("ADDING TERM " + altTerm.trim());
                    if(!altTerm.equals("")){
                        alternativeTerms.add(altTerm.trim());
                    }
                }
            }
            for(int i = 0; i<mongoDocument.getNumberOfFields(); i++){
                mongoFieldToAdd =mongoDocument.getFields().get(i);
                if(fieldToPopulate.equals(mongoFieldToAdd.getFieldName())){
                    for(String alternativeTerm: alternativeTerms){
                        mongoFieldToAdd.addToAlternativeTerms(alternativeTerm.trim(),locale);
                    }//this loop lists through all alternative terms
                }
                mongoFieldToAdd.setLocale("global");
            }// loops through each field in the document
        }// this loop loops through each line in the file

    }

    public MongoDocument getMongoDocument() {
        return mongoDocument;
    }

}
