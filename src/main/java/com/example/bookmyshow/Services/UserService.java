package com.example.bookmyshow.Services;

import com.example.bookmyshow.Converters.UserConverter;
import com.example.bookmyshow.EntryDtos.UserEntryDto;
import com.example.bookmyshow.Models.UserEntity;
import com.example.bookmyshow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDto userEntryDto) throws Exception{
        UserEntity userEntity = UserConverter.convertUserEntryDtoToUserEntity(userEntryDto);
        userRepository.save(userEntity);
        return "user added successfully.";
    }
}
