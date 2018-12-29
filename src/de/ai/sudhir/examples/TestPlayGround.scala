package de.ai.sudhir.examples

object TestPlayGround extends App {

  abstract class Animal {
    def eat
  }

  //Abstract class
  val dog = new Animal {
    override def eat: Unit = println("chamchamcham...")
  }
  dog.eat

  // For non-abstract class
  class Person(name: String) {
    def getName = println(s"Hello, my name is $name, how can I help you!")
  }
  val rakesh = new Person("Rakesh") {
    override def getName =
      println(s"Hello, my name is Rakesh, how can I help you!")
  }

  trait Employee {
    def getFirstName: String
    def getLastName: String
    def getSalary: String
  }

  val accountant = new Employee {
    override def getFirstName: String = ???
    override def getLastName: String = ???
    override def getSalary: String = ???
  }
}
