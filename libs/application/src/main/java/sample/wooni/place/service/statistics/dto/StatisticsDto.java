package sample.wooni.place.service.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.ZonedDateTime;

public record StatisticsDto(
        String keyword,
        int count,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        ZonedDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        ZonedDateTime modifiedAt
) {
    @Builder
    public StatisticsDto {
    }
}
