package com.prajyot.jpa.JPAin10steps;

import com.prajyot.jpa.JPAin10steps.entity.User;
import com.prajyot.jpa.JPAin10steps.service.UserDAOService;
import com.prajyot.jpa.JPAin10steps.service.UserReposetory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDAOServiceCommandLineRunner2 implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserDAOServiceCommandLineRunner2.class);
    @Autowired
    UserReposetory userReposetory;
    @Override
    public void run(String... args) throws Exception {
        User user = new User("Kailash" +
                "", "Admin");
        userReposetory.save(user);
       log.info("New user created: "+user);

    }
}
