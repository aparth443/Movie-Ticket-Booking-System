package com.example.bookmyshow.EntryDtos;

import com.example.bookmyshow.Enums.Genre;
import com.example.bookmyshow.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;

    private double ratings;
    private int duration;
    private Language language;
    private Genre genre;
}
