package com.deloitte.user.service;

import com.deloitte.user.dao.UserDao;
import com.deloitte.user.entity.Users;
import com.deloitte.user.exception.DuplicateResourceException;
import com.deloitte.user.exception.InsufficientDataException;
import com.deloitte.user.exception.RequestValidationException;
import com.deloitte.user.exception.ResourceNotFoundException;
import com.deloitte.user.model.UserRegistry;
import com.deloitte.user.model.UserResponse;
import com.deloitte.user.model.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public String userRegistration(UserRegistry userRegistry) {
        //all values are intact or not
        if (userRegistry.getFirstName()==null || userRegistry.getLastName()==null
            || userRegistry.getEmail()==null || userRegistry.getMobileNumber()==null
            || userRegistry.getPassword()==null || userRegistry.getConfirmationPassword()==null)
            throw new InsufficientDataException("please enter the all the required details");
        //verify the password & conPassword
        if (!userRegistry.getConfirmationPassword().equals(userRegistry.getPassword()))
            throw new RequestValidationException("Passwords are not matched , please try again ");
        // email format
        //mobile number

        //existing email or not

        if(userDao.existsUsersByEmail(userRegistry.getEmail())){
            throw new DuplicateResourceException("user with email already taken");
        }
        return userDao.userRegistration(userRegistry);
    }

    @Override
    public Users getUserById(Integer userId) {

       return userDao.getUserById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user with  id %s not found".formatted(userId)));

//        UserResponse userResponse=new UserResponse();

//        if (userById!=null ){
//            userResponse.setFirstName(userById.getFirst_name());
//            userResponse.setLastName(userById.getLast_name());
//            userResponse.setEmail(userById.getEmail());
//            userResponse.setMobileNumber(userById.getMobileNumber());
//        }
//        else throw new RuntimeException("no data found for the Id");
//
//        return userDao.getUserById(userId).orElseThrow();

    }

    @Override
    public String deleteUser(Integer id) {
        if (!userDao.existsUsersByUserId(id)) {
            throw new ResourceNotFoundException("user with id %s is not found".formatted(id));
        }
        return userDao.deleteUser(id);
    }



    @Override
    public String updateUserDetails(Integer id, UserUpdate userUpdate) {
       if ( !userDao.existsUsersByUserId(id)) return "User not found with given ID";
        Users users=userDao.getUserById(id).orElseThrow();

        if (userUpdate.getFirstName()!=null)
            users.setFirst_name(userUpdate.getFirstName());
        if (userUpdate.getLastName()!=null)
            users.setLast_name(userUpdate.getLastName());
        if (userUpdate.getMobileNumber()!=null)
            users.setMobileNumber(userUpdate.getMobileNumber());
        if (userUpdate.getEmail()!=null)
            users.setEmail(userUpdate.getEmail());
        //
        return userDao.updateUserDetails(id,users);
    }
}
