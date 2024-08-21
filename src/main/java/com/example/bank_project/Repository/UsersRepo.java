package com.example.bank_project.Repository;

import com.example.bank_project.Entity.Client;
import com.example.bank_project.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByGmail(String gmail);

}
