# DHIS 2 Live

DHIS 2 Live package. Creates an executable jar which contains jetty server classes
and a simple tray icon controller. 

Build the JAR and executable file with the following command:

```
mvn clean package
```
Previous command generated the JAR and executable files in the `target` folder.

Now, create a new folder at some location you prefer. It doesn't have to be inside the dhis2-live folder. It can be randomly placed. This folder will contain the DHIS2-Live launcher. When the folder is created, create the following structure in it:

```
/dhis2-live.jar
/dhis2-live.exe
/startup.bat (optional)
/conf/
/conf/dhis.conf
/log/
/webapps/
/webapps/dhis
```

The `dhis` folder should contain the uncompressed WAR file. This implies that you can update your Live package with a later DHIS 2 version by deleting the `dhis` folder, placing the latest `dhis.war` file under `webapps` and uncompressing it.

You can find the executable jar here:

```
target/dhis2-live-jar-with-dependencies.jar
```

You can rename the `dhis2-live-jar-with-dependencies.jar` file to `dhis2-live.jar` and place it under the appropriate folder.

`dhis.conf` file should have the same settings as regular DHIS2 installation has. For example, look on the bottom of this Readme file.


After that you are away. On Windows, the application (`dhis2-live.exe`) will install an icon in your
system tray or equivalent. On UNIX based systems, or eventually even on Windows, you can run a command `java -jar dhis2-live.jar` from command prompt. If the server is stopped you get a blue icon. While it is starting it is orange. When its running it goes green. To exit, right click the icon and select Exit.

A sample `dhis.conf` configured for PostgreSQL looks like the below. Note that you
must install PostgreSQL yourself as the database is not provided by this package.

```
# Hibernate SQL dialect
connection.dialect = org.hibernate.dialect.PostgreSQLDialect

# JDBC driver class
connection.driver_class = org.postgresql.Driver

# Database connection URL
connection.url = jdbc:postgresql:dev

# Database username
connection.username = dhis

# Database password
connection.password = dhis

# Database schema behavior, can be validate, update, create, create-drop
connection.schema = update
```

The default port that the embedded jetty server listens on is `8080`.  If this
clashes with other services on your system it can be reconfigured by creating a
file `conf/jetty.port` which contains simply the desired server port, eg `8888`.

