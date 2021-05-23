https://cups.mail.ru/ru/workareas/scbhack/602/1072

Преамбула

Имеется система, возвращающая данные по пользователям.

На вход принимает идентификатор пользователя, на выход:

Фамилию;
Имя;
Набор других данных.
Система представлена SOAP веб-сервисом. Адрес SOAP веб-сервиса будет передаваться вашему приложению через переменную окружения ws.endpoint. Описание сервиса приведено в приложенном к задаче файле soapService.wsdl.

Помимо этого есть другая система, работающая по REST API, и позволяющая получить список номеров телефонов пользователя. Причем в таком порядке, что первым в списке идет основной номер телефона, а потом все остальные. Адрес REST сервиса получения номеров телефонов будет передаваться вашему приложению через переменную окружения rs.endpoint. Описание API приведено в файле приложенном к задаче файле open-api.json.





Задача

Необходимо написать и развернуть веб-приложение, которое обращается к этим двум системам, получает данные по пользователю и по его номерам телефонов, агрегирует их и возвращает. Ваше веб-приложение должно иметь REST API с 1 эндпоинтом:

GET /user/{id}
- где {id} это идентификатор пользователя, который используется для поиска в вышеуказанных системах. И возвращать ответ в json формате, содержащем три поля:

{
"code" : 0,
"name" : "Иван Иванов",
"phone" : "+7 (495) 111-11-11"
}
code - это код ответа.

0 - успешно
1 - таймаут системы
2 - ошибка получения данных от системы
Важно!

Следует учитывать, что основные данные пользователя являются критичными и если SOAP веб-сервис долго не отвечает, или отвечает ошибкой, то необходимо вернуть соответствующий код.
При этом, данные о номерах телефонов не являются критичными, и если сервис получения телефонов недоступен, или долго не отвечает, это не должно приводить к возврату ошибки. Просто данные об основном телефоне в ответе не заполняются.
Таймаут к обеим системам следует установить в 5 секунд.



name - это имя пользователя, склеенное по маске "{firstName} {lastName}". От системы имя и фамилия будут приходить в отдельных полях.



phone - основной телефон пользователя. Если система вернет несколько телефонов, нужно взять первый из них. Если не вернет ни одного, либо ответит ошибкой 404, то не заполнять это поле.

Запрос основных данных и данных о номере телефона не зависят друг от друга, поэтому должны выполняться параллельно.

Также необходимо закрыть ваше приложение Basic авторизацией и прописать пользователя с логином testuser и паролем password123



Описание запуска решения

Ваше решение будет компилироваться и запускаться в виде Docker-контейнера, поэтому рекомендуем придерживаться той же структуры папок и файлов, которая используется в прилагаемом к задаче архиве starter_kit.zip.

При соблюдении этой рекомендации вам не придется вносить изменения в файл Dockerfile (есть в архиве). Если же вы хотите иначе организовать свой проект или использовать систему сборки, отличную от Maven, то вам понадобится скорректировать Dockerfile под свои нужды.



Формирование итогового рейтинга в лидерборде

Тот результат, который вам отображается в при обсчете решения у нас в системе, не является финальным.

Максимум по умолчанию вы сможете набрать за загруженное решение 300 баллов. Баллы начисляются по итогам прохождения тестов у нас в системе проверки по следующим ступеням:

Проверка авторизации - 30 баллов
Проверка результата работы API - 50 баллов
Тест на таймаут веб-сервиса - 30 баллов
Тест на таймаут REST сервиса - 30 баллов
Тест на ошибку от веб-сервиса - 30 баллов
Тест на ошибку от REST сервиса - 30 баллов
Тест на параллельность вызовов сервисов - 50 баллов
Нагрузочный тест - 50 баллов
Важно! Тесты проходят последовательно друг за другом. Если какой-то из тестов не пройден, следующие за ним проверку не проходят.

После завершения раунда начнется подведение финального итога.

Мы выбираем ваше лучшее решение из списка загруженных. Результат этого решения будет умножаться на коэффициент, который зависит от времени после которого было загружено решение, и количества пройденных ступеней вашим решением.

Стартовый коэффициент в зависимости от ступени:

Проверка авторизации: 1,6
Проверка результата работы API: 2,2
Тест на таймаут веб-сервиса: 2,8
Тест на таймаут REST сервиса: 3,4
Тест на ошибку от веб-сервиса: 4
Тест на ошибку от REST сервиса: 4,6
Тест на параллельность вызовов сервисов: 5,2
Нагрузочный тест: 5,8
За каждую минуту после начала раунда от коэффициента отнимается по 0,0025. Всего в раунде 240 минут.

Приведем пример расчета. Вы загрузили решение, которое прошло 4 ступени (Проверка авторизации, Проверка результата работы API, Тест на таймаут веб-сервиса, Тест на таймаут REST сервиса) и получили за него 140 баллов. До конца раунда осталось 120 минут, т.е. коэффициент 3,4 стал 3,1. Итого за это решение вы получите 140*3,1=434 балла.

Финальные баллы отобразятся в лидерборде задачи после оглашения победителей 25 мая в 12:45 по МСК.

Желаем удачи!