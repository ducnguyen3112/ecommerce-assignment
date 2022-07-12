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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private UserServiceImpl userServiceImpl;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        modelMapper = mock(ModelMapper.class);
        userServiceImpl = new UserServiceImpl(userRepository, modelMapper, passwordEncoder);
    }

    @Test
    public void findUserById_WhenRequestValid_Expect_ReturnUser() {
        User user = mock(User.class);
        Optional<User> userOptional = Optional.of(user);
        ResponseUserDto expectedUserDto = mock(ResponseUserDto.class);
        when(userRepository.findById(1L)).thenReturn(userOptional);
        user = userOptional.get();
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(expectedUserDto);
        ResponseUserDto actualUserDto = userServiceImpl.findUserById(1L);
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    public void findUserById_WhenIdNotFound_Expect_throwResourceNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () ->
                userServiceImpl.findUserById(1L), "Did not find user has id = " + 1L);
    }


    @Test
    public void existByEmail_WhenEmailExist_Expect_ReturnTrue() {
        when(userRepository.existsByEmail("email")).thenReturn(true);
        boolean result = userServiceImpl.existByEmail("email");
        assertTrue(result);
    }

    @Test
    public void existByEmail_WhenEmailNotExist_Expect_ReturnFalse() {
        when(userRepository.existsByEmail("email")).thenReturn(false);
        boolean result = userServiceImpl.existByEmail("email");
        assertFalse(result);
    }

    @Test
    public void signUp_WhenRequestSignUpValid_Expect_ReturnUserSaved() {
        RequestSignUpDto requestSignUpDto = mock(RequestSignUpDto.class);
        User user = mock(User.class);
        ResponseUserDto responseUserDto = mock(ResponseUserDto.class);
        when(modelMapper.map(requestSignUpDto, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(responseUserDto);
        assertEquals(responseUserDto, userServiceImpl.signUp(requestSignUpDto));
    }

    @Test
    public void updateUser_WhenRequestUserDtoValid_Expect_ReturnUserUpdated() {
        User user = mock(User.class);
        RequestUserDto userDto = mock(RequestUserDto.class);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(userOptional);
        user = userOptional.get();
        modelMapper.map(userDto, user);
        when(userRepository.save(user)).thenReturn(user);
        ResponseUserDto expectedUserDto = modelMapper.map(user, ResponseUserDto.class);
        ResponseUserDto actualUserDto = userServiceImpl.updateUser(userDto, 1L);
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    public void deleteUser_WhenRequestValid_Expect_ReturnResponseMessage() {
        User user = mock(User.class);
        RequestUserDto userDto = mock(RequestUserDto.class);
        ResponseUserDto expectedUserDto = mock(ResponseUserDto.class);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findById(1L)).thenReturn(userOptional);
        user = userOptional.get();
        user.setStatus(UserStatus.INACTIVE);
        verify(user).setStatus(UserStatus.INACTIVE);
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, ResponseUserDto.class)).thenReturn(expectedUserDto);
        ResponseUserDto actualUserDto = userServiceImpl.deleteUser(1L);
        assertEquals(expectedUserDto, actualUserDto);
    }
}
