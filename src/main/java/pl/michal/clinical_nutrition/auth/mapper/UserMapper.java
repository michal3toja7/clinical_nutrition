package pl.michal.clinical_nutrition.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.michal.clinical_nutrition.auth.dto.*;
import pl.michal.clinical_nutrition.auth.entity.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel="spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
    List<UserDTO> toUsersDTOs(List<User> users);
    User toUser(UserDTO userDTO);
}
