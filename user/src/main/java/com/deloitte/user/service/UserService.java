package com.deloitte.user.service;

import com.deloitte.user.entity.Users;
import com.deloitte.user.model.UserRegistry;
import com.deloitte.user.model.UserResponse;
import com.deloitte.user.model.UserUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface UserService {


   public String userRegistration(UserRegistry userRegistry);

   public UserResponse getUserById(Integer userId);

   public String deleteUser(Integer id);

   public String updateUserDetails(Integer id, UserUpdate userUpdate);
}
