package sample.wooni.place.service.search

import org.assertj.core.util.Lists
import sample.wooni.place.service.kakao.KakaoPlaceService
import sample.wooni.place.service.naver.NaverPlaceService
import sample.wooni.place.service.search.dto.PlaceSearchDto
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto
import sample.wooni.place.service.search.dto.PlaceSearchType
import sample.wooni.place.service.search.external.ExternalPlaceSearchService
import sample.wooni.place.service.statistics.PlaceStatisticsService
import sample.wooni.place.service.statistics.dto.StatisticsDto
import spock.lang.Specification

class PlaceSearchServiceImplTest extends Specification {
    PlaceSearchServiceImpl sut
    List<ExternalPlaceSearchService> searchServices
    KakaoPlaceService kakaoPlaceService
    NaverPlaceService naverPlaceService
    PlaceStatisticsService statisticsService

    def "setup"() {
        kakaoPlaceService = Mock(KakaoPlaceService.class)
        naverPlaceService = Mock(NaverPlaceService.class)
        searchServices = Lists.newArrayList(kakaoPlaceService, naverPlaceService)

        statisticsService = Mock(PlaceStatisticsService.class)

        sut = new PlaceSearchServiceImpl(searchServices, statisticsService)
    }

    def "search"() {
        given:
        1 * kakaoPlaceService.search("Test") >> Lists.newArrayList(buildDetailDto("Test", PlaceSearchType.KAKAO))
        1 * naverPlaceService.search("Test") >> Lists.newArrayList(buildDetailDto("Test 1", PlaceSearchType.NAVER))
        1 * statisticsService.save(_) >> StatisticsDto.builder().keyword("Test").count(1).build()

        when:
        var result = sut.search(new PlaceSearchDto("Test", 10))

        then:
        noExceptionThrown()
        result.size() == 1
        result.get(0).keyword() == "Test"
        result.get(0).type() == PlaceSearchType.KAKAO
        result.get(0).hitCount() == 2
        result.get(0).relatedKeyword().size() == 1

    }

    def "search with null"() {
        given:
        0 * kakaoPlaceService.search("Test") >> Lists.newArrayList(buildDetailDto("Test", PlaceSearchType.KAKAO))
        0 * naverPlaceService.search("Test") >> Lists.newArrayList(buildDetailDto("Test 1", PlaceSearchType.NAVER))
        0 * statisticsService.save(_) >> StatisticsDto.builder().keyword("Test").count(1).build()

        when:
        sut.search(new PlaceSearchDto(keyword, 10))

        then:
        thrown(IllegalArgumentException.class)

        where:
        keyword | _
        ""      | _
        null    | _
    }

    private PlaceSearchResultDetailDto buildDetailDto(String keyword, PlaceSearchType type) {
        return PlaceSearchResultDetailDto.builder()
                .keyword(keyword)
                .type(type)
                .roadAddress("경기 성남시 분당구 분당내곡로 131 판교테크원타워 11층")
                .address("경기 성남시 분당구 백현동 534 판교테크원타워 11층")
                .build()
    }

}
