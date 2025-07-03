package br.loginapp.Applogin.controller;

import br.loginapp.Applogin.model.Usuario;
import br.loginapp.Applogin.repository.UsuarioRepository;
import br.loginapp.Applogin.service.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository ur;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "registrar";
        }

        ur.save(usuario);
        return "redirect:/login";
    }

    @GetMapping("/logout")
public String logout(HttpServletResponse response) {
    CookieService.deleteCookie(response, "usuarioId");
    CookieService.deleteCookie(response, "usuarioNome");
    return "redirect:/login";
}

 @GetMapping("/")
public String dashboard(HttpServletRequest request, Model model) {
    String usuarioNome = CookieService.getCookie(request, "usuarioNome");
    model.addAttribute("usuarioNome", usuarioNome != null ? usuarioNome : "");
    return "index";
}


    @PostMapping("/login")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getSenha());

        if (usuarioLogado != null) {
            
            CookieService.setCookie(response, "usuarioId", String.valueOf(usuarioLogado.getId()), 10000);
            CookieService.setCookie(response, "usuarioNome", String.valueOf(usuarioLogado.getNome()), 10000);
            return "redirect:/";
        }

        model.addAttribute("erro", "Usuário inválido!");
        return "login";
    }
}

