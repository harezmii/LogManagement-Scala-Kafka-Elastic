# LogManagement-Scala-Kafka-Elastic
Görselde istenilen yapıya uygun mimari uygulandı.
Log4j ile log yönetimi ve üretimi yapıldı.
Apache Spark ile kaydedilen loglar stream olarak kafkaya produce edildi.
Kafka ya produce edilen loglar elasticsearh e kaydedilip kibana ile server logların lokasyona göre count'u gösterildi.

İstenilen Durumu:

The definition of the requirements
Continuously generated logs have to be shown in a browser as real time and
be stored in a database which can be any rdbms or nosql.
We assumed that we have 5 different servers reside on different cities ( Istanbul,Tokyo, Moskow, Beijing,London ) and all server logs are being collected in a central server.
Our use case is starting from here. You have to collect these logs from this central server and push it to a kafka message broker.Kafka cluster may consist of only one server. You have to complete 2 following tasks during the consuming of these messages ;
1. Show row count per city in a dashboard as real-time
2. Collect these rows in a persistence layer which can be rdbms or any nosql databases.

The Pieces that should be in your solution
1. Log structure
a. Timestamp
b. Log level (info,warning,fatal etc )
c. Log server city name ( Istanbul,Tokyo, Moskow,Beijing,London )
d. Log detail can contain any text which is not important .
You have to write a log creator which must create rows as predefined log structure.Logs must be generated continuously in log files which are limited to max 2 mb. After the log file is exceeds to 2 mb then a new log file must be created.

The sample logs: 
2019-03-21 09:59:17.992 INFO Istanbul Hello-from-Istanbul 
2019-03-21 09:59:17.996 WARN Tokyo Hello-from-Tokyo 
2019-03-21 09:59:18.057 FATAL Moskow Hello-from-Moskow 
2019-03-21 09:59:18.992 DEBUG Beijing Hello-from-Beijing 
2019-03-21 09:59:20.073 ERROR London Hello-from-London

![image](https://user-images.githubusercontent.com/55887187/108522298-be3deb80-72dd-11eb-8392-a228bcdd3321.png)
