package com.ferhad.excellentjobs.mapper;

import com.ferhad.excellentjobs.dto.CompanyResponseDto;
import com.ferhad.excellentjobs.dto.UserDto;
import com.ferhad.excellentjobs.model.User;
import com.ferhad.excellentjobs.util.TimeConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto convert(User user, CompanyResponseDto company) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCompany(company);
        userDto.setCreatedAt(TimeConverter.convert(user.getCreatedAt()));
        return userDto;
    }
}
