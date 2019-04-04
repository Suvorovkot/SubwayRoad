package fintech.school.routefinder

object Main extends App{
  override def main(args: Array[String]): Unit = {

    val list = new EdgeList()
      .addEdge(Edge(0,5,14.0))
      .addEdge(Edge(0,2,9.0))
      .addEdge(Edge(0,1,7.0))
      .addEdge(Edge(1,2,10.0))
      .addEdge(Edge(1,3,15.0))
      .addEdge(Edge(2,3,11.0))
      .addEdge(Edge(2,5,2.0))
      .addEdge(Edge(3,4,6.0))
      .addEdge(Edge(4,5,9.0))

    val list1 = new EdgeList()
      .addEdge(Edge(0,1,7.0))
      .addEdge(Edge(1,2,10.0))
      .addEdge(Edge(3,4,6.0))
      .addEdge(Edge(4,5,9.0))



//    println(RouteMaker.calculate(list,0).distToV(3))
//    println(RouteMaker.calculate(list,0).pathTo(5))
//    println(RouteMaker.calculate(list,0).pathTo(3))
  }
}
