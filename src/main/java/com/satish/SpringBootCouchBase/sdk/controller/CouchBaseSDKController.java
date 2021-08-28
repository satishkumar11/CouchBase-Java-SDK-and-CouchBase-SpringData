package com.satish.SpringBootCouchBase.sdk.controller;

import com.satish.SpringBootCouchBase.sdk.service.CouchBaseSDKService;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.portable.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/couchbase/sdk")
public class CouchBaseSDKController {

    private final CouchBaseSDKService couchBaseSDKService;

    @Autowired
    public CouchBaseSDKController(CouchBaseSDKService couchBaseSDKService) {
        this.couchBaseSDKService = couchBaseSDKService;
    }

    @GetMapping("/move")
    public void executeBucketOperation() throws ApplicationException {
        try {
            couchBaseSDKService.moveDocument();
        } catch (Exception e) {
            log.error("executeFailedOfflineTransaction : failed to execute offline transaction, Err! ", e);
        }
    }
}
