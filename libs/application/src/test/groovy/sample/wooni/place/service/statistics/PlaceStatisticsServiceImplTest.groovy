package sample.wooni.place.service.statistics

import org.assertj.core.util.Lists
import org.springframework.transaction.support.TransactionOperations
import sample.wooni.place.entity.statistics.PlaceStatistics
import sample.wooni.place.service.output.statistics.PlaceStatisticsOutput
import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand
import spock.lang.Specification

import java.time.ZonedDateTime

class PlaceStatisticsServiceImplTest extends Specification {
    PlaceStatisticsServiceImpl sut
    PlaceStatisticsOutput placeStatisticsOutput
    TransactionOperations readOperation
    TransactionOperations writeOperation

    def "setup"() {
        placeStatisticsOutput = Mock(PlaceStatisticsOutput.class)
        readOperation = Mock(TransactionOperations.class)
        writeOperation = Mock(TransactionOperations.class)

        sut = new PlaceStatisticsServiceImpl(placeStatisticsOutput, readOperation, writeOperation)
    }

    def "topSearchKeywords"() {
        given:
        PlaceStatistics statistics = Mock(PlaceStatistics.class)
        statistics.getId() >> 1
        statistics.getKeyword() >> "keyword"
        statistics.getCount() >> 1
        statistics.getCreatedAt() >> ZonedDateTime.now()
        statistics.getModifiedAt() >> ZonedDateTime.now()

        1 * readOperation.execute(_) >> Lists.newArrayList(statistics)

        when:
        var result = sut.topSearchKeywords(10)

        then:
        noExceptionThrown()
        result.size() == 1
        result.get(0).keyword() == "keyword"
        result.get(0).count() == 1
    }

    def "save"() {
        given:
        var request = StatisticsCreateCommand.builder()
                .keyword("test")
                .build()

        1 * writeOperation.execute (_) >> PlaceStatistics.builder()
                .keyword("test")
                .count(100)
                .build()

        when:
        sut.save(request)

        then:
        noExceptionThrown()
    }
}
