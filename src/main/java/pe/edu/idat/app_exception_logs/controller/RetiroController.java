package pe.edu.idat.app_exception_logs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.app_exception_logs.dto.RetiroForm;
import pe.edu.idat.app_exception_logs.service.RetiroService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/retiros")
public class RetiroController {

    private final RetiroService retiroService;
    //localhost:8080/retiros/nuevo
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("retiroForm", new RetiroForm());
        return "retiro-form";
    }
}
