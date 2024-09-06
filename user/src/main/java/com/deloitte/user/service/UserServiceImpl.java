package com.deloitte.user.service;

import com.deloitte.user.dao.UserDao;
import com.deloitte.user.entity.Users;
import com.deloitte.user.model.UserRegistry;
import com.deloitte.user.model.UserResponse;
import com.deloitte.user.model.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    @Override
    public String userRegistration(UserRegistry userRegistry) {
        //all values are intact or not
        //verify the password & conPassword
        // email format
        //mobile number
        //existing email or not

        return userDao.userRegistration(userRegistry);
    }

    @Override
    public UserResponse getUserById(Integer userId) {

        Optional<Users> userById = userDao.getUserById(userId);

        UserResponse userResponse=new UserResponse();

        if (userById!=null && !userById.isEmpty() && userById.isPresent()){
            userResponse.setFirstName(userById.get().getFirst_name());
            userResponse.setLastName(userById.get().getLast_name());
            userResponse.setEmail(userById.get().getEmail());
            userResponse.setMobileNumber(userById.get().getMobileNumber());
        }
        else throw new RuntimeException("no data found for the Id");

        return userDao.getUserById(userId).orElseThrow(() - new RuntimeException("no data"));

    }

    @Override
    public String deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }



    @Override
    public String updateUserDetails(Integer id, UserUpdate userUpdate) {
       if ( !userDao.existsUsersByUserId(id)) return "User not found with given ID";
        Optional<Users> users=userDao.getUserById(id);

        if (userUpdate.getFirstName()!=null)
            users.get().setFirst_name(userUpdate.getFirstName());
        if (userUpdate.getLastName()!=null)
            users.get().setLast_name(userUpdate.getLastName());
        if (userUpdate.getMobileNumber()!=null)
            users.get().setMobileNumber(userUpdate.getMobileNumber());
        if (userUpdate.getEmail()!=null)
            users.get().setEmail(userUpdate.getEmail());
        //
        return userDao.updateUserDetails(id,users);
    }
}
