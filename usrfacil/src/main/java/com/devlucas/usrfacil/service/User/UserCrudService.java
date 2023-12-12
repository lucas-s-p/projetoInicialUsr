package com.devlucas.usrfacil.service.User;

import com.devlucas.usrfacil.dto.UserPostDto;
import com.devlucas.usrfacil.model.User;

import java.util.List;

public interface UserCrudService {
    User userCreateService(UserPostDto userDTO);
    void userDeleteById(Long id);
    List<User> userFindAllService();
    User userFindyByIdService(Long id);
}
