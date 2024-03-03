package com.mandacarubroker.security.services;

import com.mandacarubroker.elements.dtos.RequestStockDTO;
import com.mandacarubroker.elements.dtos.RequestUserDTO;
import com.mandacarubroker.elements.models.Stock;
import com.mandacarubroker.elements.services.exceptions.DataIntegratyViolationException;
import  com.mandacarubroker.security.models.User;
import  com.mandacarubroker.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;

import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    //Get All Users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //Get User By Id
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }


    public void delete(int id) {
        userRepository.deleteById(id);
    }

    //Update User
//    public void save(User user) {
//        findByUserName(user);
//        user.setPassword(encoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }



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
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setConfirmpassword(encoder.encode(newUser.getConfirmpassword()));
        userRepository.save(newUser);
    }


    private void findByUserName(RequestUserDTO data) {

        Optional<User> userSearch = userRepository.findByUsername(data.username());
        if(userSearch.isPresent()) {
            throw new DataIntegratyViolationException("Username already registered");
        }
    }

}
