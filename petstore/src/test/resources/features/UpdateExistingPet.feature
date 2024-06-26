#language: ru

Функциональность: Обновление информации о питомце

  Сценарий: Обновление информации о питомце
    Пусть добавляем нового питомца вернет 200
      """json
      {
        "id": 0,
        "category": {
          "id": 0,
          "name": "string"
        },
        "name": "Vovka",
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """
    Пусть обновляем информацию о существующем питомце вернет 200
      """json
      {
        "id": ${petId},
        "category": {
          "id": 0,
          "name": "string"
        },
        "name": "testDudka",
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """
    Тогда json соответствует
      """json
      {
        "id": ${petId},
        "category": {
          "id": 0,
          "name": "string"
        },
        "name": "testDudka",
        "photoUrls": [
          "string"
        ],
        "tags": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "status": "available"
      }
      """