#language: ru

  Функциональность: Добавление нового питомца в магазин

    Сценарий: Добавление нового питомца в магазин
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