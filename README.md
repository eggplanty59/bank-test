Тестовое задание:
Реализовать серверную часть по работе с вкладами в банке.

На уровне базы данных (кандидат выбирает БД на свое усмотрение) должны быть реализованы следующие сущности:
  1. Клиенты
    a. Наименование
    b. Краткое наименование
    c. Адрес
    d. Организационно-правовая форма (выбор из списка)
  2. Банки
    a. Наименование
    b. БИК
  3. Вклад
    a. Клиент(ссылка)
    b. Банк(ссылка)
    c. Дата открытия
    d. Процент
    e. Срок в месяцах

Примерный сценарий работы пользователя:
  Пользователь заходит в реестр, может отсортировать и/или отфильтровать выводимые сущности. Он может отредактировать, создать новую, удалить существующую сущность.
  
Использованные технологии:
  1. Java 8
  2. Spring Boot
  3. JPA
  4. Maven
  5.Базы данных: Postres, H2
  
Описание api:

Запрос объектов:

  POST api/v1/bank/list
  POST api/v1/customer/list
  POST api/v1/deposit/list
	
    Тело запроса:
		{
       "filterCriteria" : //хренение данных о фильтрации
        {
           "operation" : String, //операция для объединения критериев: "or", "and"
           "filterCriteriaList" : [ //массив критериев
             {
                "key" : String, //атрибут для фильтрации 
                "operation" : String, //оперция фильтрации: ">", "<", "=", "!="
                 "value" : Object  //значения для сравнения
             }
             ],
            "filterParamsList" : [//массив объединенных данных о фильтрации, реализует выделение операции в скобки
            {
               "operation" : String, //операция для объединения критериев: "or", "and"
               "filterCriteriaList" : [ //массив критериев
                {
                   "key" : String, //атрибут для фильтрации 
                   "operation" : String, //оперция фильтрации: ">", "<", "=", "!="
                    value" : Object //значения для сравнения
                }
              ]
            }
           ]
          }
        ,
        "orders": [ //массив сортировок
            {
                "orderBy": String, //атрибут для сортировки 
                "orderDir": String // направление сортировки: "asc", "desc"
            }
        ]
			}
		Тело ответа:
			Банк:
			[
    		{
        		"id": Integer, // идентификатор
        		"name": String, // название
        		"bic": String // БИК
    		}
    	]
			
			Клиент:
			[
				{
						"id": Integer, //идентификатор
						"name": String, //название
						"shortName": String, //Краткое наименование
						"address": String, //адрес
						"formOfIncorporation": String //Организационно-правовая форма
				}
			]
			
			Вклад:
			[
				{
						"id": Integer, //идентификатор
						"customer": {//клиент, атрибуты описаны выше
								"id": Integer,
								"name": String,
								"shortName": String,
								"address": String,
								"formOfIncorporation": String
						},
						"bank": {//банк, атрибуты описаны выше
								"id": Integer,
								"name": String,
								"bic": String
						},
						"openDate": Data,//дата открытия
						"percent": Float, //процент
						"term": Integer //срок
				}
		]
		
Создание объектов:
  POST api/v1/bank/
  POST api/v1/customer/
	POST api/v1/deposit/
    Тело запроса:
			Банк:
			{
    		"name" : String,
    		"bic" : String
			}

			Клиент:
			{
				"name" : String,
				"shortName" : String,
				"address" : String,
				"formOfIncorporation" : String
			}
			
			Вклад:
			{
				"customer" : {
						"id":1
				},
				"bank" : {
						"id" : 1
				},
				"openDate" : "2019-12-29 19:20",
				"percent" : 10.5,
				"term" : 8
			}
			
		Тело ответа: аналогично телу запроса с добавлением присвоенного идентификатора: "id" : Integer

	Изменение объектов:
  	PUT api/v1/bank/
  	PUT api/v1/customer/
		PUT api/v1/deposit/
		
		Тело запроса:
			Банк:
			{
				"id" : Integer
    		"name" : String,
    		"bic" : String
			}

			Клиент:
			{
				"id" : Integer
				"name" : String,
				"shortName" : String,
				"address" : String,
				"formOfIncorporation" : String
			}
			
			Вклад:
			{
				"id" : Integer
				"customer" : {
						"id":1
				},
				"bank" : {
						"id" : 1
				},
				"openDate" : "2019-12-29 19:20",
				"percent" : 10.5,
				"term" : 8
			}
		Тело ответа: аналогично телу запроса
		
	Удаление объектов:
  	DELETE api/v1/bank/{id} 
  	DELETE api/v1/customer/{id} 
	DELETE api/v1/deposit/{id} 
			
