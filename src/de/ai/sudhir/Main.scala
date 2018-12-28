package de.ai.sudhir

import java.util.NoSuchElementException

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](item: B): MyList[B]
  def printElement: String
  override def toString: String = "[" + printElement + "]"
}

object Empty extends MyList[Nothing] {
  override def head = throw new NoSuchElementException
  override def tail = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](item: B): MyList[B] = new Cons(item, Empty)
  override def printElement: String = " "
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](item: B): MyList[B] = new Cons(item, this)
  override def printElement: String = " " + h + tail.printElement
}

/* Tests */
object Main extends App {

  val listOfInteger: MyList[Int] =
    new Cons[Int](1, new Cons[Int](2, new Cons[Int](3, Empty)))
  println(listOfInteger.tail.tail.head)
  println(listOfInteger.add(0).head)
  println(listOfInteger.toString)

  val listOfString: MyList[String] = new Cons[String](
    "Hello",
    new Cons[String]("Scala", new Cons[String]("World", Empty)))
  println(listOfString.tail.tail.head)
  println(listOfString.add("scala-the-rock").head)
  println(listOfString.toString)

}
