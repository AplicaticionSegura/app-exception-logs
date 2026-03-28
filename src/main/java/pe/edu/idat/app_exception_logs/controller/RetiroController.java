package pe.edu.idat.app_exception_logs.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.app_exception_logs.dto.RetiroForm;
import pe.edu.idat.app_exception_logs.service.RetiroService;

import javax.naming.Binding;

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

    @PostMapping("/procesar")
    public String procesarRetiro(@Valid @ModelAttribute("retiroForm") RetiroForm retiroForm,
                                 BindingResult result,
                                 Model model,
                                 HttpServletRequest request){
        if(result.hasErrors()){
            return "retiro-form";
        }
        String usuario = "operador01";
        String ipOrigen = request.getRemoteAddr();
        retiroService.realizarRetiro(
                retiroForm.getClienteId(),
                retiroForm.getMonto(),
                usuario, ipOrigen);
        model.addAttribute("mensaje",
                "Retiro realizado correctamente");
        return "retiro-exito";

    }

}
