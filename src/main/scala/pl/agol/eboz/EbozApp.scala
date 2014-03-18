package pl.agol.eboz

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.Logging

/**
 * @author Andrzej Goławski
 */
object EbozApp extends App {

  case class AdxpMessage(text: String)

  case class IcaoFlightPlan(text: String)

  class MessageReceiver extends Actor {

    val logger = Logging(context.system, this)

    val formatRecognizer = context.actorOf(Props[FormatRecognizer], "formatRecognizer")

    def receive = {
      case msg: String => formatRecognizer ! msg
      case msg: AdxpMessage => logger.info(msg.toString)
      case msg: IcaoFlightPlan => logger.info(msg.toString)
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

  val eBOZ = ActorSystem("eBOZ")

  val msgReceiver = eBOZ.actorOf(Props[MessageReceiver], "msgReceiver")

  msgReceiver ! FlightData.ICAO_FLIGHT_PLAN
  msgReceiver ! FlightData.ADXP_FLIGHT_PLAN

}