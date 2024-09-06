package com.deloitte.user.service;

import com.deloitte.user.entity.Users;
import com.deloitte.user.model.UserRegistry;
import com.deloitte.user.model.UserUpdate;

public interface UserService {


   public String userRegistration(UserRegistry userRegistry);

   public Users getUserById(Integer userId);

   public String deleteUser(Integer id);

   public String updateUserDetails(Integer id, UserUpdate userUpdate);
}
