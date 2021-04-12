package com.reactive.project.firstreactiveproject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document
public class User {

    @Id
    private UUID id;
    private String name;
    private String email;
    private List<Car> cars;

}