//package org.voduybao.tools.dao.entities.searchdocument;
//
//import jakarta.persistence.Id;
//import lombok.*;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//@Document(indexName = "categories", createIndex = false)
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Builder
//public class CategoryDocument {
//    @Id
//    private Integer id;
//
//    @Field(type = FieldType.Keyword)
//    private String name;
//
//    @Field(type = FieldType.Text)
//    private String description;
//}
