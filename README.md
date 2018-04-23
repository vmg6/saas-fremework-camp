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
    
# Run test to check rest
   mvn clean test -Dservers=env1 -Dgroups=rest-api
   
# Run test to check ui
   mvn clean test -Dservers=env1 -Dgroups=ui