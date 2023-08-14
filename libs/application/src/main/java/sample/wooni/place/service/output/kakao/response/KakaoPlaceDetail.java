package sample.wooni.place.service.output.kakao.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.ConstructorProperties;

public record KakaoPlaceDetail(
        String id,
        @JsonProperty("place_name") String placeName,
        @JsonProperty("category_name") String categoryName,
        @JsonProperty("category_group_code") String categoryGroupCode,
        @JsonProperty("category_group_name") String categoryGroupName,
        String phone,
        @JsonProperty("address_name") String addressName,
        @JsonProperty("road_address_name") String roadAddressName,
        String x,
        String y,
        @JsonProperty("place_url") String placeUrl,
       String distance
) {
    @ConstructorProperties({"id", "placeName", "categoryName", "categoryGroupCode", "categoryGroupName", "phone", "addressName", "roadAddressName", "x", "y", "placeUrl", "distance"})
    public KakaoPlaceDetail(String id,
                            String placeName,
                            String categoryName,
                            String categoryGroupCode,
                            String categoryGroupName,
                            String phone,
                            String addressName,
                            String roadAddressName,
                            String x,
                            String y,
                            String placeUrl,
                            String distance) {
        this.id = id;
        this.placeName = placeName;
        this.categoryName = categoryName;
        this.categoryGroupCode = categoryGroupCode;
        this.categoryGroupName = categoryGroupName;
        this.phone = phone;
        this.addressName = addressName;
        this.roadAddressName = roadAddressName;
        this.x = x;
        this.y = y;
        this.placeUrl = placeUrl;
        this.distance = distance;
    }
}
