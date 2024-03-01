# Jakarta_labb
[![Jakarta_labb CI pipeline](https://github.com/Lucia560/Jakarta_labb/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/Lucia560/Jakarta_labb/actions/workflows/maven.yml)
[![SonarCloud](https://github.com/Lucia560/Jakarta_labb/actions/workflows/sonarcloud.yml/badge.svg?branch=main)](https://github.com/Lucia560/Jakarta_labb/actions/workflows/sonarcloud.yml)
![GitHub top language](https://img.shields.io/github/languages/top/Lucia560/Jakarta_labb?label=Java&color=red)
![GitHub language count](https://img.shields.io/github/languages/count/Lucia560/Jakarta_labb?color=yellow)

## Description
This project involves developing a RESTful Web service using the JAX-RS API, with a specific focus on movie-related data. The service will be deployed on a WildFly application server, utilizing the Jakarta EE platform.
## Docker

To build the docker image:

```
mvn clean package
docker build --tag=jakarta-labb .
```
