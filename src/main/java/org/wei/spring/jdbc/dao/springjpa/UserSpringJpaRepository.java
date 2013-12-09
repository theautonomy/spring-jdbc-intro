package org.wei.spring.jdbc.dao.springjpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wei.spring.jdbc.domain.User;

public interface UserSpringJpaRepository extends JpaRepository<User, Long>  { 
	
    public User findByPin(@Param("pin") int pin);
    
    @Query("FROM User")
    public List<User> findAllUsers();
    
    @Modifying
    @Transactional
    @Query("UPDATE User u set u.name = :name WHERE u.pin = :pin")
    public int udpateUserByPin(@Param("name") String name, @Param("pin") int pin);
}

