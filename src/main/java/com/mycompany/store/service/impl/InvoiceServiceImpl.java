package com.mycompany.store.service.impl;

import com.mycompany.store.domain.Invoice;
import com.mycompany.store.repository.InvoiceRepository;
import com.mycompany.store.service.InvoiceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Invoice}.
 */
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        log.debug("Request to save Invoice : {}", invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> partialUpdate(Invoice invoice) {
        log.debug("Request to partially update Invoice : {}", invoice);

        return invoiceRepository
            .findById(invoice.getId())
            .map(
                existingInvoice -> {
                    if (invoice.getDate() != null) {
                        existingInvoice.setDate(invoice.getDate());
                    }
                    if (invoice.getDetails() != null) {
                        existingInvoice.setDetails(invoice.getDetails());
                    }
                    if (invoice.getStatus() != null) {
                        existingInvoice.setStatus(invoice.getStatus());
                    }
                    if (invoice.getPaymentMethod() != null) {
                        existingInvoice.setPaymentMethod(invoice.getPaymentMethod());
                    }
                    if (invoice.getPaymentDate() != null) {
                        existingInvoice.setPaymentDate(invoice.getPaymentDate());
                    }
                    if (invoice.getPaymentAmount() != null) {
                        existingInvoice.setPaymentAmount(invoice.getPaymentAmount());
                    }
                    if (invoice.getCode() != null) {
                        existingInvoice.setCode(invoice.getCode());
                    }

                    return existingInvoice;
                }
            )
            .map(invoiceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Invoice> findAll(Pageable pageable) {
        log.debug("Request to get all Invoices");
        return invoiceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> findOne(Long id) {
        log.debug("Request to get Invoice : {}", id);
        return invoiceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Invoice : {}", id);
        invoiceRepository.deleteById(id);
    }
}
