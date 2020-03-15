package pl.michal.clinical_nutrition.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.michal.clinical_nutrition.admin.entity.Jos;
import pl.michal.clinical_nutrition.admin.entity.Premissions;
import pl.michal.clinical_nutrition.admin.entity.User;
import pl.michal.clinical_nutrition.admin.repository.JosRepository;
import pl.michal.clinical_nutrition.admin.repository.PremissionsRepository;
import pl.michal.clinical_nutrition.admin.repository.UserRepository;

import java.util.*;

@RequiredArgsConstructor

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JosRepository josRepository;
    @Autowired
    private final PremissionsRepository premissionsRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;




    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
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
        if(user.isAdministrator())
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        else  authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }


    public UserDetails loadUserByUsername(String username,long josId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        Optional<Jos> jos = josRepository.findById(josId);
        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o nazwie: " + username);
        }
        if (jos == null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    getAuthority(user));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthority(user, jos.get()));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user, Jos jos) {

        List<Premissions> premissions = premissionsRepository.findByPremissionsPKUserAndPremissionsPKJosAndCzyAktywny(user, jos,true);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        if(user.isAdministrator()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else{
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        for (Premissions premission : premissions) {
            authorities.add(new SimpleGrantedAuthority(
                    "Premission"+premission.getPremissionsPK().getPremissionsDefinition().getId()));
        }


        return authorities;
    }



}
