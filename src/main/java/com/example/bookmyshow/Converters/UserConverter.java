package com.example.bookmyshow.Converters;

import com.example.bookmyshow.EntryDtos.UserEntryDto;
import com.example.bookmyshow.Models.UserEntity;

public class UserConverter {
    public static UserEntity convertUserEntryDtoToUserEntity(UserEntryDto userEntryDto){
        return UserEntity.builder().age(userEntryDto.getAge()).email(userEntryDto.getEmail()).mobNo(userEntryDto.getMobNo()).name(userEntryDto.getName()).address(userEntryDto.getAddress()).build();
    }
}
