package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String mark;

    protected String model;

    protected Integer price;

    @ManyToOne
    @JoinColumn(name = "engine_type_id")
    protected EngineType engineType;

    @Column (name = "person_id")
    protected Integer personId;

}