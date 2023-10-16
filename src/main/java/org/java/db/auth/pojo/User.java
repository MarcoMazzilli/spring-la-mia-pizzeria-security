package org.java.db.auth.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails {
	
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
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
	}
	@Override public boolean isAccountNonExpired() {return true;}
	@Override public boolean isAccountNonLocked() {return true;}
	@Override public boolean isCredentialsNonExpired() {return true;}
	@Override public boolean isEnabled() {return true;}
}
