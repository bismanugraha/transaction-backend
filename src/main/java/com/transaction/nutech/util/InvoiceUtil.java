package com.transaction.nutech.util;

import org.springframework.stereotype.Component;

@Component
public class InvoiceUtil {
    public String invoiceGenerator(String transactionType, String date, Long id) {
        StringBuilder invoice = new StringBuilder();

        invoice.append("INV");
        switch (transactionType) {
            case "TOPUP":
                invoice.append("-TU-");
            case "PAYMENT":
                invoice.append("-P-");
        }

        invoice.append(date);
        invoice.append("-");
        invoice.append(String.format("%04d", id));

        return invoice.toString();
    }
}
