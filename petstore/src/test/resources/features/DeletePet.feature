#language: ru

Функциональность: Удаляем информацию о питомце

  Сценарий: Удаляем информацию о питомце
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
    Когда удаляем информацию о питомце по petId вернет 200
    Тогда json соответствует
      """json
      {
          "code": 200,
          "type": "unknown",
          "message": "${petId}"
      }
      """
    Когда получаем информацию о питомце по petId вернет 404