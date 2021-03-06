package mastersreserach.arangodb;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by hashcode on 2017/01/23.
 */
public class ArangoDBMainTest {
    private   ArangoDB arangoDB;
    @Before
    public void setUp() throws Exception {
        arangoDB = new ArangoDB.Builder()
                .host("10.47.3.51")
                .port(1026)
                .user("root")
                .password("")
                .build();


    }

    @Test
    public void addRecord() throws Exception {
        System.out.println(" Adding Record");
        String dbName = "NewDB";

        String collectionName = "firstCollection";
        try {
            CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
            System.out.println("Collection created: " + myArangoCollection.getName());
        } catch (ArangoDBException e) {
            System.err.println("Failed to create collection: " + collectionName + "; " + e.getMessage());
        }
        try {
            arangoDB.createDatabase(dbName);
            System.out.println("Database created: " + dbName);
        } catch (ArangoDBException e) {
            System.err.println("Failed to create database: " + dbName + "; " + e.getMessage());
        }

        BaseDocument myObject = new BaseDocument();
        myObject.setKey("myKey");
        myObject.addAttribute("a", "Foo");
        myObject.addAttribute("b", 42);
        try {
            arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);
            System.out.println("Document created");
        } catch (ArangoDBException e) {
            System.err.println("Failed to create document. " + e.getMessage());
        }

    }
}
