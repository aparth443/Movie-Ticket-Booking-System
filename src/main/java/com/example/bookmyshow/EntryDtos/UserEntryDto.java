package com.example.bookmyshow.EntryDtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;

@Data
public class UserEntryDto {
    private String name;
    private int age;
    private String mobNo;
    private String address;
    private String email;
}
