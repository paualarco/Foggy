package com.cloriko

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import DefaultJsonProtocol._

trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)

  implicit val userRegistryRequest = jsonFormat4(UserRegistryRequest)
}
//#json-support

case class UserRegistryRequest(userName: String, name: String, lastName: String, email: String)