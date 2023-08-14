package sample.wooni.place.service.search;


import sample.wooni.place.service.search.dto.PlaceResponseDto;
import sample.wooni.place.service.search.dto.PlaceSearchDto;

public interface PlaceSearchService {
    PlaceResponseDto search(PlaceSearchDto request);
}
