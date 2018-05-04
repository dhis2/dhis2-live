# dhis2-live
DHIS 2 Live package

Creates an  executable jar which  contains jetty server classes  and a
simple tray icon controller. 

```
mvn package
```

to build the jar and the exe file.  

On Ubuntu (and other gnome-ish environments).  The java systemtray
is not supported on fancy 3d enhanced window managers.  To work on
ubuntu, first run:

```
metacity --replace
```

to get back to a vanilla WM.

The application looks for a dhis2.home system property that is being set by the Live jar.

The expected structure looks like this:

```
/dhis2-live.jar
/startup.bat (optional)
/conf/
/conf/dhis.conf
/log/
/webapps/
/webapps/dhis
```

After that you are away.  The application will install an icon in your
system tray or equivalent).  The icons are currently crap but they can
be changed.  If the server is stopped you get a blue one.  While it is
starting it is orange.  When its running it goes green.

The h2 database is created in the db directory and log file in the log directory.

To exit - right click the icon and select Exit.

A sample `dhis.conf` configured for PostgreSQL looks like this:

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
