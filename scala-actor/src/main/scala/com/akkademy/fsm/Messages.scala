package com.akkademy.fsm

object Messages {
  
  sealed trait State
  case object Disconnected extends State
  case object Connected extends State
  case object ConnectedAndPending extends State
  
}