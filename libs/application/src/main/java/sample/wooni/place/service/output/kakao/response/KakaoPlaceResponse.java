package sample.wooni.place.service.output.kakao.response;

import java.beans.ConstructorProperties;
import java.util.List;

public record KakaoPlaceResponse(
        KakaoMeta meta,
        List<KakaoPlaceDetail> documents
) {
    @ConstructorProperties({"meta", "documents"})
    public KakaoPlaceResponse {
    }
}
