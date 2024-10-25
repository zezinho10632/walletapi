package com.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wallet.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
