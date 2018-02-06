package base

//1.数字
/**
 * Double   64
 * Float    32
 * Long     64
 * Int      32
 * Short    16
 * Byte     8
 */
//2.数字字面值下划线
val oneMillion = 1_000_000

fun bitComputer() {
    /**
     *  shl(bits)  有符号左移<<
     *  shr(bits)  有符号右移>>
     *  ushr       无符号右移 >>>
     *  and        位与  &
     *  or         位或  |
     *  xor        位异或 ^
     */
    val x = (1 shl 2) and 0x000F_F000
}

fun floatCom() {
    /**
     * a ==b a!=b
     * a<b,a>b,a<=b,a>=b
     * 区间与区间检测,a..b,x in a..b, x !in a..b
     * NaN无穷大
     */
}

fun checkChar(c: Char) {
    /*
   if(c==1){
    错误，不能当成数字
   }
   */
}

fun arrayFun() {
    //1.创建一个数组并传递元素值给它
    var a = arrayOf(1, 2, 3)
    //2.创建一个指定大小，元素为空数组
    var b = arrayOfNulls<Int>(5)
    //3.可接受数组大小和一个函数参数的Array构造函数，能都还回给定索引的初始值
    val asc = Array(5, { i -> (i * i).toString() })
    //TODO Kotlin数组是不型变的。不能将Array<String>赋值给Array<Any>
}

fun stringFun(){
    var s:String="hello world"
    for(c in s){
       println(c)
    }
    //1.转义和原生
    val text="""
            for(c in "foo"){
                print(c)
            }
        """.trimMargin()
    //2.$不支持转义，用'$'表示
}
fun importFun(){
    /**
     * import foo.Bar
     * import bar.Bar as bBar
     *  名字冲突时，可用as
     */
}
fun main(args: Array<String>) {
    //3.表示方式
    // 不必保留同一性
    val a: Int = 10_000
    println(a === a)
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA)
    // 保留相等性
    val b: Int = 1000
    print(b == b)
    val boxedB: Int? = b
    val boxedC: Int? = b
    println(boxedB == boxedC)
    //4.显示转换：TODO：较小类型不能隐式转换成较大类型
    val b_: Byte = 1  //ok 字面值是静态类型
    //val i_:Int=b_  错误
    //可以进行显示的转换
    val i_: Int = b.toInt()
    /**
     * toByte():Byte
     * toShort():Short
     * toInt():Int
     * toLong():Long
     * toFloat():Float
     * toDouble():Double
     * toChar():Char
     */
    //算术可以进行简单重载适当转换
    val l = 1L + 3
    //5.位运算
    bitComputer();
    //6.浮点数比较
    floatCom()
    //7.TODO：字符Char不能直接当做数字
    checkChar('a')
    //8.布尔运算
    /**
     * || 短路逻辑或
     * && 短路逻辑与
     * !  逻辑非
     */
    //9.数组.TODO.使用Array来约定
    arrayFun()
    //10.字符串
    stringFun()
    //11.导包
    importFun()
}
