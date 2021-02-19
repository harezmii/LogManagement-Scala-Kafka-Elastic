package kafka

import org.apache.spark.sql.types.{DataTypes, StructType}

class Constant {


  def getStructType: StructType ={
      return new StructType()
        .add("timestamp",DataTypes.StringType)
        .add("logLevel",DataTypes.StringType)
        .add("cityName",DataTypes.StringType)
        .add("logMessage",DataTypes.StringType)

    }
}
