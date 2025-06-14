package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "engine_type")
public class EngineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "type_name")
    protected String typeName;
}