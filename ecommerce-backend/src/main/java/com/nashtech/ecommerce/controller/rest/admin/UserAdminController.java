package com.nashtech.ecommerce.controller.rest.admin;

import com.nashtech.ecommerce.dto.request.RequestUserDto;
import com.nashtech.ecommerce.dto.response.ResponseListUser;
import com.nashtech.ecommerce.dto.response.ResponseMessageDto;
import com.nashtech.ecommerce.dto.response.ResponseUserDto;
import com.nashtech.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
//@Tag(name = 
//        description = "Allow to display and edit user data")
public class UserAdminController {

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all users in admin site",
            tags = {"Administrator"})
    public ResponseListUser findAllUser(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("status") Optional<Integer> statusOptional,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        int status = statusOptional.orElse(-1);
        return userService.findAllUser(name, status, currentPage, pageSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find a user by id in admin site",
            tags = {"Administrator"})
    public ResponseUserDto findUserDtoById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new user",
            tags = {"Administrator"})
    public ResponseMessageDto signUp(@Valid @RequestBody RequestUserDto requestUserDto) {
        if (userService.existByEmail(requestUserDto.getEmail())) {
            return new ResponseMessageDto(HttpStatus.OK, "Email is existed",
                    LocalDateTime.now());
        }
        if (userService.existByPhoneNumber(requestUserDto.getPhoneNumber())) {
            return new ResponseMessageDto(HttpStatus.OK, "Phone number is existed",
                    LocalDateTime.now());
        }
        userService.createUser(requestUserDto);
        return new ResponseMessageDto(HttpStatus.OK, "Create user success!",
                LocalDateTime.now());

    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit information of user",
            tags = {"Administrator"})
    public ResponseUserDto updateUser(@Valid @RequestBody RequestUserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Inactive user",
            tags = {"Administrator"})
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
