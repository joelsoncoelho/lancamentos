package br.com.lancamentos.lancamentos.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    @Query("SELECT u FROM Usuario u JOIN FETCH u.roles where u.login = :login ")
    Usuario findByUserNameFetchRoles(@Param("login") String login);

}