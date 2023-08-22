package com.tourranger.user.service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.user.entity.User;
import com.tourranger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	public User findUser(String email) {
		return userRepository.findByEmail(email).orElseThrow(() ->
				new CustomException(CustomErrorCode.USER_NOT_FOUND, null)
		);
	}
}
