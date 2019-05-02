# DHIS 2 Live

DHIS 2 Live package - creates an executable jar which contains jetty server classes
and a simple tray icon controller. 

## How to build DHIS 2 Live
Build the JAR and executable file with the following command:

```
mvn clean package
```
Previous command generated the JAR and executable files in the `target` folder.

## Structure of the DHIS 2 Live folder
Now, create a new folder at some location of your preference. It doesn't have to be inside the dhis2-live folder. This folder will contain the DHIS2-Live launcher. When the folder is created, make sure its content is following (read further how to achieve it):

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
### Upgrading DHIS2 version
The `/webapps/dhis` folder should contain the uncompressed WAR file. This implies that you can update your Live package with a later DHIS 2 version by deleting the `/webapps/dhis` folder, placing the latest `dhis.war` file under `webapps` and decompressing it. This should create a new `dhis` folder within the `webapps` folder and the created folder should contain the decompressed files from the .war file.

### Getting executable .jar files at the correct location
You can find the executable jar and exe files here:

```
target/dhis2-live-jar-with-dependencies.jar
target/dhis2-live.exe
```

You can rename the `dhis2-live-jar-with-dependencies.jar` file to `dhis2-live.jar` and place it under the appropriate folder. Do the same with the .exe file.

### dhis.conf file
`dhis.conf` file should have the same settings as a regular DHIS2 installation has. For example, look at the bottom of this Readme file.

## How it works
On Windows, the application (`dhis2-live.exe`) will install an icon in your system tray or equivalent. On UNIX based systems (and eventually also on Windows), you can run a command `java -jar dhis2-live.jar` from command prompt. This will start the DHIS2 instance. 

In the system tray, the color of the icon indicates the state of DHIS2 instance. Blue icon indicates that instance is not running. Orange indicates that instance starting. Green indicates that instance is up and running. To exit/stop the instance, right click the icon and select Exit.

## dhis.conf sample
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
## Jetty port
The default port that the embedded jetty server listens on is `8080`.  If this
clashes with other services on your system it can be reconfigured by creating a
file `conf/jetty.port` which contains simply the desired server port, eg `8888`.
