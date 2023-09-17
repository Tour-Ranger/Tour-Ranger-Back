package com.tourranger.common.security;

import com.tourranger.user.entity.User;
import com.tourranger.user.entity.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

	private final User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	// Authentication
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		UserRoleEnum role = user.getRole();
		String authority = role.getAuthority();

		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(simpleGrantedAuthority);

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
