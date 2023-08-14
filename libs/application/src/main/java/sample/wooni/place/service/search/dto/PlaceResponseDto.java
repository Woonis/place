package sample.wooni.place.service.search.dto;

import lombok.Builder;

import java.util.List;

public record PlaceResponseDto(
        String keyword,
        PlaceSearchType type,
        int hitCount,
        List<String> relatedKeyword
) {
    @Builder
    public PlaceResponseDto {
    }
}
