package com.example.bookmyshow.Services;

import com.example.bookmyshow.Converters.MovieConverter;
import com.example.bookmyshow.EntryDtos.MovieEntryDto;
import com.example.bookmyshow.Models.MovieEntity;
import com.example.bookmyshow.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws NullPointerException{
        MovieEntity movieEntity = MovieConverter.convertMovieEntryDtoToMovieEntity(movieEntryDto);
        movieRepository.save(movieEntity);
        return "movie added successfully!";
    }
}
