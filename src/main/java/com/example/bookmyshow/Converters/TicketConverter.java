package com.example.bookmyshow.Converters;


import com.example.bookmyshow.EntryDtos.TicketEntryDto;
import com.example.bookmyshow.Models.TicketEntity;

public class TicketConverter {

    public static TicketEntity convertTicketEntryDtoToTicketEntity(TicketEntryDto ticketEntryDto){
        TicketEntity ticketEntity = new TicketEntity();
        return ticketEntity;
    }
}
