package pl.michal.clinical_nutrition.auth.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.michal.clinical_nutrition.auth.dto.UserDTO;
import pl.michal.clinical_nutrition.auth.entity.User;
import pl.michal.clinical_nutrition.auth.mapper.UserMapper;
import pl.michal.clinical_nutrition.auth.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> userDTOs =userMapper.toUsersDTOs(userService.findAll());
        for (UserDTO userDTO : userDTOs){
            userDTO.setPassword("");
        }

        return ResponseEntity.ok(userDTOs);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        userService.save(userMapper.toUser(userDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        UserDTO userDTO= userMapper.toUserDTO(user.get());
        userDTO.setPassword("");
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        user.setId(id);

        userService.save(user);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
