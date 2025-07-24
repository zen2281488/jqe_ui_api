package models.db;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "parking_place")
public class ParkingPlace {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column (name = "is_covered")
    private boolean isCovered;

    @Getter
    @Column (name = "is_warm")
    private boolean isWarm;

    @Getter
    @Column (name = "places_count")
    private Integer placesCount;

    @Getter
    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;
}
