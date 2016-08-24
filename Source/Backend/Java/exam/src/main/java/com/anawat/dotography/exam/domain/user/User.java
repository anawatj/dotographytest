package com.anawat.dotography.exam.domain.user;

import com.anawat.dotography.exam.domain.AbstractDomain;

public class User extends AbstractDomain<Integer> {

	public User()
	{
		
	}
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
