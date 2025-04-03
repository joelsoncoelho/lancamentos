package br.com.lancamentos.lancamentos.controller;

import br.com.lancamentos.lancamentos.domain.usuario.DadosAutenticacao;
import br.com.lancamentos.lancamentos.domain.usuario.Usuario;
import br.com.lancamentos.lancamentos.infrastructure.security.DadosJWTToken;
import br.com.lancamentos.lancamentos.infrastructure.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login endpoint")
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Login")
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
            var authentication = authenticationManager.authenticate(authenticationToken);

            var jwtToken = tokenService.createTokenJWT((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosJWTToken(jwtToken));

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
