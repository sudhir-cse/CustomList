package de.ai.sudhir.examples

object TestPlayGround extends App {

  case class Person(name: String, age: Int)

  // 1. Promote class member to instance variable
  val rakesh = new Person("Rakesh", 28)
  rakesh.name
  rakesh.age

  // 2. Sensible toString
  println(rakesh.toString)

  //3. Has handy copy methods
  val ram = rakesh.copy(name = "Ram")
  println(ram.toString)
  println(ram.name)
  println(ram.age)

  // 4. Companion method
  val priyanka = Person("Priyanka", 22)
  println(priyanka.name)

  // 5. has code implemented

  // 6. Case classes are serializable

  // 7. Case classes have extractor patter = they can be used inside pattern matching

}
