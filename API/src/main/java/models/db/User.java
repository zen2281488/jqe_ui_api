package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer age;

    @Column(name = "first_name")
    private String firstName;
    private Integer money;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "sex")
    private boolean boolSex;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    public String getSex() {
        return boolSex ? "MALE" : "FEMALE";
    }

}
