## Объектно-ориентированный анализ и проектирование распределенного приложения ##
### Задание №13 ###
Электронная платежная система. Операции удаленного объекта: создание счета с начальной суммой, пополнение счета, выполнение платежа с указанием получателя и цели, просмотр истории операций. Сериализуемый объект: платеж (получатель, цель, сумма). Пополнение счета считать особым видом
платежа и также отображать в истории операций.

### Модель предметной области ###
В процессе анализа был получен следующий первоначальный список понятий:

- Платежная система
- Счет
- Платеж
- Цель платежа
- История платежей

Разместим понятия на диаграмме классов: 

![](https://github.com/zkoalexey/base_of_payment_system/blob/master/diagr.png)

### Варианты использования ###

**Аутентификация пользователя**

1. Пользователь вводит логин и пароль для входа в систему
2. Система проверяет правильность логина и пароля
3. Система выдает токен клиенту

*Альтернатива:* 3а. Логин и/или пароль неверны - выдать ошибку

**Завершение работы**

1. Клиент отсылает токен с командой завершения сессии
2. Система проверяет, что данный токен активен
3. Система удаляет информацию о связи токена с привязанным пользователем

*Альтернатива:* 3а. Токен неактивен - не производить никаких действий

Примечание: Перед каждым из дальнейших действий клиент должен отправить токен на проверку. Лишь в случае активности токена будет произведено действие, иначе - отказ.


**Добавление пользователя**

1. Пользователь вводит логин, пароль и начальную сумму
2. Система проверяет, что введенный логин не занят
3. Система добавляет пользователя с начальной суммой

*Альтернатива:* 3а. Логин занят - выдать ошибку

**Выполнение платежа**

1. Пользователь вводит логин пользователя, которому предназначается платеж, сумму и цель платежа
2. Система проверяет, что имеется такой пользователь, а также наличие средств на счету отправителя
3. Со счета отправителя списываются средства, на счет получателя зачисляются средства, и выполняется запись об операции в историю

*Альтернатива:* 3а. Нет такого пользователя или недостаточно средств - выдать ошибку

**Пополнение счета**

1. Пользователь вводит сумму
2. На счет данного пользователя зачисляется соответствующая сумма, выполняется запись в историю операций

**Отображение истории операций**

1. Пользователь может ввести информацию о периоде, слова, фигурирующие в цели или логин получателя
2. Система выдает информацию об операциях с участием данного пользователя с введенными фильтрами

### Диаграмма классов проектирования

![](https://github.com/zkoalexey/base_of_payment_system/blob/master/diagr2.png)

### Диаграмма последовательностей

![](https://github.com/zkoalexey/base_of_payment_system/blob/master/usecase.png)

*Рис.3.Успешная аутентификация*

![](https://github.com/zkoalexey/base_of_payment_system/blob/master/usecase2.png)

*Рис.3. Неуспешная аутентификация*

![](https://github.com/zkoalexey/base_of_payment_system/blob/master/usecase3.png)

*Рис.3.Успешный запрос истории*