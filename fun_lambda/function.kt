package fun_lambda

import java.util.concurrent.locks.Lock

//1.默认参数，函数参数可以省略默认值
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {

}

open class A {
    open fun foo(i: Int = 10) {}
}

class B : A() {
    override fun foo(i: Int) { //不能有默认参数
        super.foo(i)
    }
}

//如果一个默认参数在一个无默认参数之前，只能通过命名参数调用函数来使用
fun foo(bar: Int = 0, baz: Int) {}

//TODO: lambda参数从括号外传递给函数调用,允许不传递值
fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {}

//中缀表示法
/**
 * 成员函数或扩展函数
 * 只有一个参数
 * 用infix关键字标注
 */
//1 shl 2 等同于1 shl(2)
infix fun Int.shl(x: Int): Int {
    //...
    return 0
}

//2.函数作用域
//函数可以在文件顶层声明，可以作为局部或者扩展函数
//递归函数
tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))


//3.匿名函数
/**
 * fun(x: Int, y: Int): Int = x + y
 * fun(x:Int,y:Int):Int{
 *      return x+y
 * }
 * */
//5.接收者的函数字面值
fun receiver() {
    val sum2 = fun Int.(other: Int): Int = this + other
    val represents: String.(Int) -> Boolean = { other1 -> toIntOrNull() == other1 }
    println("123".represents(123))
    fun testOperation(op: (String, Int) -> Boolean, a: String, b: Int, c: Boolean) = assert(op(a, b) == c)
    testOperation(represents, "100", 100, true)
}

//接受者类型可上下文推断时，lambda表达式可作为待接收者的函数字面值
class HTML {
    fun body() {}
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

// 6.内联函数
/*使用高阶函数会带来一些运行效率损失，使用内联消除这类开销
*
inline fun <T> lock(lock: Lock, body: () -> T): T
*/

// 实例化泛型 reified type parameters
fun <T> TreeNode.findParentOfType(clazz:Class<T>):T?{
    var p=parent
    while(p!=null&&!clazz.isInstance(p)){
        p=p.parent
    }
    @Suppress("UNCHEKED_CAST")
    return p as T?
}
//导致要这样调用
treeNode.findParentOfType(MyTreeNode::class.java)
//想要成这样调用
treeNode.findParentOfType<MyTreeNode>()
//这就用到reified
inline fun<reified T> TreeNode.findParantOfType():T?{
    var p =parent
    while(p!=null&&p! is T){
        p=p.parent
    }
    return p as T?
}

fun main(args: Array<String>) {
    //只能这样调用
    foo(baz = 1)
    foo(1) { println("hello") }
    foo { println("hello") }
    //4.闭包
    var sum = 0
    listOf(1, 2, 3).filter { it > 0 }.forEach {
        sum += it
    }
    print(sum)
    html {
        body()
    }
}
