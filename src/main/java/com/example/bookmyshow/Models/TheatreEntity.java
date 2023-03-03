package com.example.bookmyshow.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theatre_info")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "theatreEntity",cascade = CascadeType.ALL)
    private List<TheatreSeatEntity> theatreSeatEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "theatreEntity",cascade = CascadeType.ALL)
    private List<ShowEntity> showEntityList = new ArrayList<>();

}
