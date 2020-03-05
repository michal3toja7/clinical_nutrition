package pl.michal.clinical_nutrition.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.auth.mapper.UserMapper;


import pl.michal.clinical_nutrition.auth.config.JwtTokenUtil;
import pl.michal.clinical_nutrition.auth.dto.JwtRequest;
import pl.michal.clinical_nutrition.auth.dto.JwtResponse;
import pl.michal.clinical_nutrition.auth.dto.UserDTO;
import pl.michal.clinical_nutrition.auth.service.UserService;

import java.util.Optional;


@RequiredArgsConstructor

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;



    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDTO userDTO = userMapper.toUserDTO(userService.findByUsername(authenticationRequest.getUsername()));

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());


        final String token = jwtTokenUtil.generateToken(userDetails);
        userDTO.setToken(token);
        userDTO.setPassword("");
        System.out.println(jwtTokenUtil.getExpirationDateFromToken(token));
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
                //status(HttpStatus.OK).body(token+userMapper.toUserDTO((user)));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}