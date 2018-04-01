# Automation framework overview
This is framework for Automation GL ProCamp

# Requirements
* REST Assured
* Hamcrest
* Java-faker

Also one need to install Alure CLI to be able generate allure reports, see https://docs.qameta.io/allure/latest/#_reporting

# Test configuration
One can configure test execution against specific environment using environment variables:

| Name                  | Required  | Default                  | Example                                               |
| --------------------  | --------- | ------------------------ |------------------------------------------------------ |
|  ENVIRONMENT          | yes       | none                     | env1  |  

# Support groups of test
* rest-api
* ui
* performance
    
# Run test
   mvn clean test -Dservers=env1
   
# Run specific Groups of test
* mvn clean test -Dgroups=rest-api