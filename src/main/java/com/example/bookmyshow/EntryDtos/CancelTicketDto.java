package com.example.bookmyshow.EntryDtos;

import lombok.Data;

import java.util.List;

@Data
public class CancelTicketDto {
    private int ticketId;
    private List<String> cancelTicketList;
}
