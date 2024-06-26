#language: ru

  Функциональность: Получение информации о питомце

    Сценарий: Получение информации о питомце
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
      Когда получаем информацию о питомце по petId вернет 200
      Тогда json соответствует
      """json
      {
        "id": ${petId},
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
