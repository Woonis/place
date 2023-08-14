package sample.wooni.place.statistics;

import org.springframework.stereotype.Component;
import sample.wooni.place.entity.statistics.PlaceStatistics;
import sample.wooni.place.service.output.statistics.PlaceStatisticsOutput;

import java.util.List;
import java.util.Optional;

@Component
public class PlaceStatisticsRepository implements PlaceStatisticsOutput {
    private final PlaceStatisticsJpaRepository repository;

    public PlaceStatisticsRepository(PlaceStatisticsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PlaceStatistics> findOneBy(String keyword) {
        return repository.findOneBy(keyword);
    }

    @Override
    public List<PlaceStatistics> fetchTopKeyword(int limit) {
        return repository.fetchTopKeyword(limit);
    }

    @Override
    public PlaceStatistics save(PlaceStatistics placeStatistics) {
        return repository.save(placeStatistics);
    }
}
