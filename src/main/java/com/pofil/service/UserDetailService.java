package com.pofil.service;

import java.util.List;
import java.util.Optional;

import com.pofil.model.AppUser;

public interface UserDetailService {

	Optional<AppUser> getUserById(long id);

	AppUser getUserByEmail(String email);

	List<AppUser> getAllUsers();

	AppUser saveUser(AppUser user);

}
