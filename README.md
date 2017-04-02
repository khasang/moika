#Moika Vision

##Постановка проблемы
###Решаемые проблемы:
* Автомобилисты не хотят стоять в очередях на автомойки
* Автомобилисты хотят планировать свое время
* Автомобилисты хотят минимизировать свои затраты на автомойку
* Владельцы автомоек хотят увеличит число клиентов
* Владельцы автомоек хотят удержать имеющихся клиентов
* Владельцы автомоек хотят оптимизировать загрузку моек
* Владельцы автомоек хотят иметь оперативную информацию о загрузке моек и cash-flow
* Таким образом увеличит доходность бизнеса
###Заинтересованные лица:
            Автомобилист
            Менеджер (администратор) автомойки
            Владелец сервиса автомойки
### Цели:
            Повышение удобства автомобилистов
            Увеличение числа клиентов сети автомоек
            Увеличение прибыли владельцев автомоек
### Краткое описание планируемого продукта
        Предполагаемый продукт представляет собой ИТ-сервис, позволяющий:
            Автомобилистам находить удобную для себя автомойку (из зарегистрированных) и записываться на нее в удобное для него время. При этом он может выбирать набор услуг по мойке/чистке авто и ему сразу считывается стоимость услуг. Так же, в зависимости от настроек, действует система бонусов и скидок. Например, в зависимости от частоты мойки, выбранного набора услуг, типа авто и т.д. и т.п. Так же автомобилист может оценивать работу мойки (в виде балов) и оставлять записи в «книге жалоб/предложений”.
            Менеджерам автомоек иметь оперативную информацию о загрузке боксов моек, количестве клиентов в очереди и т.п.
            Владельцам моек иметь оперативную информацию об общей загрузке сети (с детализацией), генерируемом ей cash-flow, статистику, тенденции и т.п. Они могут устанавливать бонусные программы и моделировать их параметры.
            Сервис также может являться рекламной площадкой, как для собственно рекламы сети автомоек, так и для рекламы партнеров.
### Цикл использования
            Автовладелец заходит в приложение и выбирает удобную себе мойку
            Регистрация в сервисе для автовладельцев не требуется (только ввод № авто) и кэпчу
            Автовладелец резервирует удобное время
            Выбирает набор услуг
            Получает расчет стоимости (с учетом скидок и т.п.)
            Фиксирует время
            При желании оплачивает (плат. карта, яндекс-деньги, pay-pal, СМС-кой?) За это получает еще доп. скидку
            Автовладелец может так-же записаться по телефону (но без скидок)
            Может настроить напоминания по СМС
            Может потом видеть статистику по своим помывкам и суммы оплат
            Может выставит оценку и оставить отзыв
            Менеджер видит регистрации клиентов и загрузку боксов
            Устанавливает режим работы боксов
            Регистрирует клиентов по телефону
            Если приезжает клиент «с улицы» регистрирует его
            Видит оценки клиентов.
            Может клиентам ставить всякие метки (типа VIP, black-list и т.п.)
            Владелец видит общую загрузку по сети
            Устанавливает скидочные программы
            Видит статистику и аналитику
###Описание пользователей
        Автомобилист – человек, имеющий авто, доступ к Интернет и мобильное устройство (смартфон/планшет).
        Менеджер мойки – человек, управляющий работой конкретной автомойки, ее персоналом, очередью клиентов, расчетами с ними и отвечающий за эффективность работы мойки, имеет доступ к Сервису либо через Интернет, либо через локальную сеть, как через WEB-интерфейс, так и через мобильные устройства.
        Владелец (может быть одно лицо с менеджером) - собственник сети, получает информацию о работе сети и определяет параметры ее функционирования (расписания работы, бонусные программы и т.п.)
###Функции продукта
        Сервис регистрации
        Сервис выбора моек (Geo)
        Сервис оплаты
        Сервис клиентской лояльности
        Сервис статистики
###Дополнительные функциональные требования к продукту
        Пользователи продукта общаются с сервисом через WEB-интерфейс или мобильные приложения
        Продут может предоставляться как «облачный сервис»
        Интеграция с платежными системами (интернет-эквайринг)
        Опционально – интеграция с кассовым оборудованием и бухгалтерскими системам

[ссылка](http://yandex.ru)
[![ссылка](https://cdn.everypony.ru/storage/00/44/24/2016/05/17/f1b099cd15.jpg)](http://yandex.ru/)

### new world

* menu
* menu
* menu
    * menu
    * menu
        * menu
1. menu
2. menu

# menu

| Day     | Car    | Price |
| --------|--------|-------|
| Monday  | VAZ   | $6    |
| Tuesday | EU | $8    |

## Контроллеры
### PsWashFaclilityController
Управляет сущностями - мойками через REST.
Запросs в JSON
```json
{"name":"Мока на Фонтанке","idNet":"1","description":"не фонтан","idAddr":"3",
      "washBoxes": [
                           {"idType":"1","boxName":"Бокс №1","description":"Первй бокс", "boxStatus":"1"},
                           {"idType":"2","boxName":"Бокс №2","description":"Второй бокс", "boxStatus":"1"},
                           {"idType":"3","boxName":"Бокс №3","description":"Третий бокс", "boxStatus":"2"},
                           {"idType":"1","boxName":"Бокс №4","description":"Большой бокс", "boxStatus":"3"}
                           ]
}
```

* /washFacility/
    * /list - список моек
    * /add - добавить
    * /update - изменить
    * /delete - удалить
    * /{id} - получить по id
    * / inCity/{city} - список моек в городе
    ````
    {"id"="1","name"="Москва"}
    ````
    * /byCoord/{coord} - получение мойки по координатам
     ```json
    {"lat":"50.34555", "long":"67.23655"}
     ```
    * /byAddr/{idAddr} - получение мойки по адресу, зпрегмтрированному в бд

    
    
    
     
 


