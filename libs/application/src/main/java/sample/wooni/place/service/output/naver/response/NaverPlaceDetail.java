package sample.wooni.place.service.output.naver.response;

import java.beans.ConstructorProperties;

public record NaverPlaceDetail(
        String title,
        String link,
        String category,
        String description,
        String telephone,
        String address,
        String roadAddress,
        Integer mapx,
        Integer mapy
) {

    @ConstructorProperties({"title", "link", "category", "description", "telephone", "address", "roadAddress", "mapx", "mapy"})
    public NaverPlaceDetail(String title,
                            String link,
                            String category,
                            String description,
                            String telephone,
                            String address,
                            String roadAddress,
                            Integer mapx,
                            Integer mapy) {
        this.title = title;
        this.link = link;
        this.category = category;
        this.description = description;
        this.telephone = telephone;
        this.address = address;
        this.roadAddress = roadAddress;
        this.mapx = mapx;
        this.mapy = mapy;
    }
}
