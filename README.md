# cmhie

## Overview
The module allows authentication using two-step login

## Installation
Build the module from source. Install the omod file through OpenMRS module management. 
After installation complete the configuration. The OpenMRS login page is not seen anymore, 
so location based login is not possible. A default location is selected or login is without a location.

## Configuration
will be added later

###Build
To ensure that your commit builds fine run
```
mvn clean package
```
before opening a new pull request.

###Coding conventions

This module adheres to the OpenMRS coding conventions, please read
https://wiki.openmrs.org/display/docs/Coding+Conventions

####Code style

Help us to keep the code consistent!
This will produce readable diffs and make merging easier and quicker!

This module uses the Eclipse formatter plugin to automatically format *.java
files. This plugin is automatically executed when you build the module.

To manually run the formatter plugin, do
```
mvn java-formatter:format
```