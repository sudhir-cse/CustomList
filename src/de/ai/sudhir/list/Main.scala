package de.ai.sudhir.list

import java.util.NoSuchElementException

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](item: B): MyList[B]
  def printElement: String
  override def toString: String = "[" + printElement + "]"

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  override def head = throw new NoSuchElementException
  override def tail = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](item: B): MyList[B] = new Cons(item, Empty)
  override def printElement: String = " "

  override def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  override def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  override def flatMap[B](
      transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty

  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](item: B): MyList[B] = Cons(item, this)
  override def printElement: String = " " + h + tail.printElement

  override def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) Cons(h, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    Cons(transformer.transform(h), t.map(transformer))

  override def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)
}

/* Tests */
object Main extends App {

  val listOfInteger: MyList[Int] =
    Cons[Int](1, new Cons[Int](2, new Cons[Int](3, Empty)))

  // Filter
  val filterEvenNo = listOfInteger.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  })
  println(filterEvenNo.toString)

  // Map
  val multiplyListBy2 = listOfInteger.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  })
  println(multiplyListBy2.toString)

  // List concatenation
  val list1 = new Cons[Int](2, new Cons[Int](3, new Cons[Int](4, Empty)))
  val list2 = new Cons[Int](5, new Cons[Int](6, new Cons[Int](7, Empty)))
  val concatenatedList = list1 ++ list2
  println(concatenatedList.toString)

  // FlatMap
  val flatMap = listOfInteger.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] =
      Cons[Int](element, new Cons[Int](element * 2, Empty))
  })
  println(flatMap.toString)

}
