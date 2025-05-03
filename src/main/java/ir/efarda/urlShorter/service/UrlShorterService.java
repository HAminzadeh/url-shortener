package ir.efarda.urlShorter.service;


import ir.efarda.urlShorter.entity.*;
import ir.efarda.urlShorter.repository.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UrlShorterService {

    private final UrlShorterRepository urlShorterRepository;
    private final RequestTracker requestTracker;

    private static final String URL_PREFIX = "http://localhost:8989/";

    public UrlShorterService(UrlShorterRepository urlShorterRepository, RequestTracker requestTracker) {
        this.urlShorterRepository = urlShorterRepository;
        this.requestTracker = requestTracker;
    }

    public List<UrlShorter> getAllUrls() {
        return urlShorterRepository.findAll();
    }

    public UrlShorter saveUrl(UrlShorter urlShorter) {
        return urlShorterRepository.save(urlShorter);
    }

    public void deleteUrl(Integer id) {
        urlShorterRepository.deleteById(id);
    }

    public UrlShorter getUrlById(Integer id) {
        return urlShorterRepository.findById(id).orElse(null);
    }

    public String shortenUrl(String longUrl) {
        String shortCode = Integer.toHexString(longUrl.hashCode());

        UrlShorter fromDb = urlShorterRepository.findFirstByShortCode(shortCode);

        if (fromDb == null) {
            UrlShorter shorter = new UrlShorter();
            shorter.setCampaignId(1);
            shorter.setCount(0);
            shorter.setShortCode(shortCode);
            shorter.setLongCode(longUrl);
            urlShorterRepository.save(shorter);
            return URL_PREFIX + shortCode;
        } else {
            return URL_PREFIX + shortCode;
        }

    }

    public String redirectToOriginalUrl(String shortCode, HttpServletRequest request) {
        UrlShorter fromDb = urlShorterRepository.findFirstByShortCode(shortCode);
        if (fromDb != null) {
            String ip = request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            // ساخت کلید یکتا بر اساس IP + User-Agent + shortCode
            String requestKey = ip + "|" + userAgent + "|" + shortCode;

            if (!requestTracker.isDuplicate(requestKey)) {
                urlShorterRepository.incrementCount(shortCode);
            }

        }
        return urlShorterRepository.getLongCodeByShortCode(shortCode);
    }
}

