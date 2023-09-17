package com.tourranger.user.service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.user.dto.SignupRequestDto;
import com.tourranger.user.entity.User;
import com.tourranger.user.entity.UserRoleEnum;
import com.tourranger.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void signup(SignupRequestDto requestDto) {
		User user = findUser(requestDto.getEmail());
		if (user != null) {
			throw new CustomException(CustomErrorCode.USER_ALREADY_EXIST, null);
		}
		String email = requestDto.getEmail();
		String password = passwordEncoder.encode(requestDto.getPassword());
		UserRoleEnum role = UserRoleEnum.USER;
		User targetUser = User.builder()
				.email(email).password(password).role(role).build();
		userRepository.save(targetUser);
	}

	public User findUser(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

}
