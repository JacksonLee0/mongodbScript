package Controller;

import java.io.*;
import java.util.ArrayList;

public class ReadFile {

    private String path;
    private ArrayList<String> lines;
    private File file;

    public ReadFile(String file_path){
        path = file_path;
    }

    public void OpenStream()throws IOException{
        FileInputStream inputStream;
        BufferedReader bufferedReader;

        inputStream = new FileInputStream(path);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        lines = new ArrayList<String>();

        while ((line = bufferedReader.readLine()) != null){
            lines.add(line.toLowerCase());
        }

        bufferedReader.close();
        inputStream.close();
    }


    public void OpenFile()throws IOException {

        File dataBaseData = new File(path);
        FileReader fileReader;
        BufferedReader bufferedReader;

        fileReader = new FileReader(dataBaseData);
        bufferedReader = new BufferedReader(fileReader);

        String line;
        lines = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null){
            lines.add(line);
        }

        bufferedReader.close();
        fileReader.close();
    }

    public ArrayList<String> getLines(){
        return lines;
    }

    public File getFile() {
        return file;
    }

    public void printLines(){
        for (String line: lines) {
            System.out.println(line);
        }
    }
}
