package lk.ijse.paymentservice.repository;

import lk.ijse.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentServiceDAO extends JpaRepository<Payment,String> {
}
