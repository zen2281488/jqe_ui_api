package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String mark;
    private String model;
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "engine_type_id")
    private EngineType engineType;

    @Column (name = "person_id")
    private Integer personId;

}