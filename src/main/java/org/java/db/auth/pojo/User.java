package org.java.db.auth.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue()
	private int id;
	
	@Column(nullable = false , unique = true)
	@NotNull
	private String username;

	@Column(nullable = false)
	@NotNull
	private String password;
	
	@ManyToMany(fetch = FetchType .EAGER)
	private Set<Role> roles;
	
	public User() {};
	public User(String username , String password, Role... roles) {
		
		setUsername(username);
		setPassword(password);
		setRoles(new HashSet<>(Arrays.asList(roles)));
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	//
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {

		return "id : " + getId() + "->" + getUsername();
	}
	
	@Override
	public int hashCode() {
		
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Role)) return false;
		
		Role objRole = (Role) obj;
		
		return getId() == objRole.getId();
	}
}
