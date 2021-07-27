package com.mycompany.store.service.impl;

import com.mycompany.store.domain.OrderItem;
import com.mycompany.store.repository.OrderItemRepository;
import com.mycompany.store.service.OrderItemService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderItem}.
 */
@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private final Logger log = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        log.debug("Request to save OrderItem : {}", orderItem);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Optional<OrderItem> partialUpdate(OrderItem orderItem) {
        log.debug("Request to partially update OrderItem : {}", orderItem);

        return orderItemRepository
            .findById(orderItem.getId())
            .map(
                existingOrderItem -> {
                    if (orderItem.getQuantity() != null) {
                        existingOrderItem.setQuantity(orderItem.getQuantity());
                    }
                    if (orderItem.getTotalPrice() != null) {
                        existingOrderItem.setTotalPrice(orderItem.getTotalPrice());
                    }
                    if (orderItem.getStatus() != null) {
                        existingOrderItem.setStatus(orderItem.getStatus());
                    }

                    return existingOrderItem;
                }
            )
            .map(orderItemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderItem> findAll(Pageable pageable) {
        log.debug("Request to get all OrderItems");
        return orderItemRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItem> findOne(Long id) {
        log.debug("Request to get OrderItem : {}", id);
        return orderItemRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        orderItemRepository.deleteById(id);
    }
}
