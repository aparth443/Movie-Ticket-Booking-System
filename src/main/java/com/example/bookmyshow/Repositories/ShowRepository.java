package com.example.bookmyshow.Repositories;

import com.example.bookmyshow.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {
}
