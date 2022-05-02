package com.example.greatreads.Repository;

import com.example.greatreads.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
