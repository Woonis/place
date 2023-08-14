package sample.wooni.place.service.naver

import org.assertj.core.util.Lists
import sample.wooni.place.service.output.naver.ExternalNaverPlaceOutput
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto
import sample.wooni.place.service.search.dto.PlaceSearchType
import spock.lang.Specification

class NaverPlaceServiceTest extends Specification {
    NaverPlaceService sut
    ExternalNaverPlaceOutput output

    def "setup"() {
        output = Mock(ExternalNaverPlaceOutput.class)

        sut = new NaverPlaceService(output)
    }

    def "type"() {
        when:
        var result = sut.type()

        then:
        result == PlaceSearchType.NAVER
    }

    def "order"() {
        when:
        var result = sut.order()

        then:
        result == 2
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
