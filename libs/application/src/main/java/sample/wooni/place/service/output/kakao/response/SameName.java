package sample.wooni.place.service.output.kakao.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;
import java.util.List;

public record SameName(
        @JsonProperty("region") List<String> region,
        @JsonProperty("keyword") String keyword,
        @JsonProperty("selected_region") String selectedRegion
) {
    @ConstructorProperties({"region", "keyword", "selectedRegion"})

    public SameName(List<String> region,
                    String keyword,
                    String selectedRegion) {
        this.region = region;
        this.keyword = keyword;
        this.selectedRegion = selectedRegion;
    }
}
