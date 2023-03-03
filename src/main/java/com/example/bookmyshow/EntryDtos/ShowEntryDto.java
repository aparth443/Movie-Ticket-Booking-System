package com.example.bookmyshow.EntryDtos;

import com.example.bookmyshow.Enums.ShowType;
import com.example.bookmyshow.Models.MovieEntity;
import com.example.bookmyshow.Models.TheatreEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowEntryDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private ShowType showType;
    private int movieId;
    private int theatreId;
    private int classicSeatPrice;
    private int premiumSeatPrice;

}
