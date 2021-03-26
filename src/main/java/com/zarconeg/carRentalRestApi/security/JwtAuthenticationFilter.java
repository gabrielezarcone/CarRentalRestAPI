package com.zarconeg.carRentalRestApi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zarconeg.carRentalRestApi.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.zarconeg.carRentalRestApi.security.SecurityConstants.EXPIRATION_TIME;
import static com.zarconeg.carRentalRestApi.security.SecurityConstants.SECRET;

// Personalizza e definisce la logica di autenticazione
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //https://www.freecodecamp.org/news/how-to-setup-jwt-authorization-and-authentication-in-spring/


    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/user/login"); // imposta url di default per il login (senza questa linea spring userebbe "/login")
    }// NB spring security ha già creato il controller dell'endpoint per il login. Non dobbiamo farlo noi e si trova al path definito qui sopra.


    // Metodo di UsernamePasswordAuthenticationFilter che gira quando utente prova a fare login
    // legge le credenziali, crea un Pojo e quindi controlla autenticazione
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Metodo di UsernamePasswordAuthenticationFilter che gira se l'autenticazione ha succeesso
    // i parametri di questo metodo vengono passati direttamente da spring dietro le quinte
    // Restituisce i un Authentication Object
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        String body = ((User) auth.getPrincipal()).getUsername() + " " + token;

        res.getWriter().write(body);
        res.getWriter().flush();
    }
}
