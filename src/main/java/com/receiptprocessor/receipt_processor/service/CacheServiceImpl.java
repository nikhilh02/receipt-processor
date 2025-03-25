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
    public void saveReceiptPoints(String receiptId, int points){
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(receiptId, Integer.toString(points));
    }

    @Override
    public int getReceiptPoints(String receiptId) throws NullPointerException{
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        String strPoints = operation.get(receiptId);
        if(strPoints == null){
            throw new NullPointerException();
        }
        return Integer.parseInt(strPoints);
    }
}
