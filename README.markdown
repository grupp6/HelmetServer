HelmetServer
============
HelmetServer is an application server that handles alarms from an Android app.

How to build
-------------

### Prerequisites
To build you'll need:

* [Java JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Ant](http://ant.apache.org/)
* [Apache Ivy](http://ant.apache.org/ivy/)
* Database access. Any relational database that's compatible with Hibernate will do (=most).

### Download, build and run

1.   If you have git you can download the source by running the following 
     command: ```git clone git://github.com/grupp6/HelmetServer.git```
     
     If not you can download the source in the old fashoned way from 
     [this location](https://github.com/grupp6/HelmetServer/downloads).
3.   Build by running ```ant``` in the project folder. This will automatically download all dependencies.
4.   Run the jar-file in HelmetServer/build.