package ir.efarda.urlShorter.controller;

import ir.efarda.urlShorter.entity.Campaign;
import ir.efarda.urlShorter.entity.UrlShorter;
import ir.efarda.urlShorter.service.CampaignService;
import ir.efarda.urlShorter.service.RequestTracker;
import ir.efarda.urlShorter.service.UrlShorterService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UrlController {

    private final CampaignService campaignService;
    private final UrlShorterService urlShorterService;


    public UrlController(CampaignService campaignService, UrlShorterService urlShorterService) {
        this.campaignService = campaignService;
        this.urlShorterService = urlShorterService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam String longUrl) {
        return urlShorterService.shortenUrl(longUrl);
    }

    @GetMapping("/{shortCode}")
    public void redirectToOriginalUrl(@PathVariable String shortCode, HttpServletResponse response, HttpServletRequest request) {

        String originalUrl = urlShorterService.redirectToOriginalUrl(shortCode,request);

        if (originalUrl != null) {
            response.setStatus(HttpServletResponse.SC_FOUND);  // 302 status برای redirect
            response.setHeader("Location", originalUrl);  // آدرس مقصد برای redirect
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // اگر کوتاه‌سازی پیدا نشد
        }
    }

    @GetMapping("/campaigns")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @PostMapping("/campaigns")
    public Campaign createCampaign(@RequestBody Campaign campaign) {
        return campaignService.saveCampaign(campaign);
    }

    @GetMapping("/campaigns/{id}")
    public Campaign getCampaignById(@PathVariable Integer id) {
        return campaignService.getCampaignById(id);
    }

    @DeleteMapping("/campaigns/{id}")
    public void deleteCampaign(@PathVariable Integer id) {
        campaignService.deleteCampaign(id);
    }

    // CRUD for UrlShorter
    @GetMapping("/urls")
    public List<UrlShorter> getAllUrls() {
        return urlShorterService.getAllUrls();
    }

}
