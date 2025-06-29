package org.voduybao.bookstorebackend.services.merchandise.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.repositories.merchandise.ProductRepository;
import org.voduybao.bookstorebackend.services.merchandise.ProductService;

@Component
@Slf4j(topic = "PRODUCT-SERVICE")
public class ProductServiceImpl implements ProductService {

    @Setter(onMethod_ = @Autowired)
    private ProductRepository productRepository;


}
