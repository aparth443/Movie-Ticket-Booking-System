package com.example.bookmyshow.Controllers;

import com.example.bookmyshow.EntryDtos.CancelTicketDto;
import com.example.bookmyshow.EntryDtos.TicketEntryDto;
import com.example.bookmyshow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody TicketEntryDto ticketEntryDto){
        try{
            String response = ticketService.bookTicket(ticketEntryDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(Exception e){
            String response = e.getMessage();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelTicket(@RequestBody CancelTicketDto cancelTicketDto){
        try{
            return new ResponseEntity<>(ticketService.cancelTicket(cancelTicketDto),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
