# EquipmentCatalog

1. Introduction

The application provides access to the table "unit" of "equipmentCatalog" database. 
Table contains list of equipment.

2. Application interface

The main window of the application dispays the contents of the table, on the left side 5 control buttons are placed:
- 'Add' - calls dialog window for adding record to the table
- 'Update' - calls dialog window for editing of selected record
- 'Delete' - delete selected record
- 'Refresh' - calls reading contents of the table (in a separate thread) and displaying the results in the main application window
- 'Get count of records' - in a separate thread calls reading of the quantity records in the table and displays it under the button

3. Implementation

- Database: MySQL
- Database connection: Hibernate
- GUI: Swing
- Project build: Maven

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
таблице и выводит на форму под кнопкой

3. Реализация

- База данных: MySQL
- Связь с БД: Hibernate
- GUI: Swing
- Сборка проекта: Maven
