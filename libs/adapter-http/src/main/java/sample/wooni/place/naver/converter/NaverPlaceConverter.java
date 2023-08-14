package sample.wooni.place.naver.converter;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import sample.wooni.place.service.output.naver.response.NaverPlaceDetail;
import sample.wooni.place.service.output.naver.response.NaverPlaceResponse;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.dto.PlaceSearchType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class NaverPlaceConverter {
    public static List<PlaceSearchResultDetailDto> convert(NaverPlaceResponse response) {
        if (Objects.isNull(response)) {
            return null;
        }

        var items = response.items();
        return items.stream()
                .map(NaverPlaceConverter::convert)
                .collect(Collectors.toList());
    }
    private PlaceSearchResultDetailDto convert(NaverPlaceDetail detail) {
        var formattedTitle = reformatTitle(detail.title());
        return PlaceSearchResultDetailDto.builder()
                .type(PlaceSearchType.NAVER)
                .keyword(formattedTitle)
                .category(detail.category())
                .address(reformatAddress(detail.address(), formattedTitle))
                .roadAddress(reformatAddress(detail.roadAddress(), formattedTitle))
                .x(BigDecimal.valueOf(detail.mapx()))
                .y(BigDecimal.valueOf(detail.mapy()))
                .build();
    }

    // TODO 고도화 필요.
    private String reformatAddress(String address, String title) {
        return address.replaceAll(title, StringUtils.EMPTY);
    }

    private String reformatTitle(String value) {
        return value.trim().replaceAll("<[^>]*>", StringUtils.EMPTY);
    }
}
