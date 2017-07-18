package com.akkademy.test

import org.scalatest.BeforeAndAfterEach
import org.scalatest.Finders
import org.scalatest.FunSpecLike
import org.scalatest.Matchers

import com.akkademy.AkkademyDb
import com.akkademy.Messages.SetRequest

import akka.actor.ActorSystem
import akka.testkit.TestActorRef

class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  
  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }
}