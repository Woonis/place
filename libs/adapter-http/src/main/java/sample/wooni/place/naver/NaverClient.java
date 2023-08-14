package sample.wooni.place.naver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sample.wooni.place.naver.converter.NaverPlaceConverter;
import sample.wooni.place.service.output.naver.ExternalNaverPlaceOutput;
import sample.wooni.place.service.output.naver.response.NaverPlaceResponse;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NaverClient implements ExternalNaverPlaceOutput {
    private final String searchUrl;
    private final String clientId;
    private final String secretKey;
    private final RestTemplate restTemplate;

    public NaverClient(@Value("${api.naver.search-url}") String searchUrl,
                       @Value("${api.naver.client-id}") String clientId,
                       @Value("${api.naver.secret-key}") String secretKey,
                       RestTemplate restTemplate) {
        this.searchUrl = searchUrl;
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name= "NaverClient", fallbackMethod = "defaultSearchResult")
    public List<PlaceSearchResultDetailDto> search(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            throw new IllegalArgumentException("query 값은 필수 입니다.");
        }

        var response = restTemplate.exchange(
                buildUri(keyword),
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                new ParameterizedTypeReference<NaverPlaceResponse>() {
                }
        ).getBody();

        return NaverPlaceConverter.convert(response);
    }

    public List<PlaceSearchResultDetailDto> defaultSearchResult(String keyword, Exception e) {
        log.error("Failed to call external NAVER api, keyword={}, message={}", keyword, e.getMessage(), e);
        return Lists.newArrayList();
    }

    private String buildUri(String query) {
        return UriComponentsBuilder.fromUriString(searchUrl)
                .queryParam("query", query)
                .queryParam("display", 5)
                .build()
                .toUriString();
    }

    private HttpHeaders buildHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("X-Naver-Client-Id", clientId);
        headers.put("X-Naver-Client-Secret", secretKey);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        return httpHeaders;
    }

}
