package sample.wooni.place.naver.converter;

import lombok.experimental.UtilityClass;
import sample.wooni.place.common.ValueConvertUtils;
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
        var formattedTitle = ValueConvertUtils.convertTitle(detail.title());
        return PlaceSearchResultDetailDto.builder()
                .type(PlaceSearchType.NAVER)
                .keyword(formattedTitle)
                .address(ValueConvertUtils.convertAddress((detail.address())))
                .roadAddress(ValueConvertUtils.convertAddress((detail.roadAddress())))
                .x(BigDecimal.valueOf(detail.mapx()))
                .y(BigDecimal.valueOf(detail.mapy()))
                .build();
    }
}
