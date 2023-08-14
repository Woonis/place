package sample.wooni.place.service.search.dto;

import lombok.Getter;
public enum PlaceSearchType {
    KAKAO(1),
    NAVER(2);

    @Getter
    private final int order;

    PlaceSearchType(int order) {
        this.order = order;
    }
}
