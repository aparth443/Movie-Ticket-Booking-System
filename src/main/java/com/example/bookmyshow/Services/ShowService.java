package com.example.bookmyshow.Services;

import com.example.bookmyshow.Converters.ShowConverter;
import com.example.bookmyshow.EntryDtos.ShowEntryDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Models.*;
import com.example.bookmyshow.Repositories.MovieRepository;
import com.example.bookmyshow.Repositories.ShowRepository;
import com.example.bookmyshow.Repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(ShowEntryDto showEntryDto){
        ShowEntity showEntity = ShowConverter.convertShowEntryDtoToShowEntity(showEntryDto);
        int theatreId = showEntryDto.getTheatreId();
        int movieId = showEntryDto.getMovieId();

        TheatreEntity theatreEntity = theatreRepository.findById(theatreId).get();
        MovieEntity movieEntity = movieRepository.findById(movieId).get();

        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheatreEntity(theatreEntity);

        List<ShowSeatEntity> showSeatEntityList = createShowSeats(showEntryDto,showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);

        showEntity = showRepository.save(showEntity);

        movieEntity.getShowEntityList().add(showEntity);
        theatreEntity.getShowEntityList().add(showEntity);

        movieRepository.save(movieEntity);
        theatreRepository.save(theatreEntity);


        return "show added successfully";
    }

    public List<ShowSeatEntity> createShowSeats(ShowEntryDto showEntryDto, ShowEntity showEntity){
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        int classicSeatPrice = showEntryDto.getClassicSeatPrice();
        int premiumSeatPrice = showEntryDto.getPremiumSeatPrice();

        TheatreEntity theatreEntity = showEntity.getTheatreEntity();

        List<TheatreSeatEntity> theatreSeatEntityList = theatreEntity.getTheatreSeatEntityList();

        for(TheatreSeatEntity theatreSeatEntity : theatreSeatEntityList){
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();
            showSeatEntity.setSeatNo(theatreSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theatreSeatEntity.getSeatType());
            showSeatEntity.setBooked(false);
            if(theatreSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(classicSeatPrice);
            }else{
                showSeatEntity.setPrice(premiumSeatPrice);
            }
            showSeatEntity.setShowEntity(showEntity);
            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;
    }
}
