package org.java.db.auth.pojo.user.repo;

import org.java.db.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByUsername(String name);
}
