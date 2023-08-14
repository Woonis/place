package sample.wooni.place.service.search.dto;

import lombok.Builder;

import java.util.List;

public record PlaceResponseDto(
        List<String> results
) {
    @Builder
    public PlaceResponseDto(List<String> results) {
        this.results = results;
    }
}
