package com.example.mexmonxona.Repazitori;

import com.example.mexmonxona.Model.Mexmonxona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MexmonxonaRepazitori extends JpaRepository<Mexmonxona,Integer> {
boolean existsByNomi(String nomi);
boolean existsById(Integer id);
}
