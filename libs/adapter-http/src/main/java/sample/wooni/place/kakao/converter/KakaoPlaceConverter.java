package sample.wooni.place.kakao.converter;

import lombok.experimental.UtilityClass;
import sample.wooni.place.common.ValueConvertUtils;
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
        return PlaceSearchResultDetailDto.builder()
                .type(PlaceSearchType.KAKAO)
                .keyword(ValueConvertUtils.convertTitle(detail.placeName()))
                .address(ValueConvertUtils.convertAddress(detail.addressName()))
                .roadAddress(ValueConvertUtils.convertAddress(detail.roadAddressName()))
                .build();
    }
}
