package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    List<User> findUsersByCountryContainsOrCityContains(String country, String City);
    List<User> findUsersByBirthday(Date date);
    List<User> findUsersByGender(String gender);
    List<User> findUsersByFirstNameContainsOrLastNameContains(String firstName, String lastName);
}
