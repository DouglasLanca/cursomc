package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletPaymentService {
    public void fillBilletPayment(BilletPayment payment, Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        payment.setEndDate(cal.getTime());
    }
}
