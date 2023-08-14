package sample.wooni.place.service.output.kakao;

import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;

import java.util.List;

public interface ExternalKakaoPlaceOutput {
    List<PlaceSearchResultDetailDto> search(String keyword);
}
