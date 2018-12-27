package de.ai.sudhir

import java.util.NoSuchElementException

abstract class MyList {
  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(item: Int): MyList
}

object Empty extends MyList {
  override def head: Int = throw new NoSuchElementException
  override def tail: MyList = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add(item: Int): MyList = new Cons(item, Empty)
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h
  override def tail: MyList = t
  override def isEmpty: Boolean = false
  override def add(item: Int): MyList = new Cons(item, this)
}

/* Tests */
object Main extends App {

  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)

  println(list.add(10).head)

}
