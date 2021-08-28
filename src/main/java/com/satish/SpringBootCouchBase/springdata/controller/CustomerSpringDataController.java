package com.satish.SpringBootCouchBase.springdata.controller;

import com.satish.SpringBootCouchBase.springdata.model.CouchBaseDocument;
import com.satish.SpringBootCouchBase.springdata.repository.CouchBaseSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/couchbase/spring/data")
public class CustomerSpringDataController {

    @Autowired
    private CouchBaseSpringDataRepository couchBaseSpringDataRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody CouchBaseDocument customer) {
        couchBaseSpringDataRepository.save(customer);
        return "saved";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CouchBaseDocument> get() {
        return couchBaseSpringDataRepository.findAll();
    }
}
