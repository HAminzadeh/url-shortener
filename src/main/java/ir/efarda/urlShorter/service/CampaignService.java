package ir.efarda.urlShorter.service;


import ir.efarda.urlShorter.entity.*;
import ir.efarda.urlShorter.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Integer id) {
        campaignRepository.deleteById(id);
    }

    public Campaign getCampaignById(Integer id) {
        return campaignRepository.findById(id).orElse(null);
    }
}
