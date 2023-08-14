package sample.wooni.place.service.output.naver;

import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;

import java.util.List;

public interface ExternalNaverPlaceOutput {
    List<PlaceSearchResultDetailDto> search(String keyword);
}
