package kr.bit.hotgirl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String address_front;
    private String address_back;

    @NotEmpty
    private String name;


    private String zipcode;

    @NotEmpty
    private double latitude;
    @NotEmpty
    private double longitude;

}
