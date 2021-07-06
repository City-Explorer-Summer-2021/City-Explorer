package com.example.cityexplorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "food_place")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 255, message = "Must be less than 255")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 1023, message = "Must be less than 1023")
    private String description;

    @NotBlank(message = "Address cannot be empty")
    @Size(max = 255, message = "Must be less than 255")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "foodPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodPlaceValuation> foodPlaceValuations;
}
