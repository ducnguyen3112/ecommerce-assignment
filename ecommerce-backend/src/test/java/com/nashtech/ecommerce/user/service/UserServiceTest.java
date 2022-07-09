package com.nashtech.ecommerce.user.service;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.enums.RoleName;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
import com.nashtech.ecommerce.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    Optional<User> userOptional = null;
    private UserServiceImpl userService;
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setId(1l);
        userOptional = Optional.of(user);
        passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        modelMapper = mock(ModelMapper.class);
        userService = new UserServiceImpl(userRepository, modelMapper, passwordEncoder);
    }

    @Test
    public void findUserById_WhenRequestValid_Expect_ReturnUser() {
        ResponseUserDto userDto = mock(ResponseUserDto.class);
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        when(modelMapper.map(userOptional.get(), ResponseUserDto.class)).thenReturn(userDto);
        assertEquals(userDto, userService.findUserById(anyLong()));
    }

    @Test
    public void findUserById_WhenIdNotFound_Expect_throwResourceNotFoundException() {
        Assertions.assertThatThrownBy(() -> userService.findUserById(anyLong()))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Did not find user has id = " + anyLong());
    }

    @Test
    public void existByEmail_whenEmailExist_Expect_ReturnTrue() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        boolean result = userService.existByEmail(anyString());
        assertTrue(result);
    }

    @Test
    public void existByEmail_whenEmailNotExist_Expect_ReturnFalse() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        boolean result = userService.existByEmail(anyString());
        assertFalse(result);
    }

    @Test
    public void signUp_whenRequestSignUpValid_expect_ReturnUserSaved() {
        RequestSignUpDto requestSignUpDto = mock(RequestSignUpDto.class);
        User user = mock(User.class);
        requestSignUpDto.setPassword(passwordEncoder.encode(requestSignUpDto.getPassword()));
        ResponseUserDto responseUserDto = mock(ResponseUserDto.class);
        when(modelMapper.map(requestSignUpDto, User.class)).thenReturn(user);
        user.setRegisteredAt(new Date());
        Set<Role> roles = mock(Set.class);
        roles.add(new Role((long) RoleName.ROLE_USER.getValue(), RoleName.ROLE_USER));
        user.setRoles(roles);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(responseUserDto);
        assertEquals(responseUserDto, userService.signUp(requestSignUpDto));
    }
}
