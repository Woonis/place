package sample.wooni.place.service.kakao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.place.service.output.kakao.ExternalKakaoPlaceOutput;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.dto.PlaceSearchType;
import sample.wooni.place.service.search.external.ExternalPlaceSearchService;

import java.util.List;

@Slf4j
@Service
public class KakaoPlaceService implements ExternalPlaceSearchService {
    private final ExternalKakaoPlaceOutput placeOutput;

    public KakaoPlaceService(ExternalKakaoPlaceOutput placeOutput) {
        this.placeOutput = placeOutput;
    }

    @Override
    public PlaceSearchType type() {
        return PlaceSearchType.KAKAO;
    }

    @Override
    public int order() {
        return this.type().getOrder();
    }

    @Override
    public List<PlaceSearchResultDetailDto> search(String keyword) {
        log.info("search to Process : {}, keyword: {}" , type(), keyword);
        return placeOutput.search(keyword);
    }
}
