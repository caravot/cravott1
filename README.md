# Purpose
This software system is being developed as a project required towards fulfillment of the course EN.605.782.71: Web Application Development with JSP and Servlets in the Fall 2016 term.

# Requirements

* It must allow **multiple user options**. That is, the user should not be restricted to a specific sequence of actions. They should be able to choose the action they will take next (find information, choose a product, view the cart, etc.).
* It must involve **session** data related to users (such as a shopping cart, list of courses, etc.).
* It must have a good **user interface**. I don't expect anything too elaborate (although really nice features might result in extra credit), but the pages generated by the system should be readable and uniform.
* It must require **input validation**. Any errors should be trapped by the system and presented to the user as informative error messages (instead of Tomcat exception pages).
* You don't have to use a database to store the data but must session and Java Beans to store data (Database usage is encouraged though).
* At least one or two pages must implement **SSL**.

# Requirements
* Maven
* Tomcat 9
* Java 1.8
* SQLite
* Bootstrap
* jQuery
* Font Awesome

# Database DDL

```
CREATE TABLE person
(
    id INTEGER PRIMARY KEY,
    name TEXT,
    email TEXT,
    twitter TEXT,
    description TEXT
);
CREATE UNIQUE INDEX person_name_uindex ON person (name);
CREATE TABLE user_roles
(
    user_name TEXT NOT NULL,
    role_name TEXT NOT NULL,
    PRIMARY KEY (user_name, role_name)
);
CREATE TABLE users
(
    user_name TEXT PRIMARY KEY NOT NULL,
    user_pass TEXT NOT NULL
);
```

# Installation

Tomcat Setup:

* Save the *sqlite-jdbc-3.15.1.jar* to *<TOMCAT_HOME>\lib\*
* Edit the *<TOMCAT_SERVER>\conf\server.xml* to include the following

```
<Realm className="org.apache.catalina.realm.JDBCRealm"
            name="Realm MyDomain"
            driverName="org.sqlite.JDBC"
            connectionURL="jdbc:sqlite:/FULL/PATH/TO/PROJECT/db/test.db"
            userTable="users" userNameCol="user_name" userCredCol="user_pass"
            userRoleTable="user_roles" roleNameCol="role_name" />
```

* Run `mvn clean install`
* Move war to <TOMCAT_HOME>\weapps\ or deploy another way.
* Visit http://localhost:8080/

# Credits
* Beer API: [BreweryDB](http://brewerydb.com/)
* Recipes: [Yummly](http://yummly.com/)
* Yummly Models: [Mads Kalør]()https://github.com/mKaloer/Yummly-Java-Wrapper)

