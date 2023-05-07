# KC-Energy

CS 349 - Kansas City energy company management system project. Written in Java with Swing as the GUI framework.

## Requirements

You must have MySQL workbench installed on your machine.

## User Guide

### Create the Schema and Tables

1. First we must create the database for this project to run without errors.
All of the necessary SQL scripts have been included. First we must create a new schema in MySQL workbench, or
use an existing schema.
2. Open MySQL workbench. To create a new schema, right click on the `Schemas` area and select `create new schema`. Name your schema
`kc_energy` and hit `Apply`. The schema referenced in my `DbConnection` class is named `kc_energy`, so if you create a schema
make sure to name it that or rename the connection in the code.
3. Now we can create some tables. Open a SQL tab for executing queries in workbench. Paste in the create queries from
the `create-tables.txt` file located in the `data` directory, and execute to create each of the three create table scripts.

### Import the Data

1. Right click on the `customer` table in your database. Click on the `Table Data Import Wizard` button.
In the window that opens up, select the `customer_data_export.csv` file which is in the `data` directory
and then click next. Use the existing `customer`
table and click next. In the next page, the encoding should be utf-8, and make sure that all the source and destination
column names make sense. Click next until all the data has been imported.
2. Repeat this process with the `customer_payment` table and `customer_payment_data_export.csv` file and the
`monthly_bill` table with the `monthly_bill_data_export.csv` file.
3. After all three tables have data, we should be ready to run our application!

### Run the Project

1. Open the project in the editor of your choice. I used Intellij for my editor. This project is Maven project,
so to get the dependencies set up, at least in Intellij, right click on the pom.xml file and reload as a Maven project.
I am sure there are other better ways to get a Maven project going, but that's how I've been doing it when I pull down
to a new machine.
2. The `CustomerSearchWindow` class is the launching point of the application, so run that class to start things up.
3. Once the program launches you should see a search window with some customers, you can now navigate and do what
you wish!
4. Again, you may need to make sure that the MySQL connection JAR was properly set. Also that the `DbConnection` class
has the proper connection information that matches the exposed port and IP of your machine, as well as whatever you named
the schema if it's different from `kc_energy`.


