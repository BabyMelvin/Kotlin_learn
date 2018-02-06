package class_

/*扩展:能够扩展一个新功能无需继承或使用先装饰者这样的类任何类型设计模式*/

//1.扩展函数

/**
 * 扩展函数需要被扩展类作为前缀
 */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]  //this对应列表
    this[index1] = this[index2]
    this[index2] = tmp
}

fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val tmp = this[index1]  //this对应列表
    this[index1] = this[index2]
    this[index2] = tmp
}

//2.扩展是静态解析的
//扩展并没有真正改变这个类，扩展和函数相同的时候还是会选择类中的成员函数

//3.可空接收者
fun Any?.toString(): String {

    if (this == null) return "null"
    return toString()
}

//4.扩展属性
val <T> List<T>.lastIndex: Int
    get() = size - 1

fun main(args: Array<String>) {
    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2)
    println(l)

    val l2 = mutableListOf("123", "234", "345")
    l2.swap2(0, 2)
    println(l2.toString())
}
