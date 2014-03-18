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

  class MessageReceiver extends Actor {

    val logger = Logging(context.system, this)

    def receive = {
      case msg: String => logger.info(msg)
      case _ => throw new IllegalArgumentException
    }
  }

  val eBOZ = ActorSystem("eBOZ")

  val msgReceiver = eBOZ.actorOf(Props[MessageReceiver], "msgReceiver")

  msgReceiver ! FlightData.ICAO_FLIGHT_PLAN
  msgReceiver ! FlightData.ADXP_FLIGHT_PLAN

}