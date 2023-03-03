package com.example.bookmyshow.Converters;

import com.example.bookmyshow.EntryDtos.TheatreEntryDto;
import com.example.bookmyshow.Models.TheatreEntity;

public class TheatreConverter {

    public static TheatreEntity convertTheatreEntryDtoToTheatreEntity(TheatreEntryDto theatreEntryDto){
        return TheatreEntity.builder().name(theatreEntryDto.getName()).location(theatreEntryDto.getLocation()).build();

    }
}
