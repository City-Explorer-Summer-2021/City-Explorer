package com.example.cityexplorer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 255, message = "Must be less than 255")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Address cannot be empty")
    @Size(max = 255, message = "Must be less than 255")
    private String address;

    @NotBlank(message = "Phone cannot be empty")
    @Size(max = 255, message = "Must be less than 255")
    private String phone;

    @NotNull(message = "Price \"From\" cannot be null")
    @Column(name = "price_from")
    private BigDecimal priceFrom;

    @NotNull(message = "Price \"To\" cannot be null")
    @Column(name = "price_to")
    private BigDecimal priceTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private HotelCategory hotelCategory;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelPhoto> photos;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
