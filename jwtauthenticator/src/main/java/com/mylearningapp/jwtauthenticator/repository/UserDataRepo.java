package com.mylearningapp.jwtauthenticator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylearningapp.jwtauthenticator.entity.UserData;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, Long> {
	UserData findByUsername(String username);
}
