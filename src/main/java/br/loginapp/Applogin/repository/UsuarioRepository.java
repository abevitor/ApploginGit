package br.loginapp.Applogin.repository;

import br.loginapp.Applogin.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository <Usuario, String> {

    Usuario findById(long id);

    @Query(value="select * from AppLogin.usuario where email = :email and senha = :senha", nativeQuery = true)
    public Usuario login(String email, String senha);
    
}
