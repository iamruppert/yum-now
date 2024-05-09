package com.lukasz.yumnow.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "opinionId")
@ToString(of = {"opinionId", "description", "stars"})
public class Opinion {

    Integer opinionId;
    String description;
    Integer stars;
    Customer customer;
    Local local;
}
