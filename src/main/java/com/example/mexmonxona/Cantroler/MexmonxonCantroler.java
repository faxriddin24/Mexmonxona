package com.example.mexmonxona.Cantroler;

import com.example.mexmonxona.Model.Mexmonxona;
import com.example.mexmonxona.Repazitori.MexmonxonaRepazitori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mexmonxona")
public class MexmonxonCantroler {
    @Autowired
    MexmonxonaRepazitori mexmonxonaRepazitori;
    @PostMapping("/insert")
    public String MexmonxonaInsert(@RequestBody Mexmonxona mexmonxona){
        boolean b = mexmonxonaRepazitori.existsByNomi(mexmonxona.getNomi());
        if (b){
            return "Bunday Restaran nomi mavjud!";
        }
        Mexmonxona mexmonxona1=new Mexmonxona();
        mexmonxona1.setNomi(mexmonxona.getNomi());
        mexmonxonaRepazitori.save(mexmonxona1);
        return "Joylandi!";
    }
    @GetMapping
    public List<Mexmonxona> MexmonxonaData(){
        List<Mexmonxona> mexmonxonaList=mexmonxonaRepazitori.findAll();
        if (mexmonxonaList.isEmpty()){
            return null;
        }
        return mexmonxonaList;
    }
    @PutMapping("update/{id}")
    public String MexmonxonaUpdate(@RequestBody Mexmonxona mexmonxona,@PathVariable Integer id){
        Optional<Mexmonxona> optionalMexmonxona=mexmonxonaRepazitori.findById(id);
        if (!optionalMexmonxona.isPresent()){
            return "Bunday Id mavjud emas";
        }
        Mexmonxona mexmonxona1=optionalMexmonxona.get();
        mexmonxona1.setNomi(mexmonxona.getNomi()==null?mexmonxona1.getNomi():mexmonxona.getNomi());
        mexmonxonaRepazitori.save(mexmonxona1);
        return "Yangilandi";
    }
    @DeleteMapping("/{id}")
    public String MexmonxonaDelate(@PathVariable Integer id){
        boolean b = mexmonxonaRepazitori.existsById(id);
        if (!b) return "Bunday mexmonxona mavjud emas!";
        mexmonxonaRepazitori.deleteById(id);
        return "O`chirildi!";
    }
}
