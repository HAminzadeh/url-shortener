package ir.efarda.urlShorter.repository;

import ir.efarda.urlShorter.entity.UrlShorter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UrlShorterRepository extends JpaRepository<UrlShorter, Integer> {

    @Query("SELECT u.longCode FROM UrlShorter u WHERE u.shortCode = :shortCode")
    String getLongCodeByShortCode(String shortCode);

    UrlShorter findFirstByShortCode(String shortCode);

    @Modifying
    @Transactional
    @Query("UPDATE UrlShorter c SET c.count = c.count + 1 WHERE c.shortCode = :shortCode")
    void incrementCount(@Param("shortCode") String shortCode);
}
