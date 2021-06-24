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
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 255, message = "Must be less then 255")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 1023, message = "Must be less then 1023")
    private String description;

    @NotBlank(message = "Address cannot be empty")
    @Size(max = 255, message = "Must be less then 255")
    private String address;

    @NotBlank(message = "Start date cannot be empty")
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @NotBlank(message = "Finish date cannot be empty")
    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventPhoto> eventPhotoList;
}
