package com.satish.SpringBootCouchBase.sdk.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satish.SpringBootCouchBase.sdk.model.CouchBaseDocument;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CouchBaseSDKService {

    private static final int LIMIT = 2;
    private final Cluster cluster;

    @Value("${couchbase.bucket1}")
    private String BUCKET_ONE;

    @Value("${couchbase.bucket2}")
    private String BUCKET_TWO;

    @Autowired
    public CouchBaseSDKService(Cluster cluster) {
        this.cluster = cluster;
    }

    public void moveDocument() throws Exception {
        {
            try {

                QueryResult queryResult = cluster.query("select * from " + BUCKET_ONE + " limit " + LIMIT);
                List<JsonObject> allDocs = queryResult.rowsAsObject();

                for (JsonObject doc : allDocs) {

                    JsonObject data = (JsonObject) doc.get(BUCKET_ONE);
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    CouchBaseDocument couchBaseDocument = objectMapper.readValue(data.toString(), CouchBaseDocument.class);
                    log.info("couchBaseDocument : " + couchBaseDocument);

                    addToBucket(cluster, data);
                    deleteFromBucket(cluster, data);

                }
            } catch (Exception e) {
                log.error("moveDocument : failed to move document, Err! ", e);
                throw new Exception("moveDocument : failed to move document, Err! ");
            }
        }
    }

    public void addToBucket(Cluster cluster, JsonObject data) throws ApplicationException {
        try {
            Bucket bucket = cluster.bucket(BUCKET_TWO);
            Collection collection = bucket.defaultCollection();
            data.put("createdAt", String.valueOf(new Date()));
            collection.upsert(data.get("id").toString(), data);
        } catch (Exception e) {
            log.error("addToBucket1: failed to add to bucket1", e);
        }
    }

    public void deleteFromBucket(Cluster cluster, JsonObject data) throws ApplicationException {
        try {
            String deleteQuery = "delete from " + BUCKET_ONE + " where " + BUCKET_ONE + ".name = '" + data.get("name") + "'";
            cluster.query(deleteQuery);
        } catch (Exception e) {
            log.error("removeFromBucket2: failed to remove from bucket2", e);
        }
    }
}
