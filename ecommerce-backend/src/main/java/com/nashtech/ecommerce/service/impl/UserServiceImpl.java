package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestSignUpDto;
import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseListUser;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.enums.RoleName;
import com.nashtech.ecommerce.enums.UserStatus;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.UserRepository;
import com.nashtech.ecommerce.security.UserDetailsImpl;
import com.nashtech.ecommerce.service.UserService;
import org.hibernate.TypeMismatchException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.avatar.default}")
    String avatarDefault;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetailsImpl loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Did not find user has email = " + email));
        return UserDetailsImpl.build(user);
    }

    @Override
    public ResponseListUser findAllUser(String name, int status, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<User> userPage = null;
        if (status == -1) {
            if (StringUtils.hasText(name)) {
                userPage = userRepository.findByFullNameContaining(name, pageable);
            } else {
                userPage = userRepository.findAll(pageable);
            }
        } else if (status == 1 || status == 0) {
            if (StringUtils.hasText(name)) {
                userPage = userRepository.findByStatusAndFullNameContaining(name, status,
                        pageable);
            } else {
                userPage = userRepository.findByStatus(status, pageable);
            }

        }
        return ResponseListUser.builder().totalUser(userPage.getTotalElements())
                .perPage(userPage.getNumberOfElements())
                .currentPage(userPage.getNumber() + 1).lastPage(userPage.getTotalPages())
                .responseUserDtos(modelMapper.map(userPage.getContent(),
                        new TypeToken<List<ResponseUserDto>>() {
                        }.getType()))
                .build();
    }

    @Override
    public ResponseUserDto getUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Did not find user has id = " + id));
        return modelMapper.map(user, ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto createUser(RequestUserDto signUpDto) {
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User user = modelMapper.map(signUpDto, User.class);
        user.setRegisteredAt(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);
        Set<String> strRoles = signUpDto.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(strRole -> {
            if (strRole.equalsIgnoreCase("admin")) {
                roles.add(new Role((long) RoleName.ROLE_ADMIN.getValue(),
                        RoleName.ROLE_ADMIN));
            } else if (strRole.equalsIgnoreCase("user")) {
                roles.add(new Role((long) RoleName.ROLE_ADMIN.getValue(),
                        RoleName.ROLE_USER));
            } else {
                throw new TypeMismatchException("Roles must be admin or user ");
            }
        });
        user.setRoles(roles);
        return modelMapper.map(userRepository.save(user), ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto signUp(RequestSignUpDto signUpDto) {
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User user = modelMapper.map(signUpDto, User.class);
        user.setRegisteredAt(LocalDateTime.now());
        user.setStatus(UserStatus.ACTIVE);
        user.setAvatar(avatarDefault);
        Set<Role> roles = new HashSet<>();
        roles.add(new Role((long) RoleName.ROLE_USER.getValue(), RoleName.ROLE_USER));
        user.setRoles(roles);
        return modelMapper.map(userRepository.save(user), ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto updateUser(RequestUserDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Did not find user has id = " + id));
        modelMapper.map(userDto, user);
        user = userRepository.save(user);
        return modelMapper.map(user, ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Did not find user with id = " + id));
        user.setStatus(UserStatus.INACTIVE);
        user = userRepository.save(user);
        return modelMapper.map(user, ResponseUserDto.class);
    }

    @Override
    public ResponseUserDto getUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Did not find user has email = " + email));
        return modelMapper.map(user, ResponseUserDto.class);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isPhoneNumberExist(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }


}
