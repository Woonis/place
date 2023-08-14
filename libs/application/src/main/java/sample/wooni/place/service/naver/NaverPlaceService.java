package sample.wooni.place.service.naver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sample.wooni.place.service.output.naver.ExternalNaverPlaceOutput;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;
import sample.wooni.place.service.search.dto.PlaceSearchType;
import sample.wooni.place.service.search.external.ExternalPlaceSearchService;

import java.util.List;

@Slf4j
@Service
public class NaverPlaceService implements ExternalPlaceSearchService {
    private final ExternalNaverPlaceOutput placeOutput;

    public NaverPlaceService(ExternalNaverPlaceOutput placeOutput) {
        this.placeOutput = placeOutput;
    }

    @Override
    public PlaceSearchType type() {
        return PlaceSearchType.NAVER;
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
