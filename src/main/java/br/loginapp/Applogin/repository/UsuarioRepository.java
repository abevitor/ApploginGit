package br.loginapp.Applogin.repository;

import br.loginapp.Applogin.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository <Usuario, String> {

    Usuario findById(long id);
    
}
