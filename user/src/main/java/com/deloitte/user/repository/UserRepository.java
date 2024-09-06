package com.deloitte.user.repository;

import com.deloitte.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;


public interface UserRepository extends JpaRepository<Users,Integer> {

    boolean existsUsersByEmail(String email);
    public boolean existsUsersByUserId(Integer id);
}
