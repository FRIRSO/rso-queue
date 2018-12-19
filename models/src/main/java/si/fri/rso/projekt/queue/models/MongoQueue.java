package si.fri.rso.projekt.queue.models;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class MongoQueue {


    private final String DBUser       = "root";
    private final String DBPassword   = "13tpxnxJTwUScc3V";
    private final String DBName       = "db-queue";
    private final String DBCollection = "queues";

    private MongoClient connectDB() {
        MongoClientURI uri = new MongoClientURI("mongodb://"+ DBUser +":"+ DBPassword +"@gsascluster-shard-00-00-ocnkx.azure.mongodb.net:27017," +
                "gsascluster-shard-00-01-ocnkx.azure.mongodb.net:27017,gsascluster-shard-00-02-ocnkx.azure.mongodb.net:27017/test?" +
                "ssl=true&replicaSet=gsasCluster-shard-0&authSource=admin&retryWrites=true");

        return new MongoClient(uri);
    }

    public List<Queue> getAllQueues() {
        MongoClient client = connectDB();

        MongoDatabase db = client.getDatabase(DBName);

        MongoCollection<Document> queueCollection = db.getCollection(DBCollection);

        List<Queue> results = new ArrayList<>();

        for(Document curr : queueCollection.find()) {

            Queue queue = new Queue(curr.getInteger("delivererID"),
                                    curr.getInteger("queueID"),
                                    curr.getInteger("orderID"));

            results.add(queue);
        }

        return results;
    }

    public Queue getQueue(Integer queueID) {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> bc = db.getCollection(DBCollection);

        Bson filter = Filters.eq("delivererID", queueID);

        Document result = bc.find(filter).first();

        if(result == null) {
            return null;
        }


        return new Queue(result.getInteger("delivererID"),
                result.getInteger("queueID"),
                result.getInteger("orderID"));
    }
}
