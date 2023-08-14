package sample.wooni.place.service.search.external;

import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.dto.PlaceSearchType;

import java.util.List;

public interface ExternalPlaceSearchService {
    PlaceSearchType type();
    int order();
    List<PlaceSearchResultDetailDto> search(String keyword);
}
