package com.support
import scala.io.{Source}
import java.util.Calendar
import java.time.LocalDate

object ProcessControllerHelper {
    
    def getDateFileTag(): String = {
      val time = LocalDate.now
      val dateFileTag = time.toString()
      return dateFileTag
    }
  
    def timeStamp(): String = {
       val time = Calendar.getInstance.getTimeInMillis().toInt
       return time.toString()
    }
        
    
    
}