package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	@RequestMapping("/hello2")
	public String hello2(@RequestParam(value="name") String name,Model model) {
		model.addAttribute("name", name);
		return "hello2";
	}
	
	@RequestMapping("/hello2/{name}")
	public String helloPath(@PathVariable Optional<String> name,Model model)
	{ if (name.isPresent()) {
		model.addAttribute("name", name.get());
		}
	else {
		model.addAttribute("name", "Phoenix");
	}
		return "hello2";
	}
	
	@RequestMapping("/calculator")
    public String jumlah(@RequestParam(value = "a", required = false, defaultValue = "80") int a,
                            @RequestParam(value = "b", required = false, defaultValue = "876") int b, 
                            Model model) {
                            
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("hasil", a + b);

        String[] ribuan = { "", " Seribu", " Dua Ribu", " Tiga Ribu", " Empat Ribu", " Lima Ribu", " Enam Ribu"," Tujuh Ribu", " Delapan Ribu", " Sembilan Ribu" };
        String[] ratusan= { "", " Seratus", " Dua Ratus", " Tiga Ratus", " Empat Ratus", " Lima Ratus", " Enam Ratus"," Tujuh Ratus", " Delapan Ratus", " Sembilan Ratus" };
        String[] belasan = { "", " Belas", " Dua Puluh", " Tiga Puluh", " Empat Puluh", " Lima Puluh", " Enam Puluh"," Tujuh Puluh", " Delapan Puluh", " Sembilan Puluh" };
        String[] angka = { "", " Satu", " Dua", " Tiga", " Empat", " Lima", " Enam", " Tujuh", " Delapan", " Sembilan", " Sepuluh" };

        int hasil = a + b;
        if (hasil < 9999 && hasil > 0) {
            int modRibuan, modRatusan, modPuluhan, modDozen;
            modRibuan= hasil / 1000;
            modRatusan= (hasil % 1000) / 100;
            modPuluhan= (hasil % 100) / 10;
            modDozen= hasil % 10;
            if (modPuluhan== 1) {
                if (modDozen== 1) {
                    model.addAttribute("terbilang", ribuan[modRibuan] + ratusan[modRatusan] + " Se" + belasan[modPuluhan]);
                } else {
                    model.addAttribute("terbilang", ribuan[modRibuan] + ratusan[modRatusan] + angka[modDozen] + belasan[modPuluhan]);
                } 
            } else {
                model.addAttribute("terbilang", ribuan[modRibuan] + ratusan[modRatusan] + belasan[modPuluhan] + angka[modDozen]);
            }
        } else {
            model.addAttribute("terbilang", "Maksimal hasil penjumlahan adalah 9999 dan minimal adalah 1");
        }
        return "kalkulator";
    }
}


//public class PageController {

//}

