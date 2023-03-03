package com.example.bookmyshow.Converters;

import com.example.bookmyshow.EntryDtos.ShowEntryDto;
import com.example.bookmyshow.Models.ShowEntity;

public class ShowConverter {

    public static ShowEntity convertShowEntryDtoToShowEntity(ShowEntryDto showEntryDto){
        ShowEntity showEntity = ShowEntity.builder().showDate(showEntryDto.getShowDate()).showTime(showEntryDto.getShowTime()).showType(showEntryDto.getShowType()).build();
        return showEntity;
    }
}
