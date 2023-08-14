package sample.wooni.place.service.statistics;


import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand;
import sample.wooni.place.service.statistics.dto.StatisticsDto;

import java.util.List;

public interface PlaceStatisticsService {
    List<StatisticsDto> topSearchKeywords(int limit);
    StatisticsDto save(StatisticsCreateCommand command);
}
