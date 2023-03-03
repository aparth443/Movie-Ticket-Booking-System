package com.example.bookmyshow.Models;


import com.example.bookmyshow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "theatre_seat_info")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheatreSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private String seatNo;

    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;


}
