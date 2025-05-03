package ir.efarda.urlShorter.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class UrlShorter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shortCode;

    @Lob
    private String longCode;

    private Integer count = 0;

    private Integer campaignId;
}
