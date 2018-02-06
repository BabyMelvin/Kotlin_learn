//整个文件注解
@file:JvmName("FOO")

package others

import com.sun.xml.internal.ws.api.pipe.Fiber
import java.awt.image.AreaAveragingScaleFilter
import javax.swing.RepaintManager
import kotlin.reflect.KClass
import kotlin.reflect.jvm.internal.impl.resolve.constants.KClassValue

/*注解是将元数据附加到代码的方法*/
/**
 * @Target:指定注解元素可能的类型(类，函数，属性，表达式等)
 * @Retention:指定该注解是否存储在编译后的class文件中，以及运行时能否通过反射可见
 * @Repeatable:允许单个元素上多次使用相同的注解
 * @MustBeDocumented:指定该注解是公有API一部分
 * */


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER
        , AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Fancy

//用法
@Fancy
class Foo {
    @Fancy
    fun baz(@Fancy foo: Int): Int {
        return (@Fancy 1)
    }
}

//构造函数,可接受参数
/**
 * 允许的类型：
 * Int,Long原生类型等
 * 字符串
 * 类
 * 枚举
 * 其他注解
 */

annotation class Special(val why: String)

@Special("example")
class Foo1 {}

//注解作为另一个注解的参数,则其名称不以@字符为前缀
annotation class ReplaceWith(val expression: String)

annotation class Deprecated(val message: String, val replaceWith: ReplaceWith = ReplaceWith(""))

@Deprecated("This function is deprecated,use==instead", ReplaceWith("this == other"))

//类作为注解的参数,需使用KClass
annotation class Ann(val arg1: KClass<*>, val arg2: KClass<out Any>)

@Ann(String::class, Int::class)
class MyClass

annotation class HeHe
//注解使用处目标
/*
class Example(@field:HeHe val foo,   //标注java字段
              @get:HeHe val bar,    //标注java的getter
              @param:HeHe val qux    //标注java构造函数参数
)
*/

//同一个目标有多个注解，添加方括号
annotation class Inject

annotation class VisibleForTesting
class Example1 {
    @set:[Inject VisibleForTesting]
    lateinit var collaborator: Collection<Int>
}

//扩展函数接受者参数
fun @receiver:others.Fancy String.myExtension() {}


//lambda表达式
annotation class Suspendable

//val f = @Suspendable { Fiber.sleep(10) }


//2.java注解
annotation class Rule

annotation class Test
class TemporaryFolder {
    fun newFile() {

    }
}

class Tests {
    //将@Rule注解用于属性getter
    @get:Rule
    val tempFolder = TemporaryFolder()

    @Test
    fun simple() {
        val f = tempFolder.newFile()
    }
}

//java编写注解没有定义参数顺序，所以不能使用常规函数调用传递参数，用命名参数语法
/**
 * java中
 * public @interface Ann{
 *      int intValue();
 *      String stringValue();
 * }
 */

annotation class Ann1(val intValue: Int, val stringValue: String)

@Ann1(intValue = 1, stringValue = "abc")
class C1 {}
//数组作为参数

annotation class AnnWithArrayValue(val value: Array<String>)

@AnnWithArrayValue(value = arrayOf("abc", "foo", "bar"))
class B {}

//访问注解实例属性，注解实例值作为属性暴露给kotlin代码
//java
/**
 * public @ interface Anny {
 *  int value();
 * }

fun foo(anny:Anny){
val i=anny.value
}
 */



