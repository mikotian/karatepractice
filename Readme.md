# Karate based API Testing Framework

```
root
├── pom.xml - Maven File
├── Readme.md - this file
├── src
│   ├── main
│   │   ├── java -- This is where you can put your helper classes
│   │   └── resources -- any resource file for helper classes
│   └── test -- this is where all the test drivers/fixtures/data resides
│       ├── java 
│       │   └── runner -- all the runners belong to me
│       │       └── TestRunner.java -- Main runner file. contains code to generate cucumber type reports 
│       └── resources -- features, data, configs reside here 
│           ├── features -- this can be split according to component etc
│           │   └── posts.feature
│           ├── karate-config.js -- This file is executed when the framework starts. Global variables can be declared here
│           └── TestData -- input/output files
│               └── postSchema.json
```

## Setup your machine

Install JDK : 

    JDK Version 11 :
    
    https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

Install Maven : 

    https://maven.apache.org/download.cgi
---
### Quick Setup

* Clone this repo

* Navigate to the root folder

* Open a command prompt/terminal and paste this command

~~~
mvn test
~~~

This will run your tests and produce reports in the target directory

---

## IDE Setup(Eclipse/Intellij/VSCode)

Open the main folder and the IDE should ask you if you want to setup the project as a maven project. Say yes.

Another way is to import the pom.xml as a maven project.


## Adding tests

Create new feature file (or edit the one provided)

~~~
Feature: Status Tests
Background:
* def testUrl = "https://httpbin.org"

@status202
Scenario: Get all posts
Given url testUrl
Given path 'status','202'
When method GET
Then match responseStatus == 202

@status204
Scenario: Get all posts
Given url testUrl
Given path 'status','204'
When method DELETE
Then match responseStatus == 204
~~~

The code above is self-contained and if you add a feature file with the above content in the "features" directory then
the new tests will be added to the tests executed when the following command is executed:

~~~
mvn test
~~~

for more details on the karate DSL and other samples visit: [Karate Docs](https://github.com/intuit/karate#index)

## Viewing Tests Results

#### Karate Reports:
Navigate to ./target/karate-reports/karate-summary.html

#### Cucumber Reports:
Navigate to ./target/cucumber-html-reports/overview-features.html


##Additional Usage

To run tests of a specific 'tag' e.g. @posts

~~~
mvn test -Dtags=@posts
~~~

To run tests only from a specific folder(inside features)

~~~
mvn test -Dfeatures='<folder>'
~~~

You can also mix and match

~~~
mvn test -Dfeatures='/api' -Dtags=@create
~~~

or provide an environment (default is qa)

~~~
mvn test -Dkarate.env=<envname>
~~~
