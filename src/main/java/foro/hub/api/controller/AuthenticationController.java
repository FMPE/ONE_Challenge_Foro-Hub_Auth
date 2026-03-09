package foro.hub.api.controller;

import foro.hub.api.domain.user.DataAuthenticationUser;
import foro.hub.api.domain.user.User;
import foro.hub.api.infra.security.DataJwtToken;
import foro.hub.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DataAuthenticationUser dataAuthUser) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataAuthUser.login(),
                dataAuthUser.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.generateToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DataJwtToken(jwtToken));
    }
}
