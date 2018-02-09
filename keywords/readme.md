# hard Keywords 
> 不能作为标识符
* as
    * **type cast**:y as String(unsafe),y as? String(safe)
    * **alias import**：import bar.Bar as bBar 
* in
    * a collection or another entity that defines the  `contain` method
    * mark type parameter as contravariant()
```kotlin
//Consumer in, Producer out! 
//out: never consumed
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}
//in:only be consumed and never produced
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, we can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}
```
* null
    * a constant representing an object reference not point any object
* object
    * a class and its instance at the same time
* typealias
    * similar to `typedef`
* var & val
    * property(similar **global or in class**) of local variable
```kotlin
    val isEmpty:Boolean 
        get()=this.size==0
      
    var stringRepresentation:String
        get()=this.toString()
        set(value){
            setDataFromString(value)
        }
```
# soft keywords
> 在应用环境中作为关键字，其他环境可作为标识符(变量名，或函数名称等)
* by
    * delegate the implements of an interface to another object:将接口的实现委托给其他对象
    * delegate the implementation of accessors for property to another object.:将属性访问器委托给一个对象
```kotlin
//类委托：被证明是一种实现继承好的模式，Kotlin支持原生零模板代码。
//Derived能够继承Base接口中的所有公有方法，并委托给某个对象.
interface Base{
    fun print()
}
class BaseImpl(val x:Int):Base{
    override fun print(){print(x)}
}
class Derived(b:Base):Base by b{
    //如果自己内部实现将会覆盖
    //override fun print(){print("abd")}
}
fun main(args:Array<String>){
    val b=BaseImpl(10)
    Derived(b).print()//print 10
}
//属性接收器委托
class Example{
    var p:String by Delegate()
}

class Delegate{
    operator fun getValue(thisRef:Any?,property:KProperty<*>):String{
        return "$thisRef,${property.name}"
    }
    operator fun setValue(thisRef:Any?,property:KProperty<*>,value:String){
        println("$value assigned to ${property.name} in $thisRef")
    }
}
```  
* where
    * 当泛型有多个上界时使用
```kotlin
//fun <T:Comparable<T>> sort  T:Comparable<T>上界形式
fun <T> copyWhenGreater(list:List<T>):List<String>
    where T:CharSequence,
          T:Comparable<T>{
    return list.filter{it>threshold}.map{it.toString}
}
```
# modifier keywords(修饰符关键字)
* actual:多平台的项目
* abstract:标记类或方法抽象的
* const：编译时常量
* crossinline:禁止向内联函数传递不是在lambda本地还回
* expect:选定平台
* external:标记不是Kotlin实现，JNI或者JavaScript
* infix: 1 shl 2只有一个参数
* reified:标记参数类型，运行能获得(实例化模板)
* tailrec:递归调用
* vararg:传递多个参数
##是否继承
> **class 和 fun 默认是 final**
* final:禁止override
* open：允许继承类或者方法
##是否可见
> **默认是public**
* private:当前类和当前文件可见
* protected:类有效，当前类和子类可见
* internal:当前模块可见(同一目录下)
* public:任何地方可见

# 操作符
* 相等
    * 1.结构相等
        * ==,equals()
    * 2.引用相等(两个引用指向同一个对象)
        * === or !==
* ;
   * 同一行多个表达式