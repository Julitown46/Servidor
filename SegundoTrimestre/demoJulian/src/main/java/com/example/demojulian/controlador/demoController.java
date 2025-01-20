package com.example.demojulian.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoController {
    @GetMapping("/demoth1")
    public String demoTH(Model model) {

        String textoParrafo2 = "Esto es el texto que irá dentro del párrafo...";
        model.addAttribute("parrafo2", textoParrafo2);

        return "demoth1";
    }
}
