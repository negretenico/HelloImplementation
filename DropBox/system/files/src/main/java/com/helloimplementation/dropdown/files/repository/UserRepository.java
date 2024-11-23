package com.helloimplementation.dropdown.files.repository;

import com.helloimplementation.dropdown.files.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
