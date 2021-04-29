package org.js.azdanov.api.users.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.js.azdanov.api.users.service.UsersService;
import org.js.azdanov.api.users.shared.UserDto;
import org.js.azdanov.api.users.ui.model.LoginRequest;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UsersService usersService;
    private final ObjectMapper objectMapper;
    private final Environment environment;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest login = getLoginRequest(request);

        return getAuthenticationManager().authenticate(
            new UsernamePasswordAuthenticationToken(
                login.getEmail(),
                login.getPassword(),
                List.of()
            )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();

        UserDto user = usersService.getByEmail(username);
        String userId = user.getUserId().toString();
        Date expiration = new Date(System.currentTimeMillis() +
            Long.parseLong(environment.getProperty("jwt.expiration", "86400000")));

        String jwt = Jwts.builder()
            .setSubject(userId)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, environment.getProperty("jwt.secret"))
            .compact();

        response.addHeader("token", jwt);
        response.addHeader("userId", userId);
    }

    @SneakyThrows
    private LoginRequest getLoginRequest(HttpServletRequest request) {
        return objectMapper.readValue(request.getInputStream(), LoginRequest.class);
    }
}
