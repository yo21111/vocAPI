# VOC API
 - 인입된 클레임을 바탕으로 voc를 생성합니다.
 - voc는 배상정보와 이의제기를 포함하는 Entity입니다.
 - 이의제기를 등록하는 경우 voc에 이의제기 날짜가와 내용이 수정됩니다.(null -> '내용')

## 사용한 기술
  - Java 15
  - Spring boot 2.7.0
  - queryDsl 5.0.0
  - MySQL 8.0.27 (Schema : voc)

## 0. Entity별 용어 정리
```json
{
  {
        "id": 12,
        "company_id": "3446d08c-6c99-4677-827d-40b1596445e3",
        "classification": "고객사",
        "unique_id": "6c888807-0e14-4149-b591-6c0a559e2c13",
        "compensation": {
            "id": 12,
            "company_id": "e6d39351-7b90-4122-b5c8-2054ca40160e",
            "classification": "고객사",
            "unique_id": "aaa2edd5-914c-47d5-8758-90c556e7598b",
            "penalty": {
                "id": 11,
                "shipping_no": "fbb10954-bc90-4939-bfd6-1ba09d3655f8",
                "penalty_Content": "배송 중 물건 누락",
                "penalty_price": 10000,
                "register_date": "2022-06-26"
            },
            "register_date": "2022-06-26"
        },
        "objection": {
            "id": 12,
            "unique_id": "b38677e6-a7c7-4098-a678-fa7b33ae6592",
            "shipping_no": "fbb10954-bc90-4939-bfd6-1ba09d3655f8",
            "confirm_date": "2022-06-26",
            "objection_content": "이의제기 합니다."
        }
    }, ....
  }
  ```
  - ### voc Entity
  |Name|Type|Description|
  |------|---|---|
  |id|Long|voc id|  
  |company_id|String|클레입 회사 아이디|
  |classification|String|클레임 회사 분류 / "고객사", "운송사"|
  |unique_id|String|회사 직원번호 / 고객사 : 담당매니저, 운송사 : 운송기사|
  |compensation|compensation|해당 voc의 배상정보 Entity|
  |objection|objection|해당 voc의 이의제기 Entity|
  
  - ### 배상정보 Entity - Compensation
  |Name|Type|Description|
  |------|---|---|
  |id|Long|배상정보 id|
  |company_id|String|클레입 받은 회사 아이디|
  |classification|String|클레임 받은 회사 분류 / "고객사", "운송사"|
  |unique_id|String|회사 직원번호 / 고객사 : 담당매니저, 운송사 : 운송기사|
  |penalty|penalty|배상정보의 배상청구 Entity|
  |register_date|LocalDateTime|배상정보 등록 일자|
  
  - ### 배상청구 Entity - Penalty
  |Name|Type|Description|
  |------|---|---|
  |id|Long|배상청구 id|
  |shipping_no|String|물건 운송번호|
  |penalty_Content|String|귀책사유|
  |penalty_price|int|배상 청구 금액 (0보다 큰 경우 배상 청구 O)|
  |register_date|LocalDateTime|배상청구 등록 일자|  

  - ### 이의제기 Entity - Objection
  |Name|Type|Description|
  |------|---|---|  
  |id|Long| id|
  |unique_id|String|직원번호|
  |shipping_no|String|물건 운송번호|
  |confirm_date|LocalDateTime|배상청구 확인 |
  |objection_content|String|이의제기 |  

## 1. VOC 목록 조회 API
  - ### 요청 URL
    1. **GET** voc 목록 조회 - 전체목록 : http://localhost:9090/voc
    2. **GET** "고객사" 또는 "운송사" VOC 목록만 확인 : http://localhost:9090/voc/{classification}
    3. **GET** voc 목록 조회 - 기사 확인 이전 : http://localhost:9090/voc/unchecked
    4. **GET** voc 목록 조회 - 기사 확인 + 이의제기 O : http://localhost:9090/voc/checked
    5. **GET** voc 목록 조회 - 기사 확인 + 이의제기 X : http://localhost:9090/voc/checked_noObj



## 2. 배상정보 목록 조회 API
  - ### 요청 URL
    1. **GET** 배상정보 리스트 조회 : http://localhost:9090/comp
    2. **GET** 배상 청구가 없는 배상정보 리스트 조회 : http://localhost:9090/comp/noPenalty
    3. **GET** 배상 청구가 있는 배상정보 리스트 조회 : http://localhost:9090/comp/penalty
    
## 3. 등록
  - ### 요청 URL
    1. **POST** voc 등록 : http://localhost:9090/voc
    2. **POST** 패널티 확인 여부 등록 + 이의 제기 등록 : http://localhost:9090/objection
  
