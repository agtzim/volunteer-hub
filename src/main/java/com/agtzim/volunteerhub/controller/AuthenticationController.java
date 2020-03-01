package com.agtzim.volunteerhub.controller;

import com.agtzim.volunteerhub.model.User;
import com.agtzim.volunteerhub.payload.AuthenticationResponse;
import com.agtzim.volunteerhub.payload.LogInRequest;
import com.agtzim.volunteerhub.repository.UserRepository;
import com.agtzim.volunteerhub.service.UserPrincipalDetailsService;
import com.agtzim.volunteerhub.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    private JwtUtil jwtUtilToken;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LogInRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails principal = userPrincipalDetailsService
                .loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtUtilToken.generateToken(principal);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(/*@RequestBody SignUpRequest signUpRequest*/@RequestBody User user) {

        if(userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
