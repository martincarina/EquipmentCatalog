# EquipmentCatalog

1. Введение
Приложение обеспечивает доступ к таблице 'unit' базе данных 'equipmentCatalog'.
Таблица содержит список оборудования.

2. Интерфейс приложения
В основное окно приложения выводится содержимое таблицы, слева расположены пять 
кнопок управления:
- 'Add' - вызывает диалоговое окно для добавления записи в таблицу
- 'Update' - вызывает диалоговое окно для редактирования выбранной записи
- 'Delete' - удаляет выбранную запись
- 'Refresh' - вызывает чтение содержимого таблицы (в отдельном потоке)
и вывод результатов в основное окно приложения
- 'Get count of records' - в отдельном потоке вызывает чтение числа записей в 
таблице и вывод на форму под кнопкой
