package class_

/**
 * 语法
 *     var <propertyName>[:PropertyType][=<property_initializer>]
 *          [<getter>]
 *          [<setter>]
 */
class PersonP {

    //1.属性
    var stringRepresention: String
        get() = this.toString()
        set(value) {
            setDataFromString(value)
        }

    //2.幕后字段
    var counter = 0
        set(value) {
            if (value >= 0)
                field = value
        }

    fun setDataFromString(value: String) {

    }

    //3.幕后属性
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null)
                _table = HashMap()
            return _table ?: throw AssertionError("set to null by another thread")
        }
    //4.编译期间常量
    /**
     * const:修饰符标记为编译期常量
     * 1.位于顶层或是object一个成员
     * 2.用string或原生类型初始化
     * 3.没有getter
     */
    //应用在annotations中
    /*
     const var SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

     @Deprecated(SUBSYSTEM_DEPRECATED)
     private fun foo() {
     }
    */
    companion object {
        val SUBSYSTEM_DEPRECATED: String = "this subsystem is deprecated"
    }

    //5.延迟初始化属性与变量
    //正常属性在构造函数中非空申明要初始化。
    lateinit var subject: String
    //检验是否初始化。foo::bar.isInitialized

}

//6.接口中的属性
//TODO: 接口和抽象类不同是，接口不能保存状态。可以有属性，但是要抽象或者提供接收器
//能够提供属性接收器，但是不能使用备用属性，接收器中不能引用它。
interface A3 {
    fun foo() {
        print("A")
    }

    fun bar()
}

interface B3 {
    fun foo() {
        print("B")
    }

    fun bar() {
        print("Bar")
    }
}

interface MyInterface : A3, B3 {
    override fun bar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val prop: Int    //抽象的
    val propertyWithImplementation: String
        get() = "foo"

    override fun foo() {
        //TODO: 避免冲突
        super<A3>.foo()
        super<B3>.foo()
        print(prop)
    }

}

//8.伴生对象的扩展
class MyClass {
    companion object {}
}

fun MyClass.Companion.foo() {

}

//9.扩展声明为成员,this冲突情况，扩展接受者优先
class D {
    fun bar() {}
}

class C2 {
    fun baz() {}
    fun D.foo() {
        toString()          //调用D.toString()
        this@C2.toString() // 调用C.toString()
        bar()
        baz()
    }

    fun caller(d: D) {
        d.foo()
    }
}
//10.动机


class Child : MyInterface {
    override val prop: Int
        get() = 20  //To change initializer of created properties use File | Settings | File Templates.
    //调用伴生对象普通成员
    val a = MyClass.foo()


}
