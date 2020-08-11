package ru.reidj.springapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reidj.springapplication.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);

    User findByActivationCode(String code);
}
