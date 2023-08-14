package sample.wooni.place.service.search.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public record PlaceSearchDto(
        @NotBlank(message = "keyword는 필수 입니다.")
        String keyword,
        Integer limit
) {
        public int getLimitOrDefault() {
                return Objects.nonNull(limit) ? limit : 10;
        }
}
