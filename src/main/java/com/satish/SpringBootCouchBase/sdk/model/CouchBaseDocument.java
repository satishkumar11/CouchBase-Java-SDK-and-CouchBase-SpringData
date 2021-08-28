package com.satish.SpringBootCouchBase.sdk.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;


@Data
@ToString
public class CouchBaseDocument {
    @Id
    private Integer id;
    private String name;
}
