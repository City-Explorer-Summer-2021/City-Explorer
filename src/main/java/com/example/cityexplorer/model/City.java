package com.example.cityexplorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 255, message = "Must be less then 255")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @OneToMany(mappedBy = "city")
    private List<CityPhoto> cityPhotoList;

    @OneToMany(mappedBy = "city")
    private List<Transport> transportList;

//    @OneToMany(mappedBy = "city")
//    private List<Hotel> hotelList;

    @NotBlank(message = "Latitude value cannot be empty")
    private BigDecimal lat;

    @NotBlank(message = "Longitude value cannot be empty")
    private BigDecimal lon;

    @OneToMany(mappedBy = "city")
    private List<Attraction> attractionList;

    @OneToMany(mappedBy = "city")
    private List<Event> eventList;

    @OneToMany(mappedBy = "city")
    private List<FoodPlace> foodPlaceList;

}
