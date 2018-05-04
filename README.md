# DHIS 2 Live

DHIS 2 Live package. Creates an executable jar which contains jetty server classe
and a simple tray icon controller. Build the JAR and executable file with the
following command:

```
mvn clean package
```

The expected file structure looks like this:

```
/dhis2-live.jar
/startup.bat (optional)
/conf/
/conf/dhis.conf
/log/
/webapps/
/webapps/dhis
```

You can find the executable jar here:

```
target/dhis2-live-jar-with-dependencies.jar
```

You can rename the `dhis2-live-jar-with-dependencies.jar` file to `dhis2-live.jar` and place it under the appropriate folder.


After that you are away. The application will install an icon in your
system tray or equivalent. If the server is stopped you get a blue icon.
While it is starting it is orange. When its running it goes green. To exit, 
right click the icon and select Exit.

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
