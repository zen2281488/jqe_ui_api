package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "parking_place")
public class ParkingPlace {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Getter
    @Column (name = "is_covered")
    protected boolean isCovered;

    @Getter
    @Column (name = "is_warm")
    protected boolean isWarm;

    @Getter
    @Column (name = "places_count")
    protected Integer placesCount;

    @Getter
    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    protected House house;
}
