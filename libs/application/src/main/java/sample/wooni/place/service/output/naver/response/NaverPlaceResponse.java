package sample.wooni.place.service.output.naver.response;

import java.beans.ConstructorProperties;
import java.util.List;

public record NaverPlaceResponse(
        String title,
        String link,
        String description,
        String lastBuildDate,
        Integer total,
        Integer start,
        Integer display,
        List<NaverPlaceDetail> items
) {

    @ConstructorProperties({"title", "link", "description", "lastBuildDate", "total", "start", "display", "items"})
    public NaverPlaceResponse(String title,
                              String link,
                              String description,
                              String lastBuildDate,
                              Integer total,
                              Integer start,
                              Integer display,
                              List<NaverPlaceDetail> items) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = items;
    }
}
