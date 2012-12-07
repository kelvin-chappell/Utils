package specs2

object DataTableBuilderClient extends App {

  val cantHappen = new Exclusion(((combination: List[Any]) => combination == List("a", 1, true, "randomrandomrandom2")
    || combination(1).asInstanceOf[Int] > 3),
    "This can't happen")

  new DataTableBuilder(
    List(
      Set("a", "b", "c"),
      Set(1, 2, 3, 4),
      Set(true, false),
      Set("randomrandomrandom1", "randomrandomrandom2", "randomrandomrandom3", "randomrandomrandom4", "randomrandomrandom5")),
    List(cantHappen))
    .build
    .foreach(println)
}
