package com.satish.SpringBootCouchBase.springdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchBaseSpringDataConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.url}")
    private String COUCHBASE_URL;

    @Value("${couchbase.userName}")
    private String COUCHBASE_SECURITY_USER_NAME;

    @Value("${couchbase.userPassword}")
    private String COUCHBASE_SECURITY_USER_PASS;

    @Value("${couchbase.bucket1}")
    private String BUCKET_ONE;

    @Override
    public String getConnectionString() {
        return COUCHBASE_URL;
    }

    @Override
    public String getUserName() {
        return COUCHBASE_SECURITY_USER_NAME;
    }

    @Override
    public String getPassword() {
        return COUCHBASE_SECURITY_USER_PASS;
    }

    @Override
    public String getBucketName() {
        return BUCKET_ONE;
    }
}
