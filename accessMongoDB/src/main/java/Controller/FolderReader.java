package Controller;

import java.io.File;

public class FolderReader {

    private String folderLocation;
    private File folder;
    private String folderName;

    private File[] filesInFolder;

    public FolderReader(String folderLocation) {
        this.folderLocation = folderLocation;
        openFolder();
        folderName = folder.getName();
        populateFileArrayFromFolder(folder);
    }

    private void openFolder(){
        try {
            folder = new File(folderLocation);
        }
        catch (Exception e){
            System.out.println("Folder doesn't exist");
        }
    }

    private void populateFileArrayFromFolder(File folder){
        filesInFolder = folder.listFiles();
        if (folder.listFiles() == null) {
            System.out.println("There are no files contained in " + folderName);
        }
    }

    public void listFileNamesFromFolder(){
        if (filesInFolder !=null){
            System.out.println("These are the files contained in " + folderName + "\n");
            for (File file: filesInFolder) {
                if (file.isFile()){
                    System.out.println("File : " + file.getName());
                }
            }
            System.out.println();
        }
    }

    public String getFolderName() {
        return folderName;
    }

    public File[] getFilesInFolder() {
        return filesInFolder;
    }

}
