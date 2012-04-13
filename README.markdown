HelmetServer
============
HelmetServer is an application server that handles alarms and some data logging for an Android app.

How to build
-------------

### Prerequisites
To build and run you'll need:

* [Java JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Tomcat 7](http://tomcat.apache.org/)
* [Apache Ant](http://ant.apache.org/)
* [Apache Ivy](http://ant.apache.org/ivy/)
* [Eclipse](http://www.eclipse.org/) with the [Web Tools Platform](http://www.eclipse.org/webtools/) installed
* Database access. Any relational database that's compatible with Hibernate will do (=most).

### Download, build and run

This is one way to get the server working.

1.   If you have git you can download the source by running the following 
     command: ```git clone git://github.com/grupp6/HelmetServer.git```
     
     If not you can download the source in the old fashoned way from 
     [this location](https://github.com/grupp6/HelmetServer/downloads).
2.   Edit src/hibernate.cfg.xml and add the database connection settings for your database. Please note that
     the server will automatically drop and create the necessary tables needed (using Hibernate). See the
     [Hibernate documentation](http://www.hibernate.org/docs) for more details.
3.   Download dependencies by running ```ant``` in the project folder or choosing "run as ant build" in Eclipse
     on build.xml. This will automatically download all dependencies.
4.   Add the Tomcat server to Eclipse (Windows -> Preferences -> Runtime Environment -> Add...).
5.   Run the project on the server by choosing run on server in Eclipse.