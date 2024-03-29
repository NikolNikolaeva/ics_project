package com.vmwareTalantBoost.ics.Image.Repositories;

import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u where u.id = ?1")
    User findUserById(Long id);

    @Query("SELECT s FROM User s where s.username = ?1")
    User findUserByUsername(String username);

    @Query("SELECT u FROM User u where u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u where u.username = ?1")
    Optional<User> findUserByUsernameExist(String username);

    @Query("SELECT u FROM User u order by u.username ASC ")
    List<User> findAllUsers();

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.email = ?2")
    Optional<User> findUserExistByUsernameAndEmail(String username, String email);


}
