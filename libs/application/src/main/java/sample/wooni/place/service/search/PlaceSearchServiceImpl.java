package sample.wooni.place.service.search;


import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.place.service.search.dto.PlaceResponseDto;
import sample.wooni.place.service.search.dto.PlaceSearchDto;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.external.ExternalPlaceSearchService;
import sample.wooni.place.service.statistics.PlaceStatisticsService;
import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlaceSearchServiceImpl implements PlaceSearchService {
    private List<ExternalPlaceSearchService> searchServices;
    private final PlaceStatisticsService statisticsService;

    public PlaceSearchServiceImpl(List<ExternalPlaceSearchService> searchServices,
                                  PlaceStatisticsService statisticsService) {
        this.searchServices = searchServices;
        this.statisticsService = statisticsService;
    }

    @PostConstruct
    void afterProperties() {
        this.searchServices = searchServices.stream()
                .sorted(Comparator.comparing(ExternalPlaceSearchService::order))
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceResponseDto> search(PlaceSearchDto request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.keyword())) {
            throw new IllegalArgumentException("검색 조건이 유효하지 않습니다.");
        }

        var searchResults = new ArrayList<PlaceSearchResultDetailDto>();
        searchServices.forEach(
                it -> searchResults.addAll(it.search(request.keyword()))
        );

        var addressGrouping = searchResults.stream()
                .collect(Collectors.groupingBy(PlaceSearchResultDetailDto::roadAddress));

        var result = new ArrayList<PlaceResponseDto>();
        for (PlaceSearchResultDetailDto detail : searchResults) {
            var keywords = addressGrouping.get(detail.roadAddress());

            if (Objects.nonNull(keywords)) {
                result.add(buildResponse(keywords));
                addressGrouping.put(detail.roadAddress(), null);
            }
        }

        result.sort(Comparator.comparing(PlaceResponseDto::hitCount).reversed());

        statisticsService.save(
                StatisticsCreateCommand.builder()
                        .keyword(request.keyword())
                        .build()
        );
        return result;
    }

    private PlaceResponseDto buildResponse(List<PlaceSearchResultDetailDto> keywords) {
        var firstItem = keywords.get(0);

        return PlaceResponseDto.builder()
                .keyword(firstItem.keyword())
                .type(firstItem.type())
                .hitCount(keywords.size())
                .relatedKeyword(keywords.subList(1, keywords.size()).stream().map(this::buildKey).toList())
                .build();
    }

    private String buildKey(PlaceSearchResultDetailDto detail) {
        return String.format("%s (%s)", detail.keyword(), detail.type());
    }
}
