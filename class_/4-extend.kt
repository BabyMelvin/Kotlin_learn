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
open class C3

class D3 : C3()

fun C3.foo() = "c"
fun D3.foo() = "d"
fun printFoo(c: C3) {
    println(c.foo())// print c
}

//同名函数和参数的，总是调用成员函数
class C4 {
    fun foo() {
        println("member")
    }
}

fun C4.foo() {
    println("extension")
}//TODO: 输出 member
//签名不相同，则能够重载


//3.可空接收者
// 这样在Kotlin中实现toString()不用检查null;  扩展实现如下。
fun Any?.toString(): String {

    if (this == null) return "null"
    return toString()
}

//4.扩展属性
val <T> List<T>.lastIndex: Int
    get() = size - 1

//5.伴随对象扩展
class MyClass1 {
    companion object {

    }
}

//6.扩展范围
//定义扩展直接在package下面
//package foo.bar1
// fun Baz.goo(){}
//调用
//package com.example.usage
//import foo.bar.goo 导入所有名为goo的扩展
/*fun usage(baz:Baz){
    baz.goo()
}*/
//7.定义扩展为成员函数
class D1 {
    fun bar() {}
}

class C5 {
    //1. 调用
    fun baz() {}

    fun D.foo() {
        bar()   //D.bar
        baz()   //C.baz
    }

    fun caller(d: D) {
        d.foo()  //调用D的扩展
    }

    // 2.避免奇异
    fun D.fqq() {
        toString()  //调用D.toString()
        this@C5.toString()  //调用C5.toString()
    }
}

//3. 可使用open,这意味着这些函数对于分配类是虚拟的，扩展接收类是静态的
open class D4 {
}

class D6 : D4() {
}

open class C6 {
    open fun D4.foo() {
        println("D4.foo in C6")
    }

    open fun D6.foo() {
        println("D6.foo in C6")
    }

    fun caller(d: D4) {
        d.foo()
    }
}

class C7 : C6() {
    override fun D4.foo() {
        println("D4.foo in C7")
    }

    override fun D6.foo() {
        println("D6.foo in C7")
    }
}


fun MyClass1.Companion.foo() {}
//TODO: 调用方法和普通伴随一样:MyClass.foo()
fun main(args: Array<String>) {
    val l = mutableListOf(1, 2, 3)
    l.swap(0, 2)
    println(l)

    val l2 = mutableListOf("123", "234", "345")
    l2.swap2(0, 2)
    println(l2.toString())

    C6().caller(D4())//"D4.foo in C6"
}
