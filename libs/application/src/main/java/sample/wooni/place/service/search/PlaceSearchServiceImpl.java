package sample.wooni.place.service.search;


import com.google.common.collect.Lists;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.place.service.search.dto.PlaceResponseDto;
import sample.wooni.place.service.search.dto.PlaceSearchDto;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.external.ExternalPlaceSearchService;

import java.util.*;
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
    public List<PlaceResponseDto> search(PlaceSearchDto request) {
        var searchResults = new ArrayList<PlaceSearchResultDetailDto>();
        searchServices.forEach(
                it -> searchResults.addAll(it.search(request.keyword()))
        );

        Map<String, List<PlaceSearchResultDetailDto>> addressToDetailMap = searchResults.stream().collect(
                Collectors.groupingBy(PlaceSearchResultDetailDto::roadAddress)
        );

        return addressToDetailMap.values().stream()
                .filter(it -> it.size() > 0)
                .map(this::buildResponse)
                .sorted(Comparator.comparing(PlaceResponseDto::hitCount).reversed())
                .toList();
    }

    private PlaceResponseDto buildResponse(List<PlaceSearchResultDetailDto> details) {
        var firstItem = details.get(0);

        return PlaceResponseDto.builder()
                .keyword(firstItem.keyword())
                .hitCount(details.size())
                .type(firstItem.type())
                .relatedKeyword(details.size() > 1 ? details.subList(1, details.size()).stream().map(this::buildKeyword).toList() : Lists.newArrayList())
                .build();
    }

    private String buildKeyword(PlaceSearchResultDetailDto detail) {
        return String.format("%s (%s)", detail.keyword(), detail.type());
    }

}
