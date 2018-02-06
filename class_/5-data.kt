package class_

/*只保存数据的类*/
/**
 * 编译器自动推导出所有属性
 * equals()/hashCode()对
 * toString()：“User(name=Jhon,age=42)”
 * componentN():按声明顺序对应所有属性
 * copy()函数
 */
data class User(val name: String, val age: Int)

//1.复制

/**
 * 复制一个对象改变它的一些属性，其余保持不变
 */
//fun copy(name:String=this.name,age:Int=this.age)=User(name,age)

val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)

//2.密封类
/**
 * 当一个值为有限集中的类型，不能有任何其他类型时。某种意义是枚举类的扩展
 * 本身是抽象的，不能实例化抽象成员，扩展密封类子类可放在任何位置
 */
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumer : Expr()


//好处如果验证语句覆盖所有情况，无需再加case语句
fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumer -> Double.NaN
}

//3.泛型
class Box<T>(t: T) {
    var value = t
}

//4.型变和类型投影
/**
 * java中通配符类型，kotlin中没有。其他两个东西：declaration-site variance和type projections
 * java中泛型是不型变的，List<String>不是list<Object>的子类。那么和数组差不多了
 */
//一般一个c类型参数声明为T out时，只能出现在输出位置回报是C<Base>安全的作为C<Derived>超类
//生产者
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs
}

//消费者
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0)
    val y: Comparable<Double> = x
}

//类型投影
fun copy(from: Array<out Any>, to: Array<Any>) {

}

fun fill(dest: Array<in String>, value: String) {

}

//星投影
/**
 * Function<*,String>  表示Function<in Nothing,String>
 * Function<Int,*>     表示Function<Int,out Any?>
 * Function<
 */
//5.泛型函数
/*
fun <T> singletonList(item: T):List<T> {
}
fun <T> T.basicToString():String{       //扩展函数
}
val l=singletonList<Int>(1)
val l=singletonList(1)
*/
//5.泛型约束“:”后面就是上界
fun <T : Comparable<T>> sort(list: List<T>) {}

/**
 * sort(listOf(1, 2, 3))
 * sort(listOf(HashMap<Int, String>()))  ??? int not subtype of comparable<int>
 */
//多个上界
/*
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it -> threshold }.map { it.toString() }
}
*/
//类型擦除
/**
 * Kotlin泛型声明用法执行安全只在编译期进行，运行时泛型类型实例不保留关于其类型实参任何信息。
 * 其类型信息被称为擦除，Foo<Bar>与Foo<Bar?>实例被Foo<*>擦除
 *
 */

//6.嵌套类与内部类
class Outer2 {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

class Outer3 {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar
    }
}

//7.匿名内部类
/**
 * window.addMouseListener(object:MouseAdapter){
 *      override fun mouseClicked(e:MouseEvent){
 *          //...
 *      }
 *      override fun mouseEntered(e:MouseEvent){
 *          //...
 *      }
 * }
 */

//8.枚举类
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//初始化
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}
//匿名类
/*
enum class ProtocolState{
    WAITING{
        override fun signal()=TALKING
    },
    TALKING{
        override fun signal()=WAITING
    };
    abstract fun signal():ProtocolState
}*/

//9.使用枚举常量
enum class RGB { RED, GREEN, BLUE }

inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}


fun main(args: Array<String>) {
    //2.数据类和解构声明
    val jane = User("Jane", 35)
    val (name, age) = jane
    println("$name,$age years of age")
    //泛型
    val box = Box<Int>(1)
    //嵌套类声明
    val demo = Outer2.Nested().foo()
    //内部类声明
    val demo2 = Outer3().Inner().foo()
    //匿名内部类
    //val listener=ActionListener{println("clicked")}
    //使用枚举常量
    printAllValues<RGB>()
}
