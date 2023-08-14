package sample.wooni.place.service.search;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.place.service.search.dto.PlaceResponseDto;
import sample.wooni.place.service.search.dto.PlaceSearchDto;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.external.ExternalPlaceSearchService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlaceSearchServiceImpl implements PlaceSearchService {
    private List<ExternalPlaceSearchService> searchServices;

    public PlaceSearchServiceImpl(List<ExternalPlaceSearchService> searchServices) {
        this.searchServices = searchServices;
    }

    @PostConstruct
    void afterProperties() {
        this.searchServices = searchServices.stream()
                .sorted(Comparator.comparing(ExternalPlaceSearchService::order))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceResponseDto search(PlaceSearchDto request) {
        var results = new ArrayList<PlaceSearchResultDetailDto>();
        searchServices.forEach(
                it -> results.addAll(it.search(request.keyword()))
        );

        log.info("size : {}", results.size());
        return null;
    }
}
