package base

fun ifFun() {
    /**
     * 作为表达式 val max=if(a>b) a else b
     *          val max=if(a>b){
     *             print("choose a")
     *          }else{
     *             print("choose b")
     *          }
     */
}

fun whenFun(x: Int) {
    when (x) {
        1 -> print("x==1")
        2 -> print("x==2")
        3, 4 -> print("x==3 or x==4")
        in 5..10 -> print("x 5到10中")
        else -> print("x is neither 1 nor 2")
    }
    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }
}

fun forFun() {
    //1.形式
    /**
     * for(item in collection) print(item)
     *
     */
    //2.代码块
    /**
     * 不会产生中间变量
     * for (item in array.indices){
     *    print(array[i]
     * }
     */
    //3.或者库函数
    /**
     * for((index,value) in array.withIndex()){
     *    println("the element at $index is $value")
     *}
     */
}

fun retrieveData() {

}

fun whileFun(y: Int) {
    var x: Int = 10
    while (x > 0) {
        x--
    }
    do {
        val y = retrieveData()
    } while (y != null)

}

fun labelFun() {
    var a: Int = 20
    //1.返回整个函数
    /**
     * a.forEach{
     *   if(a ==0)  return直接返回labelFun
     *   print(a)
     * }
     */
    //2.返回到标签位置

    /**
     * ints.forEach lit@{
     *    if(a==0) return @lit
     *    print(a)
     * }
     */
    //3.返回到标签位置
    /**
     * fun foo(){
     *     ints.forEach{
     *          if(a==0) return @forEach
     *          print(a)
     *     }
     * }
     */
    //4.匿名函数代替lambda表达式
    /**
     * ints.forEach(fun(value:Int){
     *      if(value==0) return
     *      print(value)
     * })
     */
    //5.return@a 1
    /**
     * 从标签@a返回1
     */
}

fun main(args: Array<String>) {
    //1.if 表达式
    ifFun()
    //2.when取代switch
    whenFun(3)
    //3.for循环
    forFun()
    //4.while循环
    whileFun(20)
    //5.标签处返回
    labelFun()
}