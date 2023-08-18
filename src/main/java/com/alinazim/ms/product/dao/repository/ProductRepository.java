package com.alinazim.ms.product.dao.repository;

import com.alinazim.ms.product.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
