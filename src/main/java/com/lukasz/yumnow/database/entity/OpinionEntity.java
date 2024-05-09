package com.lukasz.yumnow.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@With
@Builder
@Getter
@Setter
@EqualsAndHashCode(of= "opinionId")
@ToString(of= {"opinionId", "description", "stars"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "opinion")
public class OpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Integer opinionId;

    @Column(name = "description")
    private String description;

    @Column(name = "stars")
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private LocalEntity local;

}
