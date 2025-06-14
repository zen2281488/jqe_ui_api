package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected Integer age;

    @Column (name = "first_name")
    protected String firstName;

    protected Integer money;

    @Column (name = "second_name")
    protected String secondName;

    @Column (name = "sex")
    protected boolean boolSex;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    protected House house;

    public String getSex() {
        return boolSex ? "MALE" : "FEMALE";
    }

}
