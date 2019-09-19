package com.restapi.agriculture.repostory;

import com.restapi.agriculture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByIdUser(Integer id);
}
