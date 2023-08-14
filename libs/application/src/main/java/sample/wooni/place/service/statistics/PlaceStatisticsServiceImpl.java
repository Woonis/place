package sample.wooni.place.service.statistics;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionOperations;
import sample.wooni.place.service.output.statistics.PlaceStatisticsOutput;
import sample.wooni.place.service.statistics.converter.PlaceStatisticsConverter;
import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand;
import sample.wooni.place.service.statistics.dto.StatisticsDto;

import java.util.List;
import java.util.Objects;

@Service
public class PlaceStatisticsServiceImpl implements PlaceStatisticsService {

    private final PlaceStatisticsOutput placeStatisticsOutput;
    private final TransactionOperations readOperation;
    private final TransactionOperations writeOperation;

    public PlaceStatisticsServiceImpl(PlaceStatisticsOutput placeStatisticsOutput,
                                      TransactionOperations readOperation,
                                      TransactionOperations writeOperation) {
        this.placeStatisticsOutput = placeStatisticsOutput;
        this.readOperation = readOperation;
        this.writeOperation = writeOperation;
    }

    @Override
    public List<StatisticsDto> topSearchKeywords(int limit) {
        var response = readOperation.execute( status -> placeStatisticsOutput.fetchTopKeyword(limit));
        return PlaceStatisticsConverter.convert(response);
    }

    @Override
    public StatisticsDto save(StatisticsCreateCommand command) {
        var response = writeOperation.execute(status -> {
            var exists = placeStatisticsOutput.findOneBy(command.keyword()).orElse(null);
            if (Objects.nonNull(exists)) {
                exists.increaseCount();
                return exists;
            }

            var request = PlaceStatisticsConverter.convert(command, NumberUtils.INTEGER_ONE);
            return placeStatisticsOutput.save(request);
        });

        return PlaceStatisticsConverter.convert(response);
    }
}
