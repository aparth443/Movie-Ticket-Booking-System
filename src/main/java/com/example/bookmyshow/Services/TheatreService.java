package com.example.bookmyshow.Services;

import com.example.bookmyshow.Converters.TheatreConverter;
import com.example.bookmyshow.EntryDtos.TheatreEntryDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Models.TheatreEntity;
import com.example.bookmyshow.Models.TheatreSeatEntity;
import com.example.bookmyshow.Repositories.TheatreRepository;
import com.example.bookmyshow.Repositories.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(TheatreEntryDto theatreEntryDto) throws Exception{
        if(theatreEntryDto.getName() == null || theatreEntryDto.getLocation() == null){
            throw new Exception("Name and location should be valid!");
        }
        TheatreEntity theatreEntity = TheatreConverter.convertTheatreEntryDtoToTheatreEntity(theatreEntryDto);
        List<TheatreSeatEntity> theatreSeatEntityList = createTheatreSeats(theatreEntryDto,theatreEntity);
        theatreEntity.setTheatreSeatEntityList(theatreSeatEntityList);
        theatreRepository.save(theatreEntity);
        return "theatre added successfully.";
    }

    public List<TheatreSeatEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto, TheatreEntity theatre){

        int noOfClassicSeats = theatreEntryDto.getClassicSeatCount();
        int noOfPremiumSeats = theatreEntryDto.getPremiumSeatCount();

        List<TheatreSeatEntity> theatreSeatEntityList = new ArrayList<>();

        for(int cnt = 1;cnt<=noOfClassicSeats;cnt++){
            TheatreSeatEntity theatreSeatEntity = TheatreSeatEntity.builder().seatType(SeatType.CLASSIC).seatNo(cnt + "C").theatreEntity(theatre).build();
            theatreSeatEntityList.add(theatreSeatEntity);
        }

        for(int cnt = 1;cnt<=noOfPremiumSeats;cnt++){
            TheatreSeatEntity theatreSeatEntity = TheatreSeatEntity.builder().seatType(SeatType.PREMIUM).seatNo(cnt + "P").theatreEntity(theatre).build();
            theatreSeatEntityList.add(theatreSeatEntity);
        }
        return theatreSeatEntityList;
    }
}
