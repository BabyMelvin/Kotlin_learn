package class_

/*可见性*/

//1.包
/**
 * 四个可见性修饰符,默认可见性是Public
 * private    声明的文件可见
 * protected  不适用顶层声明
 * internal   相同模块处可见
 * public     随处可见
 * */
fun haz() {}

class Hello {

}

//2.接口和类
/**
 * 四个可见性修饰符,默认可见性是Public
 * private    这个类中可见
 * protected  子类中可见
 * internal   能见到本模块中可见
 * public     该类任何成员都可可见
 * */
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4 //默认public

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    override val b = 5
}

class Unrelated(o: Outer) {
    init {
        println(o.c)
        print(o.d)
    }
}

//3.构造函数
class CA private constructor(a: Int) {}

//4.模块
//TODO: 一个模块是编译在一起的一套Kotlin文件