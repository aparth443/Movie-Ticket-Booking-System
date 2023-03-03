package com.example.bookmyshow.Repositories;

import com.example.bookmyshow.Models.TheatreSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreSeatRepository extends JpaRepository<TheatreSeatEntity,Integer> {
}
