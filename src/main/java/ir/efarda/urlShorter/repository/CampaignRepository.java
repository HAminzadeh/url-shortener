package ir.efarda.urlShorter.repository;


import ir.efarda.urlShorter.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
}
