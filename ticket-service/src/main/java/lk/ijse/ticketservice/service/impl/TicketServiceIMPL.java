package lk.ijse.ticketservice.service.impl;

import lk.ijse.ticketservice.ENUM.TicketStatus;
import lk.ijse.ticketservice.service.TicketService;

import lk.ijse.ticketservice.conversion.ConversionData;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.Ticket;
import lk.ijse.ticketservice.repository.TicketServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketServiceIMPL implements TicketService {
    @Autowired
    private TicketServiceDAO ticketRepository;
    @Autowired
    private ConversionData conversionData;
    @Override
    public void saveTicket(TicketDTO ticketDTO) {
        ticketRepository.save(conversionData.mapTo(ticketDTO, Ticket.class));
    }

    @Override
    public TicketDTO getTicket(String ticketId) {
        return conversionData.mapTo(ticketRepository.findById(ticketId).get(), TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        return conversionData.mapTo(ticketRepository.findAll(), TicketDTO.class);
    }

    @Override
    public void updateTicket(TicketDTO ticketDTO) {
        if (!ticketRepository.existsById(ticketDTO.getTicketId())){
            return;
        }
        ticketRepository.save(conversionData.mapTo(ticketDTO, Ticket.class));
    }

    @Override
    public void deleteTicket(String ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<TicketDTO> getTicketsByUserId(String userId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.findAllByUserId(userId);
        for (Ticket ticket : tickets) {
            tickeDTOs.add(conversionData.mapTo(ticket, TicketDTO.class));
        }
        return tickeDTOs;
    }

    @Override
    public List<TicketDTO> getTicketsByVehicleId(String vehicleId) {
        List<TicketDTO> tickeDTOs = new ArrayList<>();
        List<Ticket> tickets = ticketRepository.findAllByVehicleId(vehicleId);
        for (Ticket ticket : tickets) {
            tickeDTOs.add(conversionData.mapTo(ticket, TicketDTO.class));
        }
        return tickeDTOs;
    }

    @Override
    public boolean isTicketExists(String ticketId) {
        if (ticketRepository.existsById(ticketId)){
            TicketDTO ticket = getTicket(ticketId);
            ticket.setTicketStatus(TicketStatus.PAID);
            updateTicket(ticket);
            return true;
        }
        return false;
    }
}