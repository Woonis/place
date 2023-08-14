# Place
- KaKao와 Naver의 open api를 사용한 장소 검색 서비스를 제공합니다.

# 환경
- Java 17 / Spring Boot 3.0.4
- gradle.kotlin 버젼 기반
- DB: H2, flyway
- Spock 기반 테스트 케이스

# 빌드 결과물


# 프로젝트 구성
```
place
├── apps                  
│   └── place-api             - place app(Controller)
├── buildSrc                  - 그래들 의존성 버전 관리
└── libs
    ├── adapter-http          - http를 사용한 외부 통신
    ├── adapter-persistence   - jpa 기반의 db 통신
    └── application           - 어플리케이션의 비즈니스 로직
```

# 특징
1. KAKAO, NAVER 오픈 api의 연동을 통해 각 장소 검색을 수행할 수 있다.
2. 각 장소 검색 api 수행시, 자동으로 통계 데이터가 적재되어진다.
3. 키워드의 연관성은 주소를 기반으로 한다.
4. 통계에 대한 디비는 H2디비의 로컬 디비를 사용하며, flyway의 수행을 통해 첫 데이터는 자동 init되어 진다.
5. 통계 디비에 대한 쿼리는 query dsl을 사용하여 쿼리를 수행하게 된다.
6. 테스트는 spock을 통해 어플리케이션 단의 테스트 코드를 수행한다.

# API spec
## 1. 장소 검색

### 요청
``
GET /api/v1/place
``

Parameter

| Name      | Type      | Description | Required |
|-----------|-----------|-------------|----------|
| `keyword` | `String`  | 검색 키워드      | O        |
| `limit`   | `Integer` | 결과 갯수       | X        |


Response
```json
{
  "success": true,
  "code": 200,
  "message": null,
  "data": [
    {
      "keyword": "별미곱창 본점",
      "type": "KAKAO",
      "hitCount": 3,
      "relatedKeyword": [
        "별미곱창 (NAVER)",
        "별미곱창 3호점 (NAVER)"
      ]
    }
  ]
}
```


## 2. 검색어 통계 조회
``
GET /api/v1/place/statistics/top-keyword
``


Parameter

| Name    | Type     | Description              | Required |
|---------|----------|--------------------------|----------|
| `limit` | `Number` | 인기 검색의 제한 수              | X        |

Response
```json
{
  "success": true,
  "code": 200,
  "message": null,
  "data": [
    {
      "keyword": "치킨",
      "count": 10000000,
      "createdAt": "2023-08-14 00:00:00",
      "modifiedAt": "2023-08-14 00:00:00"
    },
    {
      "keyword": "곱창",
      "count": 20000,
      "createdAt": "2023-08-14 00:00:00",
      "modifiedAt": "2023-08-14 00:00:00"
    }
  ]
}
```