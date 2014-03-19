package pl.agol.eboz

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.Logging
import scala.reflect.ClassTag

/**
 * @author Andrzej Goławski
 */
object EbozApp extends App {

  case class AdxpMessage(text: String)

  case class IcaoFlightPlan(text: String)

  case class FlightPlan(msgType: String)

  class MessageReceiver extends Actor {

    val formatRecognizer = context.actorOf(Props[FormatRecognizer], "formatRecognizer")
    val adexpPareser = context.actorOf(Props[AdxpPareser], "adexpParser")
    val icaoPareser = context.actorOf(Props[IcaoPareser], "icaoParser")

    def receive = {
      case msg: String => formatRecognizer ! msg
      case msg: AdxpMessage => adexpPareser ! msg
      case msg: IcaoFlightPlan => icaoPareser ! msg
      case _ => throw new IllegalArgumentException
    }
  }

  class FormatRecognizer extends Actor {

    def receive = {
      case msg: String => sender ! resolve(msg)
    }

    private def resolve(msg: String) = msg match {
      case msg if msg.startsWith("-TITLE IFPL") => AdxpMessage(msg)
      case msg if msg.startsWith("(FPL") => IcaoFlightPlan(msg)
      case _ => throw new UnknowFormatException
    }
  }

  class UnknowFormatException extends Exception

  abstract class FplPareser[T : ClassTag] extends Actor {

    val fplProcess = context.actorOf(Props[FplProcess])

    protected def parse(message: T): FlightPlan

    def receive = {
      case message: T => fplProcess ! parse(message)
    }
  }

  class AdxpPareser extends FplPareser[AdxpMessage] {
    protected def parse(message: AdxpMessage): FlightPlan = {
      FlightPlan("ADXP")
    }
  }

  class IcaoPareser extends FplPareser[IcaoFlightPlan] {
    protected def parse(message: IcaoFlightPlan): FlightPlan = {
      FlightPlan("ICAO")
    }
  }

  class FplProcess extends Actor {

    val logger = Logging(context.system, this)

    def receive = {
      case fpl => logger.info(fpl.toString)
    }
  }
  val eBOZ = ActorSystem("eBOZ")

  val msgReceiver = eBOZ.actorOf(Props[MessageReceiver], "msgReceiver")

  msgReceiver ! FlightData.ICAO_FLIGHT_PLAN
  msgReceiver ! FlightData.ADXP_FLIGHT_PLAN

}