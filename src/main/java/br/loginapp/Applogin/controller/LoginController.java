package br.loginapp.Applogin.controller;

import br.loginapp.Applogin.model.Usuario;
import br.loginapp.Applogin.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
class LoginController {
    
    @Autowired
    private UsuarioRepository ur;


    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrarUsuario(@Valid Usuario usuario, BindingResult result) {

        if(result.hasErrors()) {
            return "redirect:/registrar";

        }

        ur.save(usuario);

        return "redirect:/login";
    }

    

    
}
