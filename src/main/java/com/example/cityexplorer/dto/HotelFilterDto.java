package com.example.cityexplorer.dto;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.HotelCategory;
import com.example.cityexplorer.model.HotelPhoto;
import com.example.cityexplorer.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelFilterDto {
    @Size(max = 255, message = "Must be less than 255")
    private String content;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    private HotelCategory hotelCategory;
}
