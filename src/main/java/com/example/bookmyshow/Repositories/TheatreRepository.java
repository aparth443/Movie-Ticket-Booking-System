package com.example.bookmyshow.Repositories;

import com.example.bookmyshow.Models.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity,Integer> {

}
