package pl.michal.clinical_nutrition.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.admin.dto.JosDTO;
import pl.michal.clinical_nutrition.admin.mapper.UserMapper;


import pl.michal.clinical_nutrition.admin.config.JwtTokenUtil;
import pl.michal.clinical_nutrition.admin.dto.JwtRequest;
import pl.michal.clinical_nutrition.admin.dto.UserDTO;
import pl.michal.clinical_nutrition.admin.service.UserService;


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



    @RequestMapping(value = "/api/user/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) throws Exception {
        token=token.substring(7); //ucinam Bearer
        jwtTokenUtil.addTokenToBlackList(token);
        return ResponseEntity.status(HttpStatus.OK).body("Wylogowano");
    }

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @RequestMapping(value = "/api/user/refreshToken", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(@RequestBody JosDTO josDTO, @RequestHeader("Authorization") String token) throws Exception {
        token=token.substring(7); //ucinam Bearer
        final UserDetails userDetails = userService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token),josDTO.getId());
        final String newToken = jwtTokenUtil.generateToken(userDetails,josDTO.getId(), token);
        return ResponseEntity.status(HttpStatus.OK).body(newToken);
    }
    @RequestMapping(value = "/api/user/getCurrentUser", method = RequestMethod.POST)
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) throws Exception {
        token=token.substring(7); //ucinam Bearer
        UserDTO userDTO = userMapper.toUserDTO(userService.findByUsername(jwtTokenUtil.getUsernameFromToken(token)));
        userDTO.setPassword(null);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
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