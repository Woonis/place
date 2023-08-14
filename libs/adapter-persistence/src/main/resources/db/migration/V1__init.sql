DROP TABLE IF EXISTS place_statistics;
CREATE TABLE place_statistics
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    keyword     VARCHAR(500) NOT NULL,
    count       BIGINT       NOT NULL,
    created_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);
CREATE INDEX idx_place_statistics_01 ON place_statistics (keyword);
CREATE INDEX idx_place_statistics_02 ON place_statistics (count DESC);
