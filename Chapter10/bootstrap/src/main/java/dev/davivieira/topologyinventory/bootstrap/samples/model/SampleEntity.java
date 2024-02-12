package dev.davivieira.topologyinventory.bootstrap.samples.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sample_entity")
@NamedQuery(name = "SampleEntity.findAll",
        query = "SELECT f FROM SampleEntity f ORDER BY f.field",
        hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    private String field;
    @Getter
    @Setter
    @Column(name="`value`")
    private int value;
}