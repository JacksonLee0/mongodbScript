package Controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ReadFromJSONFile {

    public static void main(String[] args)throws IOException{

        JSONParser parser = new JSONParser();

        try{

            Object obj = parser.parse(new FileReader("C:/Users/Dongming/Desktop/nlp_ner.musicians.txt"));

            JSONObject jsonObject = (JSONObject)obj;

            String id = (String)jsonObject.get("_id");
            String name = (String)jsonObject.get("name");
            String instrucment = (String)jsonObject.get("instrument");
            String born = (String)jsonObject.get("born");

            System.out.println("_id" + id);
            System.out.println("name"+name);
            System.out.println("instrument"+instrucment);
            System.out.println("born"+born);


        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

}
