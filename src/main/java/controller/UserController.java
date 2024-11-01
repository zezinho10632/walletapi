package controller;

import Service.UserService;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Response<UserDTO>> create(@Validated @RequestBody UserDto dto, BindingResult result)  {

        Reponse<UserDto> reponse = new Reponse<UserDto>();

        User user = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntityToDto(user))

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    private User convertDtoToEntity(UserDTO dto) {
        user u =new User();
        u.setEmail(dto.getEmail());
        u.setName(dto.getName());
        u.setPassword(dto.password);

        return u;
    }

    private UserDTO convertEntityToDto(User u) {
        UserDTO dto = new UserDTO();
        dto.setEmail(u.getEmail());
        dto.setName(u.getName());
        dto.setPassword(u.password);

        return  dto;
    }
}
