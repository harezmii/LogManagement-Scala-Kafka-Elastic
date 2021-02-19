package log

import org.apache.log4j.{Logger, MDC, PropertyConfigurator}
import scala.util.Random
object Log {
  PropertyConfigurator.configure("log4j.properties")
  val log: Logger =  Logger.getLogger(Log.getClass)

  val city = List("Istanbul","Tokyo","Moscow","London","Beijing")

  def main(args: Array[String]): Unit = {
    while (true){

      MDC.put("app.name",randomCity())
      log.info("Hello-from-")
      MDC.put("app.name",randomCity())
      log.warn("Hello-from-")
      MDC.put("app.name",randomCity())
      log.fatal("Hello-from-")
      MDC.put("app.name",randomCity())
      log.debug("Hello-from-")
      MDC.put("app.name",randomCity())
      log.error("Hello-from-")

      Thread.sleep(800)
    }

    def randomCity() : String = {
      val randomNumber = new Random().nextInt(5)
      var randomCity = randomNumber match {
        case 0 => City(city(0)).cityName
        case 1 => City(city(1)).cityName
        case 2 => City(city(2)).cityName
        case 3 => City(city(3)).cityName
        case 4 => City(city(4)).cityName
      }
      return randomCity
    }
  }
}
case class City(cityName : String)

