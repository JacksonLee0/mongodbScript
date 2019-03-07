package Database;

import java.util.ArrayList;

public class MongoField {

        private String fieldName;
        private ArrayList<AlternativeTerm> alternativeTerms = new ArrayList<>();
        private String locale;
        private long lastModifiedDate = System.nanoTime();

        public MongoField(String fieldName) {
            this.fieldName = fieldName;
        }

        public void addToAlternativeTerms(String termName, String locale){
            Boolean newTermFlag = true;
            AlternativeTerm altTerm = new AlternativeTerm(termName, locale);
            for (AlternativeTerm term: alternativeTerms) {
                if (term.getName().toLowerCase().equals(termName.toLowerCase())){
                    term.addLocale(locale);
                    newTermFlag = false;
                }
            }
            if (newTermFlag == true){
                alternativeTerms.add(altTerm);
            }
        }

        public String getFieldName() {
            return fieldName;
        }

        public ArrayList<AlternativeTerm> getAlternativeTerms() {
            return alternativeTerms;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getLocale() {
            return locale;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void printDocument(){
            System.out.println("FieldName : " + fieldName);
            System.out.println("Local :" + locale);
            System.out.println("Last Modified Time: " + lastModifiedDate);
            for (AlternativeTerm term: alternativeTerms) {
                System.out.println(term + " " + locale);
            }
            System.out.println();
        }

}
