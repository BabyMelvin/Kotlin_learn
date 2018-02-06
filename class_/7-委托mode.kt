package class_

import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 委托对象要求
 * 1.只读属性，必须提供一个getValue函数，参数
 *      thisRef---属性所有者类型相同或者是其超类
 *      property--必须是类型KProperty<*>或其超类
 * 2.可变属性，额外提供setValue
 *      thisRef---同上
 *      property--同上
 *      new value-必须属性类型相同，或是其超类
 *
 * */
//1.委托
/*委托模式，是继承很好的替代方式*/
interface Base1 {
    fun print()
}

class BaseImpl(val x: Int) : Base1 {
    override fun print() {
        print(x)
    }
}

/**
 * 超类列表中by-子句表示b将会Derived1中内部存储，并且编译器将Base方法转发给b。
 */
class Derived1(b: Base1) : Base1 by b

//2.委托属性
/**
 * 延迟属性(lazy properties)：其值只在首次访问时计算
 * 可观察属性(observable properties):监听器会收到有关此属性变更的通知
 * 多个属性存储在一个映射中(map)，不是每个单独存放的字段中
 */
class Delegate {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef ,thank you for delegating '${property.name}' to me"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to ${property.name} in $thisRef")
    }
}

class Example {
    var p: String by class_.Delegate()
}

//3.标准委托
//延迟属性Lazy,第一次会计算表达式，第二次只计算lazy
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

//可观察observable:两个参数：初始化值和修改时处理程序(handler),赋值时调用该处理程序。
//handler有三个参数：被赋值的属性、旧值和新值。
class User1 {
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$old->$new")
    }
    //vetoable()
}

//属性存储在映射中
class User2(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

//将只读Map换成MutableMap
class MutableUser(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
}

//4.局部委托属性：可以将局部变量声明为委托属性
/*
fun example(computeFoo: () -> User) {
    val memoizedFoo by lazy(computeFoo)
    if(someCondition && memoizedFoo.isValid()){
       memoizedFoo.doSomething()
    }
}*/
//5.在绑定之前检查属性名称
/*
class ResourceID<T> {

}

class ResourceDelegate<T> : ReadOnlyProperty<MyUI, T> {
    override fun getValue(thisRef: MyUI, property: KProperty<*>): T {
        return " "
    }
}

class ResourceLoader<T>(id: ResourceID<T>) {
    operator fun provideDelegate(thisRef: MyUI,
                                 prop: KProperty<*>):
            ReadOnlyProperty<MyUI, T> {
        return ResourceDelegate()
    }
}

class MyUI {
    fun <T> bindResource(id: ResourceID<T>): ResourceLoader<T> {}
    val image by bindResource(ResourceID.image_id)
    val text by bindResource(ResourceID.text_id)
}
*/
fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived1(b).print()
    //委托属性
    val e = Example()
    e.p = "New"
    println(e.p)
    //延迟属性lazy
    println(lazyValue)
    println(lazyValue)

    //可观察
    val user = User1()
    user.name = "first"
    user.name = "second"

    //映射参数
    val user2 = User2(mapOf(
            "name" to "john doe",
            "age" to 25))
}
