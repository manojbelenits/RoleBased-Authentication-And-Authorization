package org.belen.rolebasedaccess.repo;

import org.apache.catalina.User;
import org.belen.rolebasedaccess.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepo extends JpaRepository<UserEntity,Long> {


    @Query("select usrs from UserEntity usrs where usrs.username=:uname")
    public Optional<UserEntity> findUserByUname(String uname);

    boolean existsByUsername(String username);
}
