package sample.wooni.place.service.search.dto;

import lombok.Builder;

import java.math.BigDecimal;

public record PlaceSearchResultDetailDto(
        PlaceSearchType type,
        String keyword,
        String address,
        String roadAddress,
        BigDecimal x,
        BigDecimal y
) {

    @Builder
    public PlaceSearchResultDetailDto {
    }
}
