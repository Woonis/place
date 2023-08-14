package sample.wooni.place.statistics;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;
import sample.wooni.place.entity.statistics.PlaceStatistics;

import java.util.List;
import java.util.Optional;

import static sample.wooni.place.entity.statistics.QPlaceStatistics.placeStatistics;


@Repository
public class PlaceStatisticsCustomRepositoryImpl implements PlaceStatisticsCustomRepository {
    private final JPAQueryFactory queryFactory;

    public PlaceStatisticsCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<PlaceStatistics> findOneBy(String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(placeStatistics.keyword.eq(keyword));

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(placeStatistics)
                        .where(builder)
                        .limit(NumberUtils.INTEGER_ONE)
                        .fetchOne()
        );
    }

    @Override
    public List<PlaceStatistics> fetchTopKeyword(int limit) {
        return queryFactory
                .selectFrom(placeStatistics)
                .orderBy(placeStatistics.count.desc(), placeStatistics.id.desc())
                .limit(limit)
                .fetch();
    }
}
