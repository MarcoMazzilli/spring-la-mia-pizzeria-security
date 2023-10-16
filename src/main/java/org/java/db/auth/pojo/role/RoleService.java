package org.java.db.auth.pojo.role;

import java.util.List;

import org.java.db.auth.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll(){
		
		return roleRepo.findAll();
	}
	
	public Role findById(int id) {
		
		return roleRepo.findById(id).get();
	}
	
	public void save(Role role) {
		roleRepo.save(role);
	}
	
}
