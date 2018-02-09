//TODO: with用法

class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}

fun main(args: Array<String>) {

    //调用一个实例的多个方法，用with
    val myTurtle = Turtle()
    with(myTurtle) {
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
    }
    //java7's try with resources
    /*
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
    */
    // 能够接受null Boolean 类型
    /*val b:Boolean?=....
    if (b==true){

    }else{
        //`b` is false or null
    }*/
}

