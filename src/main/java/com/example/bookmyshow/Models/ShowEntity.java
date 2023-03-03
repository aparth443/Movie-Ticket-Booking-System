package com.example.bookmyshow.Models;

import com.example.bookmyshow.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "show_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;
    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "showEntity",cascade = CascadeType.ALL)
    private List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;

    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;


}
