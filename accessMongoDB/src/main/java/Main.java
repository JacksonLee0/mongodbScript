import Controller.CSVReader;
import Controller.FolderReader;
import Controller.WorkBookReader;
import Database.MongoCollection;
import Database.MongoDB;
import com.sun.media.sound.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    //initialise

    public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {

        //create the folderLocation
        String folderLocation = "C:\\Users\\Dongming\\collectionFiles\\excel file\\";

        FolderReader folderReader; // = new FolderReader(folderLocation);
        MongoCollection mongoCollection = new MongoCollection();
        System.out.println("Connecting to database...");
        MongoDB database = new MongoDB();
        database.connectToDB();

        String choice;
        String collectionName;
        CSVReader csvReader;
        while (true) {
            System.out.println("\nPlease enter your next step:" +
                    "\n1: create collection\n2: drop collection\n3: create and add collection (XLSX)" +
                    "\n4: create and add collection (CSV)\n5: exit");
            choice = reader.nextLine();

            switch (choice) {
                case "1": {
                    collectionName = enterCollection();
                    mongoCollection.setCollectionName(collectionName);
                    database.createCollection(collectionName);

                    break;

                }
                case "2": {
                    collectionName = enterCollection();
                    mongoCollection.setCollectionName(collectionName);
                    database.dropCollection(collectionName);

                    break;
                }
                case "3": {

                    //folderLocation += "excel file/";
                    folderReader = new FolderReader(folderLocation);

                    //print the name of files in folder
                    folderReader.listFileNamesFromFolder();

                    collectionName = enterCollection();
                    mongoCollection.setCollectionName(collectionName);
                    File excelFile = new File(folderLocation + collectionName + ".xlsx");
                    try {
                        WorkBookReader workBookReader = new WorkBookReader(excelFile);
                        database.createAndAddDocuments(workBookReader.getMongoDocument(), collectionName);
                    } catch (InvalidFormatException e) {
                        e.printStackTrace();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "4":{

//                    collectionName = enterCollection();
//
//                    folderLocation += "csv file/"+collectionName+"/";
//                    folderReader = new FolderReader(folderLocation);
//
//                    //print the name of files in folder
//                    System.out.println(folderLocation);
//                    folderReader.listFileNamesFromFolder();
//
//                    csvReader = new CSVReader(folderReader.getFilesInFolder(),collectionName);
//                    database.createAndAddDocuments(csvReader.getMongoDocument(), collectionName);
                }
                case "5": {
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("Error, please enter again");
                    break;
                }
            }
        }
    }



    //enter the collection name and return it
    public static String enterCollection() {
        Scanner reader = new Scanner(System.in);
        String collectionName;
        System.out.println("Please enter the name of collection: ");
        collectionName = reader.nextLine();
        return collectionName;
    }
}



