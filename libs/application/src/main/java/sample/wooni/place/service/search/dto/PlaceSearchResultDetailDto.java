package sample.wooni.place.service.search.dto;

import lombok.Builder;

public record PlaceSearchResultDetailDto(
        PlaceSearchType type,
        String keyword,
        String address,
        String roadAddress
) {

    @Builder
    public PlaceSearchResultDetailDto {
    }
}
