package sample.wooni.place.service.statistics.converter;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import sample.wooni.place.entity.statistics.PlaceStatistics;
import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand;
import sample.wooni.place.service.statistics.dto.StatisticsDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class PlaceStatisticsConverter {
    public static StatisticsDto convert(PlaceStatistics target) {
        if (Objects.isNull(target)) {
            return null;
        }

        return PlaceStatisticsConverter.convertDto(target);
    }
    public static List<StatisticsDto> convert(List<PlaceStatistics> targets) {
        if (CollectionUtils.isEmpty(targets)) {
            return Lists.newArrayList();
        }

        return targets.stream()
                .filter(Objects::nonNull)
                .map(PlaceStatisticsConverter::convertDto)
                .collect(Collectors.toList());
    }

    private StatisticsDto convertDto(PlaceStatistics target) {
        return StatisticsDto.builder()
                .keyword(target.getKeyword())
                .count(target.getCount())
                .createdAt(target.getCreatedAt())
                .modifiedAt(target.getModifiedAt())
                .build();
    }

    public PlaceStatistics convert(StatisticsCreateCommand command, int count) {
        return PlaceStatistics.builder()
                .keyword(command.keyword())
                .count(count)
                .build();
    }
}
