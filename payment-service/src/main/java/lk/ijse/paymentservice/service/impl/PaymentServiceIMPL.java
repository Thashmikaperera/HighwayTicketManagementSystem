package lk.ijse.paymentservice.service.impl;

import lk.ijse.paymentservice.conversion.ConversionData;
import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.entity.Payment;
import lk.ijse.paymentservice.repository.PaymentServiceDAO;
import lk.ijse.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceIMPL implements PaymentService {
    @Autowired
    private PaymentServiceDAO paymentServiceDao;
    @Autowired
    private ConversionData conversionData;
    @Override
    public void savePayment(PaymentDTO paymentDTO) {
        paymentServiceDao.save(conversionData.mapTo(paymentDTO, Payment.class));
    }

    @Override
    public List<PaymentDTO> getAllPayment() {
        return conversionData.mapTo(paymentServiceDao.findAll(), PaymentDTO.class);
    }

    @Override
    public PaymentDTO getPayment(String paymentId) {
        return conversionData.mapTo(paymentServiceDao.findById(paymentId).get(), PaymentDTO.class);
    }
}