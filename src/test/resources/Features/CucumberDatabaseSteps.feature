# language: ru

Функционал: Управление бд

Предыстория:
* открыта страница по адресу "http://localhost:8080/"

  @Db
  @vegetable
  Сценарий: Добавление нового объекта в бд
    * выполнено нажатие на раздел "Песочница"
    * выбран подраздел "Товары"
    * проверка, что страница с товарами открыта
    * выполнено нажатие на "Добавить"
    * проверка, что страница добавления товара открыта
    * поле "Название" заполнено значением "Артишок"
    * выбран тип продукта "Овощ"
    * продукт отмечен как экзотический
    * кнопка сохранить нажата
    * в таблице "FOOD" есть элемент с именем "Артишок"
    * выполнено удаление элемента с именем "Артишок" из таблицы "FOOD"
    * в таблице "FOOD" нет элемента с именем "Артишок"


    @Db
    Сценарий: Добавление существующего объекта в бд
      * выполнено нажатие на раздел "Песочница"
      * выбран подраздел "Товары"
      * проверка, что страница с товарами открыта
      * выполнено нажатие на "Добавить"
      * проверка, что страница добавления товара открыта
      * поле "Название" заполнено значением "Яблоко"
      * выбран тип продукта "Фрукт"
      * кнопка сохранить нажата
      * выполнена проверка на дубли