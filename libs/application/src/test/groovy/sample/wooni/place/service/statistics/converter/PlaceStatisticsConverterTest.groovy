package sample.wooni.place.service.statistics.converter

import org.assertj.core.util.Lists
import sample.wooni.place.entity.statistics.PlaceStatistics
import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand
import spock.lang.Specification

class PlaceStatisticsConverterTest extends Specification {

    def "convert"() {
        when:
        var result = PlaceStatisticsConverter.convert(build(keyword, count))

        then:
        noExceptionThrown()
        result.keyword() == keyword
        result.count() == count

        where:
        keyword               | count
        "keyword"             | 1000
        "test"                | 200
    }

    def "convert multiple"() {
        when:
        var result = PlaceStatisticsConverter.convert(
                Lists.newArrayList(
                        build("keyword", 1000),
                        build("test", 200)
                )
        )

        then:
        noExceptionThrown()
        result.size() == 2
        result.get(0).keyword() == "keyword"
        result.get(1).keyword() == "test"
    }

    def "convert"() {
        when:
        var result = PlaceStatisticsConverter.convert(
                StatisticsCreateCommand.builder().keyword("keyword").build(),
                10
        )

        then:
        noExceptionThrown()
        result.getKeyword() == "keyword"
        result.getCount() == 10
    }

    private PlaceStatistics build(String keyword, int count) {
        return PlaceStatistics.builder()
                .keyword(keyword)
                .count(count)
                .build()
    }
}
