package lk.ijse.vehicleservice.repository;

import lk.ijse.vehicleservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleServiceDAO extends JpaRepository<Vehicle, String> {
    List<Vehicle> findAllByUserId(String userId);
}