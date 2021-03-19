package com.prajyot.jpa.JPAin10steps.service;

import com.prajyot.jpa.JPAin10steps.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposetory extends JpaRepository<User,Long> {

}
