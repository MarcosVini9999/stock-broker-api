package com.mandacarubroker.security.services;

import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;

import com.mandacarubroker.security.domain.dtos.RequestUserDTO;
import com.mandacarubroker.security.domain.dtos.ResponseUserDTO;
import com.mandacarubroker.security.domain.entities.User;
import com.mandacarubroker.security.repositories.UserRepository;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    //Get All Users
    public List<ResponseUserDTO> findAll() {

        return userRepository.findAll().stream().map(ResponseUserDTO::new).toList();
    }

    //Get User By Id
    public User findById(int id) {

        return userRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        userRepository.deleteById(id);
    }





    public static void validateRequestUserDTO(RequestUserDTO data) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<RequestUserDTO>> violations = validator.validate(data);

            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder("Validation failed. Details: ");

                for (ConstraintViolation<RequestUserDTO> violation : violations) {
                    errorMessage.append(String.format("[%s: %s], ", violation.getPropertyPath(), violation.getMessage()));
                }

                errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

                throw new ConstraintViolationException(errorMessage.toString(), violations);
            }
        }catch (ValidationException ve) {
            throw new ValidationException(ve.getMessage());
        }

    }


    public void validateAndCreateUser(RequestUserDTO data) {
        validateRequestUserDTO(data);
        findByUserName(data);

        User newUser = new User(data);

        userRepository.save(newUser);
    }


    private void findByUserName(RequestUserDTO data) {

        Optional<User> userSearch = userRepository.findByUsername(data.username());
        if(userSearch.isPresent()) {
            throw new DataIntegratyViolationException("Username already registered");
        }
    }

}
