import Controller.FolderReader;
import Controller.WorkBookReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class testWorkBookReader {


    File file;
    WorkBookReader workBookReader;
    FolderReader folderReader;
    final String fileFolder = "C:/Users/Dongming/MongoDBCollection/";

    @Before
    public void setUp() throws IOException, InvalidFormatException {
        folderReader = new FolderReader(fileFolder);
        folderReader.listFileNamesFromFolder();
        new File(fileFolder+"color.xlsx");
        workBookReader = new WorkBookReader(file);
    }



    @Test
    public void testReader(){

        workBookReader.getMongoDocument();
    }

}
