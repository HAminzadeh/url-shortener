package ir.efarda.urlShorter.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RequestTracker {

    private final Map<String, Long> recentRequests = new ConcurrentHashMap<>();
    private final long ttlMillis = 2000; // 2 ثانیه

    public boolean isDuplicate(String key) {
        long now = System.currentTimeMillis();

        recentRequests.entrySet().removeIf(entry -> now - entry.getValue() > ttlMillis);

        if (recentRequests.containsKey(key)) {
            return true;
        } else {
            recentRequests.put(key, now);
            return false;
        }
    }
}
