package com.receiptprocessor.receipt_processor.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService{

    private final StringRedisTemplate redisTemplate;

    CacheServiceImpl(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveReceiptPoints(String receiptId, Double points){
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(receiptId, points.toString());
    }

    @Override
    public double getReceiptPoints(String receiptId){
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        String strPoints = operation.get(receiptId);
        return Double.parseDouble(strPoints);
    }
}
