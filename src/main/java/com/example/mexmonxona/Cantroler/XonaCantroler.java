package com.example.mexmonxona.Cantroler;

import com.example.mexmonxona.Dto.XonaDto;
import com.example.mexmonxona.Model.Mexmonxona;
import com.example.mexmonxona.Model.Xona;
import com.example.mexmonxona.Repazitori.MexmonxonaRepazitori;
import com.example.mexmonxona.Repazitori.XonaRepazitori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/xona")
public class XonaCantroler {
    @Autowired
    MexmonxonaRepazitori mexmonxonaRepazitori;
    @Autowired
    XonaRepazitori xonaRepazitori;
    @PostMapping("/insert")
    public String XonaInsert(@RequestBody XonaDto xonaDto){
        Optional<Mexmonxona> optionalMexmonxona=mexmonxonaRepazitori.findById(xonaDto.getMexmonxonaId());
        if (!optionalMexmonxona.isPresent()) return "Mexmon xona topilmadi!";
       boolean b = xonaRepazitori.existsByNomiAndMexmonxonaId(xonaDto.getNomi(), xonaDto.getMexmonxonaId());
        if (b) return "Bu mexmonxonada bunaday xona mavjud!";
        Xona xona=new Xona();
        xona.setNomi(xonaDto.getNomi());
        xona.setMexmonxona(optionalMexmonxona.get());
        xonaRepazitori.save(xona);
        return "Joylandi!";
    }
    @GetMapping
    public List<Xona> XonaData(){
        List<Xona> xonaList=xonaRepazitori.findAll();
        if (xonaList.isEmpty()) return null;
        return xonaList;
    }
    @PutMapping("/update/{id}")
    public String XonaUpdate(@PathVariable Integer id,@RequestBody XonaDto xonaDto){
        Optional<Mexmonxona> optionalMexmonxona=mexmonxonaRepazitori.findById(xonaDto.getMexmonxonaId());
        Optional<Xona> optionalXona=xonaRepazitori.findById(id);
        if (!optionalXona.isPresent()) return "Malumot topilmadi!";
        Xona xona=optionalXona.get();
        xona.setNomi(xonaDto.getNomi()==null?xona.getNomi():xonaDto.getNomi());
        xona.setMexmonxona(optionalMexmonxona.get());
        xonaRepazitori.save(xona);
        return "Yangilandi!";
    }
    @DeleteMapping("/{id}")
    public String XonaDelete(@PathVariable Integer id){
        boolean b = xonaRepazitori.existsById(id);
        if (!b) return "Bunday xona mavjud emas!";
        xonaRepazitori.deleteById(id);
        return "O`chirildi!";
    }
}
