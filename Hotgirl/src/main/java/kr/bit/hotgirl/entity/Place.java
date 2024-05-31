package kr.bit.hotgirl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(nullable = false)
    private String addressFront;

    @Column(nullable = true)
    private String addressBack;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String name;


    @Column(nullable = true)
    private String zipcode;

    @NotEmpty
    @Column(nullable = false)
    private double latitude;

    @NotEmpty
    @Column(nullable = false)
    private double longitude;

    public Place() {}
    public Place(@NotEmpty String addressFront, @NotEmpty String name, @NotEmpty double latitude, @NotEmpty double longitude) {
        this.addressFront = addressFront;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

