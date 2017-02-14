package com.yida.demo.repository;

import com.yida.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by helloz on 2017/2/14.
 */
public interface UserRepository extends JpaRepository<User,Integer>{
}
