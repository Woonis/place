package sample.wooni.place.service.search;


import sample.wooni.place.service.search.dto.PlaceResponseDto;
import sample.wooni.place.service.search.dto.PlaceSearchDto;

import java.util.List;

public interface PlaceSearchService {
    List<PlaceResponseDto> search(PlaceSearchDto request);
}
