package lk.ijse.ticketservice.entity;

import jakarta.persistence.*;
import lk.ijse.ticketservice.ENUM.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {
    @Id
    private String ticketId;
    private LocalDate ticketIssueDate;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
    private String vehicleId;
    private String userId;
}