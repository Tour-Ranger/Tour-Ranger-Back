package com.tourranger.user.repository;

import com.tourranger.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom {
}
