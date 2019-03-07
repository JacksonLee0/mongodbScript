package Database;

import java.util.ArrayList;

public class MongoDocument {

    private ArrayList<MongoField> fields = new ArrayList<>();

    public MongoDocument() {

    }

    public int getNumberOfFields() {
        return fields.size();
    }


    public ArrayList<MongoField> getFields() {
            return fields;
    }

    public void addToField(MongoField field){
            this.fields.add(field);
    }


    public void setFields(ArrayList<MongoField> fields) {
            this.fields = fields;
    }



}
