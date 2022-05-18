##Gradle Commands

console:

$./gradlew build

Run application using java 11 and executable JAR file produced by gradle in \build task listening to port 8081

$./gradlew bootrun

#Run test

$./gradlew test

##Store Metric Reading

Assumption: temperatureReading only one Fahrenheit/Celsius taken in input

POST : /sensor/register

example:
POST
$ curl -x http://localhost:8081/sensor/register
{
"sensorId": "124",
"countryName":"INDIA",
"cityName": "GNT",
"temperatureReading" :"32.4"
}

output:

{
"message": "Stored Successly ",
"generatedId": "1",
"sensorId": "124"
}

---
## GET Stored Metric Reading
GET : /sensor/read/all

example:
http://localhost:8081/sensor/read/all

Output:

[
{
"id": 1,
"sensorId": "124",
"countryName": "INDIA",
"cityName": "GNT",
"temperatureReading": "32.4"
}
]

---

## GET Stored Metric Reading for specific sendor id
GET : /sensor/read/{sensorId}

example:
http://localhost:8081/sensor/read/124

output: 

[
{
"id": 1,
"sensorId": "124",
"countryName": "INDIA",
"cityName": "GNT",
"temperatureReading": "32.4"
}
]
---
## GET Stored Consumption - Max, Min, Sum, Average for specific sendor id

GET: /consumption/week/{sensorID}
Assumption: has 7 days values/ else computes avgerage of days specified

example:
GET
http://localhost:8081/consumption/week/124

output:
{
"maxReading": "32.4",
"minReading": "32.4",
"avgReading": "32.4",
"sumReading": "32.4"
}

---
## GET Stored Consumption - Latest Metric Reading for specific sendor id

GET:  /consumption/dateRange/{sensorID}

example:
GET
http://localhost:8081/consumption/dateRange/124

output:
{
"id": 1,
"sensorId": "124",
"countryName": "INDIA",
"cityName": "GNT",
"temperatureReading": "32.4"
}

