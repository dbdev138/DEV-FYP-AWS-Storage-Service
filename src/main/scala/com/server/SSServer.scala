package com.server

import com.services.StorageService

import akka.actor.ActorSystem
import com.support.CORSSupport
import spray.http.MediaTypes
import spray.routing.{Route, SimpleRoutingApp}

object SSServer extends App with SimpleRoutingApp with CORSSupport{


  implicit val actorSystem = ActorSystem()


  //Custom directive to replace the inclcusion of the stated return type header
  def getJson(route: Route) = get{
    respondWithMediaType(MediaTypes.`application/json`){
      route
    }
  }

  //Define Each route independently as lazy vals to keep code clean
  //Link the names of each route in the start server method

  lazy val helloRoute = get {
      cors{
        path("hello") {
          complete {
            "Welcome to the AWS Storage Service \n here are a list of the available routes:"
          }
        }
      }
  }
  
  lazy val store_pcs_processA = get {
      cors{
        path("storageServices" / "s3" / "processControllers" / "processA" / "withObject" / Segment / "andDestination" / Segment ) { (data, fileName) =>
          complete {
             StorageService.writeObjectToS3(data,fileName)
          }
        }
      }
  }
  
  
  

  startServer(interface = "localhost", port = 8084) {
    helloRoute~
    store_pcs_processA
  }

}