package com.satish.SpringBootCouchBase.sdk.config;

import com.couchbase.client.core.env.Authenticator;
import com.couchbase.client.core.env.PasswordAuthenticator;
import com.couchbase.client.core.env.SeedNode;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class CouchBaseSDKConfig {

    @Value("${couchbase.url}")
    private String COUCHBASE_URL;

    @Value("${couchbase.userName}")
    private String COUCHBASE_SECURITY_USER_NAME;

    @Value("${couchbase.userPassword}")
    private String COUCHBASE_SECURITY_USER_PASS;

    @Bean
    public Cluster couchBaseConnect() {
        log.info("connecting to couchbase");
        Set<SeedNode> seedNodes = new HashSet<>(Arrays.asList(SeedNode.create(COUCHBASE_URL)));
        Authenticator authenticator = PasswordAuthenticator.create(COUCHBASE_SECURITY_USER_NAME, COUCHBASE_SECURITY_USER_PASS);
        ClusterOptions options = ClusterOptions.clusterOptions(authenticator);
        Cluster cluster = Cluster.connect(seedNodes, options);
        log.info("connected to couchbase");
        return cluster;
    }
}
