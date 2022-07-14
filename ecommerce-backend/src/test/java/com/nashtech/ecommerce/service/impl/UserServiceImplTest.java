package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.enums.UserStatus;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private UserServiceImpl userServiceImpl;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    private Optional<User> userOptional;

    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setUp() {
        user = mock(User.class);
        userOptional = Optional.of(user);
        passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        modelMapper = mock(ModelMapper.class);
        userServiceImpl = new UserServiceImpl(userRepository, modelMapper, passwordEncoder);
    }

    @Test
    public void findUser_WhenUserIdExist_Expect_ReturnUser() {
        ResponseUserDto expectedUserDto = mock(ResponseUserDto.class);
        when(userRepository.findById(1L)).thenReturn(userOptional);
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(expectedUserDto);
        ResponseUserDto actualUserDto = userServiceImpl.getUser(1L);
        assertThat(actualUserDto).isEqualTo(expectedUserDto);
    }

    @Test
    public void findUserById_WhenIdNotFound_Expect_ThrowResourceNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                userServiceImpl.getUser(1L));
        assertThat(exception.getMessage()).isEqualTo("Did not find user has id = " + 1L);
    }


    @Test
    public void existByEmail_WhenEmailExist_Expect_ReturnTrue() {
        when(userRepository.existsByEmail("email")).thenReturn(true);
        boolean result = userServiceImpl.isEmailExist("email");
        assertTrue(result);
    }

    @Test
    public void existByEmail_WhenEmailNotExist_Expect_ReturnFalse() {
        when(userRepository.existsByEmail("email")).thenReturn(false);
        boolean result = userServiceImpl.isEmailExist("email");
        assertFalse(result);
    }

    @Test
    public void signUp_WhenRequestSignUpValid_Expect_ReturnUserSaved() {
        RequestSignUpDto requestSignUpDto = mock(RequestSignUpDto.class);
        ResponseUserDto expected = mock(ResponseUserDto.class);
        when(modelMapper.map(requestSignUpDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(expected);
        ResponseUserDto actual = userServiceImpl.signUp(requestSignUpDto);
        verify(requestSignUpDto).setPassword(passwordEncoder.encode(requestSignUpDto.getPassword()));
        verify(user).setStatus(UserStatus.ACTIVE);
        verify(user).setRegisteredAt(LocalDateTime.now());
        //verify(user).setRoles(roles);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updateUser_WhenRequestUserDtoValid_Expect_ReturnUserUpdated() {
        RequestUserDto userDto = mock(RequestUserDto.class);
        when(userRepository.findById(1L)).thenReturn(userOptional);
        when(userRepository.save(user)).thenReturn(user);
        ResponseUserDto expectedUserDto = modelMapper.map(user, ResponseUserDto.class);
        ResponseUserDto actualUserDto = userServiceImpl.updateUser(userDto, 1L);
        verify(modelMapper).map(userDto, user);
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    public void deleteUser_WhenRequestValid_Expect_ReturnResponseMessage() {
        ResponseUserDto expectedUserDto = mock(ResponseUserDto.class);
        when(userRepository.findById(1L)).thenReturn(userOptional);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(expectedUserDto);
        ResponseUserDto actualUserDto = userServiceImpl.deleteUser(1L);
        verify(user).setStatus(UserStatus.INACTIVE);
        verify(userRepository).save(user);
        assertEquals(expectedUserDto, actualUserDto);
    }
}
