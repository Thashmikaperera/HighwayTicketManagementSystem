package lk.ijse.ticketservice.repository;

import lk.ijse.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketServiceDAO extends JpaRepository<Ticket, String> {
    List<Ticket> findAllByUserId(String userId);
    List<Ticket> findAllByVehicleId(String vehicleId);
}
