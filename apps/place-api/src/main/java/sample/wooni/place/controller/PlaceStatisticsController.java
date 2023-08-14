package sample.wooni.place.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sample.wooni.place.controller.common.ResultResponse;
import sample.wooni.place.service.statistics.PlaceStatisticsService;
import sample.wooni.place.service.statistics.dto.StatisticsCreateCommand;
import sample.wooni.place.service.statistics.dto.StatisticsDto;

import java.util.List;

@RestController
public class PlaceStatisticsController {
    private final PlaceStatisticsService service;

    public PlaceStatisticsController(PlaceStatisticsService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/place/statistics/top-keyword")
    public ResultResponse<List<StatisticsDto>> topSearchKeywords(@RequestParam(required = false, defaultValue = "10") int limit) {
        return ResultResponse.ok(service.topSearchKeywords(limit));
    }

    @PostMapping("/api/v1/place/statistics")
    public ResultResponse<StatisticsDto> save(@Valid @RequestBody StatisticsCreateCommand request) {
        return ResultResponse.ok(service.save(request));
    }
}
