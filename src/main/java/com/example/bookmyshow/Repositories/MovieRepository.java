package com.example.bookmyshow.Repositories;

import com.example.bookmyshow.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

}
