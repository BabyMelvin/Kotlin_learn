package fun_lambda

import java.util.concurrent.locks.Lock

//1.高阶函数：将函数作为返回值

fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    } finally {
        lock.unlock()
    }
}
//2.lambda表达式
/**
 * lambda表达式大括号包着
 * 参数在->前面
 * 函数体在->后面
 */

fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this)
        result.add(transform(item))
    return result
}


fun add(a: Int, b: Int): Int = a + b
fun toBeSychronized(a: Int, b: Int) = add(a, b)

fun <K, R> Map<K, R>.forEach(transform: (K, R) -> R): Map<K, R> {
    val result = mapOf<K, R>()
    for (item in result.entries)
        transform(item.key, item.value)
    return result
}

//3.Lambda表达式与匿名函数
fun <T> max(collection: Collection<T>, less: (T, T) -> Boolean): T? {
    var max: T? = null
    for (it in collection)
        if (max == null || less(max, it))
            max = it
    return max
}

//4.lambda表达式语法,TODO: 表达式中不是返回Unit,则最后一个表达式为返回类型
val sum = { x: Int, y: Int -> x + y }

fun main(args: Array<String>) {
    /*
    1. 形式
    val result = lock(lock, ::toBeSychronized)
    2.形式
    val result=lock(lock,{add(a,b)})
    */
    val doubled = listOf(1, 2, 3).map { value -> value * 2 }
    //it,单个参数隐式名称
    val doubled1 = listOf(1, 2, 3).map { it * 2 }
    //LINQ-风格
    listOf<String>("123", "234", "345").filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }
    //下划线用于未使用变量
    mapOf<String, Any?>(
            "amp" to "123",
            "he" to "jd"
    ).forEach { _, value -> println("$value") }
    //调用max匿名函数
    max(listOf("123", "234"), { a, b -> a.length < b.length })
}