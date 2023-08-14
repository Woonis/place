package sample.wooni.place.kakao;

import com.google.common.collect.Maps;
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
import sample.wooni.place.kakao.converter.KakaoPlaceConverter;
import sample.wooni.place.service.output.kakao.ExternalKakaoPlaceOutput;
import sample.wooni.place.service.output.kakao.response.KakaoPlaceResponse;
import sample.wooni.place.service.search.dto.PlaceSearchResultDetailDto;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class KakaoClient implements ExternalKakaoPlaceOutput {
    private final String searchUrl;
    private final String apiKey;
    private final RestTemplate restTemplate;

    public KakaoClient(@Value("${api.kakao.search-url}") String searchUrl,
                       @Value("${api.kakao.key}") String apiKey,
                       RestTemplate restTemplate) {
        this.searchUrl = searchUrl;
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PlaceSearchResultDetailDto> search(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            throw new IllegalArgumentException("keyword, url 값은 필수 입니다.");
        }

        var response = restTemplate.exchange(
                buildPlaceUrl(keyword),
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                new ParameterizedTypeReference<KakaoPlaceResponse>() {
                }
        ).getBody();

        return KakaoPlaceConverter.convert(response);
    }

    // TODO size 손봐야함.
    private String buildPlaceUrl(String query) {
        return UriComponentsBuilder.fromUriString(searchUrl)
                .queryParam("query", query)
                .queryParam("page", 1)
                .queryParam("size", 5)
                .build()
                .toUriString();
    }

    private HttpHeaders buildHeaders() {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", String.format("KakaoAK %s", apiKey));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        return httpHeaders;
    }
}
