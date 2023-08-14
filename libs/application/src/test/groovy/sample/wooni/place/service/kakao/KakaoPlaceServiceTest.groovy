package sample.wooni.place.service.kakao

import org.assertj.core.util.Lists
import sample.wooni.place.service.output.kakao.ExternalKakaoPlaceOutput
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto
import sample.wooni.place.service.search.dto.PlaceSearchType
import spock.lang.Specification

class KakaoPlaceServiceTest extends Specification {
    KakaoPlaceService sut
    ExternalKakaoPlaceOutput output

    def "setup"() {
        output = Mock(ExternalKakaoPlaceOutput.class)

        sut = new KakaoPlaceService(output)
    }

    def "type"() {
        when:
        var result = sut.type()

        then:
        result == PlaceSearchType.KAKAO
    }

    def "order"() {
        when:
        var result = sut.order()

        then:
        result == 1
    }

    def "search"() {
        given:
        1 * output.search("Test") >> Lists.newArrayList(
                PlaceSearchResultDetailDto.builder().keyword("Test").build()
        )

        when:
        var result = sut.search("Test")

        then:
        result.size() == 1
    }

}
