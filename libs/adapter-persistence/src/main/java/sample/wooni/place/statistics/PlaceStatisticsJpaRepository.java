package sample.wooni.place.statistics;


import org.springframework.data.jpa.repository.JpaRepository;
import sample.wooni.place.entity.statistics.PlaceStatistics;

public interface PlaceStatisticsJpaRepository extends JpaRepository<PlaceStatistics, Long>, PlaceStatisticsCustomRepository {
}
