

CREATE TABLE Campaign (
   id INT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(255),
   expire_date DATETIME,
   description TEXT
);

INSERT INTO campaign (title, expire_date, description)
VALUES ('New Year Campaign', '2026-12-31 23:59:59', 'Special campaign for New Year discounts.');

-- ایجاد جدول UrlShorter
CREATE TABLE Url_Shorter (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ShortCode VARCHAR(255),
    LongCode TEXT,
    Count INT DEFAULT 0,
    CampaignID INT,
    FOREIGN KEY (CampaignID) REFERENCES Campaign(ID)
);