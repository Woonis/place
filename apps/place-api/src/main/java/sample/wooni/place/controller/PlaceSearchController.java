package sample.wooni.place.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.wooni.place.controller.common.ResultResponse;
import sample.wooni.place.service.search.PlaceSearchService;
import sample.wooni.place.service.search.dto.PlaceResponseDto;
import sample.wooni.place.service.search.dto.PlaceSearchDto;

import java.util.List;

@Slf4j
@RestController
public class PlaceSearchController {

    private final PlaceSearchService service;

    public PlaceSearchController(PlaceSearchService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/place")
    public ResultResponse<List<PlaceResponseDto>> search(@Valid PlaceSearchDto request) {
        return ResultResponse.ok(service.search(request));
    }
}
