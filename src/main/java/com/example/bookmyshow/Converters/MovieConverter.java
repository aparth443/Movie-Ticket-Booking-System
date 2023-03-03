package com.example.bookmyshow.Converters;

import com.example.bookmyshow.EntryDtos.MovieEntryDto;
import com.example.bookmyshow.Models.MovieEntity;

public class MovieConverter {

    public static MovieEntity convertMovieEntryDtoToMovieEntity(MovieEntryDto movieEntryDto){
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).genre(movieEntryDto.getGenre()).duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();
        return movieEntity;
    }
}
