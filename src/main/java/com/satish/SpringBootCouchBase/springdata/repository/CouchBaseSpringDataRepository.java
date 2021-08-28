package com.satish.SpringBootCouchBase.springdata.repository;

import com.satish.SpringBootCouchBase.springdata.model.CouchBaseDocument;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouchBaseSpringDataRepository extends CouchbaseRepository<CouchBaseDocument, Integer> {
}
