package com.crm.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.app.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	User findByEmail(String email);

	@Query("SELECT u FROM User u")
    List<User> findAllWithAccess();
	

}
