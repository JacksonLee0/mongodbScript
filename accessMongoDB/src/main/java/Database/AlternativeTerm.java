package Database;

import java.util.ArrayList;

public class AlternativeTerm {

    private String name;
    private ArrayList<String> locales = new ArrayList<>();

    public AlternativeTerm(String name, String locale) {
        this.name = name;
        this.locales.add(locale);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getLocales() {
        return locales;
    }

    public void addLocale(String locale){
        this.locales.add(locale);
    }


    public void setLocales(ArrayList<String> locales) {
        this.locales = locales;
    }

}
