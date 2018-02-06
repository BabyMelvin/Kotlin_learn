package class_

import java.awt.Container

//1.类的声明,主构造函数没有任何注解@Inject或可见性 修饰符（public）可省略constructor
//非抽象类没有申明任何（主或次）构造函数，默认无参数且为public。要非构成的话，需要声明一个private
class Person constructor(firstName: String) {
    init {
        //作为主构造函数初始化
    }
}

//2.继承,TODO：所有类继承一个超类Any.
//      TODO：默认情况下所有都是final，open申明才能继承
//      final类是不能开放成员的
/**
 * Any只有这三个方法
 *      equals()
 *      hashCode()
 *      toString()
 */
open class Base(p: Int)

open class BaseB {
    constructor(name: String) {

    }
}

class Derived(p: Int) : Base(p)//有主构造函数可以直接用主构造函数就地初始化
class DerivedB(p: Int, name: String) : BaseB(name) {
    constructor(p: Int, name: String, ctx: Container) : this(p, name)
}
//3.覆盖方法

open class Bas {
    open var x: Int = 3
        get() = 2

    open fun v() {
        print("base:super")
    }

    open fun nv() {

    }
}

class Deriv() : Bas() {
    //1.覆盖方法
    override fun v() {
        super.nv()
        println("deriv:child")
    }

    //2.覆盖属性,可用var覆盖val,反之不可以。
    override var x: Int = 2

    //内部类
    inner class Baz {
        fun g() {
            println(x)
            super@Deriv.v()
            println(super@Deriv.v())
        }
    }
}

// 4.多继承
open class A {
    open fun f() {
        print("A")
    }

    fun a() {
        print("a")
    }
}

interface B {
    fun f() {
        print("B")
    }

    fun b() {
        print("b")
    }
}

class C() : A(), B {
    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}

//5. 抽象类
open class Bas1 {
    open fun f() {}
}

//6.半生对象
/**
 * TODO: 没有静态类
 * 声明一个伴生对象，可以先java一样使用静态方法相同语法调用成员
 */
abstract class De : Bas1() {
    abstract override fun f()
}

/********************************************/
class InitOrderDemo(name: String) {
    val firstProperty = "First property:$name".also(::println)

    //1.主构造函数
    init {
        println("First initializer block that print$name")
    }

    val secondProperty = "Second property:${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }

    //2.次构造函数,每次构造函数需要委托给主构造函数
    constructor(name: String, parent: Person) : this(name) {

    }
}

fun main(args: Array<String>) {
    //对象声明，没有new关键字
    InitOrderDemo("hello")
    Deriv()
    Deriv().Baz().g()
}
