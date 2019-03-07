package Database;

public class MongoCollection {
    private String collectionName;

    public MongoCollection() {

    }

    //Getters
    public String getCollectionName() {
        return collectionName;
    }

    //Setters
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
