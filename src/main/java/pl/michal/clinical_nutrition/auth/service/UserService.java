package pl.michal.clinical_nutrition.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.auth.config.JwtTokenUtil;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.auth.repository.UserRepository;

import java.util.*;

@RequiredArgsConstructor

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public User findByToken(String token) {

        System.out.println(jwtTokenUtil.getExpirationDateFromToken(token));
        return userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));

    }


    public User save(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o nazwie: " + username);
        }
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o nazwie: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user));
    }



    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if(user.isAdministrator()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
