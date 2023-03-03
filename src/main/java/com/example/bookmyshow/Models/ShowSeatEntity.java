package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.SeatType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "show_seat_info")
@Data
@NoArgsConstructor
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isBooked;
    private int price;
    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity showEntity;


}
