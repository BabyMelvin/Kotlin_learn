package others

import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

/**
 * java中反射所需运行时单独jar文件，减少不适用反射功能应用程序所需要运行时库大小
 * 确保.jar文件添加到了classpath中
 * */

//1.类应用，获取静态已知Kotlin类引用,引用的是KClass值
val c = MyClass::class
//要获取java类引用 TODO: c.java
val d = MyClass::class.java


//3.函数引用,定义了这个函数
fun isOdd(x: Int) = x % 2 != 0

//4.属性引用
/**
 * Kotlin一等对象访问，也可以使用::运算符
 * ::x求值为KProperty<Int>类型属性对象
 */
val x = 1
var y = 1

//访问类的成员属性
class A(val p: Int) {

}

//对属性的扩展
val String.lastChar: Char
    get() = this[length - 1]

//3.和java互相操作
fun main(args: Array<String>) {
    //2.绑定类引用
    val a: Int = 2
    assert(a is Int) { "Bad widget:${a}::class.equalifiedName" }
    val numbers = listOf(1, 2, 3)
    //函数引用
    println(numbers.filter(::isOdd))
    //::isOdd函数类型(Int)->Boolean的一个值
    //函数重载
    /**
     * String::toCharArray为类型String提供一个扩展函数:String.()->CharArray
     */
    //val predicate: (String) -> Boolean = ::isOdd

    //4.属性引用
    println(::x.get())
    println(::x.name)
    ::y.set(2)
    println(y)
    //属性引用可以用不需要参数韩输出
    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length))

    val prop = A::p
    println(prop.get(A(1)))
    println(String::lastChar.get("abc"))

    //3.和java互相操作
    println(A::p.javaGetter)
    println(A::p.javaField)

}

