package sample.wooni.place.statistics;

import sample.wooni.place.entity.statistics.PlaceStatistics;

import java.util.List;
import java.util.Optional;

public interface PlaceStatisticsCustomRepository {
    Optional<PlaceStatistics> findOneBy(String keyword);
    List<PlaceStatistics> fetchTopKeyword(int limit);
}
