package sample.wooni.place.service.output.statistics;

import sample.wooni.place.entity.statistics.PlaceStatistics;

import java.util.List;
import java.util.Optional;

public interface PlaceStatisticsOutput {
    Optional<PlaceStatistics> findOneBy(String keyword);
    List<PlaceStatistics> fetchTopKeyword(int limit);
    PlaceStatistics save(PlaceStatistics placeStatistics);
}
