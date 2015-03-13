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
/ dhis2-live.jar
/ startup.bat (optional)
/ conf /
/ conf / hibernate.properties
/ database /
/ log /
/ webapps /
/ webapps / dhis
/ webapps / birt (optional)
```

After that you are away.  The application will install an icon in your
system tray or equivalent).  The icons are currently crap but they can
be changed.  If the server is stopped you get a blue one.  While it is
starting it is orange.  When its running it goes green.

The h2 database is created in the db directory and log file in the log directory.

To exit - right click the icon and select Exit.

A sample `hibernate.properties` configured for H2 looks like this:

```
--------------------------------------------------------------------
hibernate.dialect = org.hisp.dhis.dialect.H2Dialect
hibernate.connection.driver_class = org.h2.Driver
hibernate.connection.url = jdbc:h2:./database/dhis2;AUTO_SERVER=TRUE
hibernate.connection.username = sa
hibernate.connection.password =
hibernate.hbm2ddl.auto = update
--------------------------------------------------------------------
```

The default port that the embedded jetty server listens on is `8080`.  If this
clashes with other services on your system it can be reconfigured by creating a
file `conf/jetty.port` which contains simply the desired server port, eg `8888`.


TODO 
1.  make a nice "instrument panel" showing application status when you
click on the tray icon
postgres and h2, though the latter (and JavaDB) is targeted.
2.  make richer configuration of jetty through `web.xml` or similar

