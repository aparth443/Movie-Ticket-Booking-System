package com.example.bookmyshow.Controllers;


import com.example.bookmyshow.EntryDtos.MovieEntryDto;
import com.example.bookmyshow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try{
            String response = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            String response = "Movie could not be added.";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
