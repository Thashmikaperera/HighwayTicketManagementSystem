package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleServiceDAO extends JpaRepository<Vehicle, String> {
    List<Vehicle> findAllByUserId(String userId);
}