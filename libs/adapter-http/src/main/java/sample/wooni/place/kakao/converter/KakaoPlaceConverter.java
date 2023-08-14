package sample.wooni.place.kakao.converter;

import lombok.experimental.UtilityClass;
import sample.wooni.place.common.GeoConvertUtil;
import sample.wooni.place.service.output.kakao.response.KakaoPlaceDetail;
import sample.wooni.place.service.output.kakao.response.KakaoPlaceResponse;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.dto.PlaceSearchType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class KakaoPlaceConverter {
    public static List<PlaceSearchResultDetailDto> convert(KakaoPlaceResponse response) {
        if (Objects.isNull(response)) {
            return null;
        }

        var documents = response.documents();
        return documents.stream()
                .map(KakaoPlaceConverter::convert)
                .collect(Collectors.toList());
    }

    private PlaceSearchResultDetailDto convert(KakaoPlaceDetail detail) {
        GeoConvertUtil.GeoDetailDto convertedGeo = GeoConvertUtil.convert(detail.x(), detail.y());
        return PlaceSearchResultDetailDto.builder()
                .type(PlaceSearchType.KAKAO)
                .keyword(detail.placeName().trim())
                .category(detail.categoryName())
                .address(detail.addressName())
                .roadAddress(detail.roadAddressName())
                .x(convertedGeo.x())
                .y(convertedGeo.y())
                .build();
    }
}
