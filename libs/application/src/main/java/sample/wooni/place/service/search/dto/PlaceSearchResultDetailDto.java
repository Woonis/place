package sample.wooni.place.service.search.dto;

import lombok.Builder;

import java.math.BigDecimal;

public record PlaceSearchResultDetailDto(
        PlaceSearchType type,
        String keyword,
        String category,
        String address,
        String roadAddress,
        BigDecimal x,
        BigDecimal y
) {

    @Builder
    public PlaceSearchResultDetailDto(PlaceSearchType type,
                                      String keyword,
                                      String category,
                                      String address,
                                      String roadAddress,
                                      BigDecimal x,
                                      BigDecimal y) {
        this.type = type;
        this.keyword = keyword;
        this.category = category;
        this.address = address;
        this.roadAddress = roadAddress;
        this.x = x;
        this.y = y;
    }
}
