HelmetServer
============
HelmetServer is an application server that handles alarms from an Android app.

How to build
-------------
To build you'll need:
* Java JDK 7
* Ant
* Apache Ivy
* A database

This is the steps to download, build and run the server:
1. Download source from [https://github.com/grupp6/HelmetServer](https://github.com/grupp6/HelmetServer). You can choose to use git clone or download an archive.
2. Add database connection settings to the Hibernate configuration file (HelmetServer/src/hibernate.cfg.xml).
3. Build by running ant. This will also download dependencies using Ivy.
4. Run the jar-file in HelmetServer/build.