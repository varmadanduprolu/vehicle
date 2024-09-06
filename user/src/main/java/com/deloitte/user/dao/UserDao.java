package com.deloitte.user.dao;

import com.deloitte.user.entity.Users;
import com.deloitte.user.model.UserRegistry;

import java.util.Optional;

public interface UserDao {
   public String userRegistration(UserRegistry userRegistry);

   public Optional<Users> getUserById(Integer userId);

   public String deleteUser(Integer id);

   public String updateUserDetails(Integer id, Users users);
   public boolean existsUsersByUserId(Integer id);

   boolean existsUsersByEmail(String email);
}
