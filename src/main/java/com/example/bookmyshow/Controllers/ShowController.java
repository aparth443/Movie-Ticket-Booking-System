package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.EntryDtos.ShowEntryDto;
import com.example.bookmyshow.Models.ShowEntity;
import com.example.bookmyshow.Models.ShowSeatEntity;
import com.example.bookmyshow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {


    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){
        try{
            String response = showService.addShow(showEntryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("show could not be added",HttpStatus.BAD_REQUEST);
        }
    }
}
