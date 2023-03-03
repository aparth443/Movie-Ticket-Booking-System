package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.EntryDtos.TheatreEntryDto;
import com.example.bookmyshow.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreEntryDto theatreEntryDto){
        try{
            String response = theatreService.addTheatre(theatreEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("show could not be added.",HttpStatus.BAD_REQUEST);
        }
    }

}
