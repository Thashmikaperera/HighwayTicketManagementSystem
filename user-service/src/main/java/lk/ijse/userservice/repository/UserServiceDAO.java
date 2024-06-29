package lk.ijse.userservice.repository;

import lk.ijse.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserServiceDAO extends JpaRepository<User,String> {
}