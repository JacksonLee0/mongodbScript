package Controller;

import java.util.ArrayList;

public class TextCleaner {

    public static String convertAlts(String _text){
        if (_text.contains("&")){
            _text = _text.replace("&", "and");
        }
        if (_text.contains("@")){
            _text = _text.replace("@", "at");
        }
        return _text;
    }

    public static String removePunctuation(String _text){
        String pattern = "\\pP";
        return _text.replaceAll(pattern,"");
    }

    public static String removeQuotes(String _text){
        return  _text.replaceAll("\"", "");
    }

    public static String removeExtraWhiteSpace(String _text){
        return _text.replaceAll("  ", " ");
    }

    public static ArrayList<String> removeWhiteSpace(ArrayList<String> _text){
        ArrayList<String> _textWithoutSpace = new ArrayList<String>();
        for (String line: _text) {
            _textWithoutSpace.add(line.replaceAll(" ", ""));
        }
        return _textWithoutSpace;
    }

    public static String textCleanUp(String text){
        text = text.toLowerCase();
        text = convertAlts(text);
        text = removePunctuation(text);
        text = removeExtraWhiteSpace(text);
        return text;
    }

    public static ArrayList<String> textCleanUp(ArrayList<String> textList){
        ArrayList<String> cleanList = new ArrayList<String>();
        for (String line: textList) {
            cleanList.add(textCleanUp(line));
        }
        return cleanList;
    }

}
