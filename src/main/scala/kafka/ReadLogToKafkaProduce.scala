package kafka
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructType}

object ReadLogToKafkaProduce {
  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir","C:\\hadoop-2.2.0")

    val structType = new StructType()
      .add("timestamp",DataTypes.StringType)
      .add("logLevel",DataTypes.StringType)
      .add("cityName",DataTypes.StringType)
      .add("logMessage",DataTypes.StringType)

    val session = SparkSession
      .builder()
      .config("spark.driver.memory","2G")
      .config("spark.executor.memory","2G")
      .appName("LogToKafka")
      .master("local")
      .getOrCreate()

    var readData = session
      .readStream
      .schema(structType)
      .option("sep",",")
      .format("csv")
      .load("LOG//")

    readData
      .selectExpr("to_json(struct(*)) AS value")
      .writeStream
      .format("kafka")
      .option("kafka.bootstrap.servers","localhost:9092")
      .option("topic","test")
      .outputMode("append")
      .option("checkpointLocation","checkpointLocations")
      .start()
      .awaitTermination()

  }
}
