package com.kamil.dinnerapp.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {
	
	
	private Integer id;
	private String username;
	private String token;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUserDetails(Integer id, String username, String token) {
		this.id = id;
		this.username = username;
		this.token = token;
		this.authorities = null;
	}
	public JwtUserDetails(Integer id, String username, String token, List<GrantedAuthority> grantedAuthorities) {
		this.id = id;
		this.username = username;
		this.token = token;
		this.authorities = grantedAuthorities;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public Integer getId() {
		return id;
	}
	
	public String getToken() {
		return token;
	}
	
	
	
	
	
	

}
