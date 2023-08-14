package sample.wooni.place.service.output.kakao.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

public record KakaoMeta(
        @JsonProperty("total_count") int totalCount,
        @JsonProperty("pageable_count") int pageableCount,
        @JsonProperty("is_end") boolean isEnd,
        @JsonProperty("same_name") SameName sameName
){
    @ConstructorProperties({"totalCount", "pageableCount", "isEnd", "sameName"})
    public KakaoMeta(int totalCount, int pageableCount, boolean isEnd, SameName sameName) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
        this.sameName = sameName;
    }
}
