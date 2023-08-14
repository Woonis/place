package sample.wooni.place.service.statistics.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record StatisticsCreateCommand(
        @NotBlank String keyword
) {
    @Builder
    public StatisticsCreateCommand {
    }
}
