package com.example.cityexplorer.projection;

import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.FoodPlaceValuation;
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

public interface FoodPlaceProjection {

    Long getId();

    String getName();

    String getDescription();

    String getAddress();

    City getCity();

    float getAvgValue();

    long getVotesNumer();
}
