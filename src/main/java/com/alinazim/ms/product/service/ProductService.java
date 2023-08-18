package com.alinazim.ms.product.service;

import com.alinazim.ms.product.dao.entity.ProductEntity;
import com.alinazim.ms.product.dao.repository.ProductRepository;
import com.alinazim.ms.product.exception.NotFoundException;
import com.alinazim.ms.product.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.alinazim.ms.product.util.CommonUtil.COMMON_UTIL;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CacheUtil cacheUtil;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public ProductEntity saveProduct(ProductEntity product) {
        var cacheKey = COMMON_UTIL.getCacheKey("product", product.getName());
        cacheUtil.saveToCache(cacheKey, product, 100L, ChronoUnit.SECONDS);
        System.out.println("Data in cache : " + Optional.ofNullable(cacheUtil.getBucket(cacheKey)));
        return productRepository.save(product);
    }

    public ProductEntity updateProduct(Long id, ProductEntity product) {
        ProductEntity existingProduct = getProductById(id);
        BeanUtils.copyProperties(product, existingProduct, "id");
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

