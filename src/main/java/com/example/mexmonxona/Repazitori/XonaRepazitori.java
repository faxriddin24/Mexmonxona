package com.example.mexmonxona.Repazitori;

import com.example.mexmonxona.Model.Xona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XonaRepazitori extends JpaRepository<Xona,Integer> {
    boolean existsByNomiAndMexmonxonaId(String nomi,Integer id);
    boolean existsById(Integer id);
}
