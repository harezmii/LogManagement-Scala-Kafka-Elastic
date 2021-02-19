package kafka
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{DataTypes, StructType}
import org.apache.spark.sql.functions._
import java.lang.System._

object ReadKafkaToElasticSearch {
  def main(args: Array[String]): Unit = {

    setProperty("hadoop.home.dir","C:\\hadoop-2.2.0")

    val structType = new StructType()
      .add("timestamp",DataTypes.StringType)
      .add("logLevel",DataTypes.StringType)
      .add("cityName",DataTypes.StringType)
      .add("logMessage",DataTypes.StringType)

    val session = SparkSession
      .builder()
      .appName(ReadLogToKafkaProduce.getClass.getName)
      .master("local")
      .getOrCreate()

    val df = session.read
      .format("kafka")
      .option("kafka.bootstrap.servers","localhost:9092")
      .option("subscribe","test")
      .load()

    val castData = df.selectExpr("CAST(value AS STRING)")

    val dataSet = castData
      .select(from_json(castData.col("value"),structType).as("json"))
      .select("json.*")

    dataSet.write
      .format("org.elasticsearch.spark.sql")
      .mode("overwrite")
      .option("es.nodes", "localhost")
      .option("es.port","9200")
      .option("es.net.http.auth.user","elastic")
      .option("es.net.http.auth.pass","deneme")
      .option("index.mapping.total_fields.limit","10000")
      .option("number_of_replicas","0")
      //.option("es.nodes.wan.only","true")
      .save("log")
  }
}
