package com.deloitte.user.dao;

import com.deloitte.user.entity.Users;
import com.deloitte.user.model.UserRegistry;
import com.deloitte.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
   private UserRepository userRepository;
    @Override
    public String userRegistration(UserRegistry userRegistry) {
        try {
            Users users=new Users();
            users.setFirst_name(userRegistry.getFirstName());
            users.setLast_name(userRegistry.getLastName());
            users.setEmail(userRegistry.getEmail());
            users.setMobileNumber(userRegistry.getMobileNumber());
            users.setPassword(userRegistry.getPassword());
            userRepository.save(users);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "successfully registered";
    }

    @Override
    public Optional<Users> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public String deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

       return "successfully deleted the user";
    }

    @Override
    public String updateUserDetails(Integer id, Users users) {
        try{
            userRepository.save(users);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return "updated successfully";
    }

    @Override
    public boolean existsUsersByUserId(Integer id) {
        return userRepository.existsUsersByUserId(id);
    }

    @Override
    public boolean existsUsersByEmail(String email) {
        return userRepository.existsUsersByEmail(email);
    }

    public String encription(String pass){
        String password=pass;
        return password;
    }


}
