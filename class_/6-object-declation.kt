package class_

import sun.text.normalizer.Trie

/*对象表达式和对象声明*/

//1.对象表达式
/**
 * window.addMouseListener(object:MouseAdapter(){
 *      var clickCount=0
 *      var enterCount=0
 *     override fun mouseClicked(e:MouseEvent){
 *         clickCount++
 *     }
 *     override fun mouseEntered(e:MouseEvent){
 *          enterCount++
 *     }
 * })
 * */
//超类有构造函数，则必须传递参数给它
open class A1(x: Int) {
    open val y: Int = x
}

interface B1 {}

//只需要一个对象 ，不需要超类
fun foo1() {
    val adHoc = object {
        val x: Int = 0
        val y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

//匿名对象最为公有返回类型或公有属性类型，实际类型是匿名对象声明的超类，没有任何超类，则是Any
//匿名对象中添加成员将无法访问
class C1 {
    //私有函数，所以其返回类型是 TODO: 匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    //公有函数，所以其返回类型是 TODO: Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x
        //val x2 = publicFoo().x TODO: 未能解析器引用
    }
}


//2.对象声明
//单例模式
interface DataProvider {}

object DataProviderManager {
    fun registerDataProvider(provider: DataProvider) {

    }

    // val allDataProvides: Collection<DataProvider>
    //    get() =//...
}

//3.伴生对象
class MyClassB {
    companion object {
        fun create(): MyClassB = MyClassB()
    }
}

//TODO: 伴生对象像静态成员，运行时任然真实对象实例成员
interface Factory<T> {
    fun create(): T
}

class MyClassC {
    companion object : Factory<MyClassC> {
        override fun create(): MyClassC {
            return MyClassC()
        }

    }
}

fun main(args: Array<String>) {

    val ab: A1 = object : A1(1), B {
        override val y = 15
    }
    //声明 ,伴生对象
    //DataProviderManager.registerDataProvider(...)
    val x = MyClassB.Companion
}
