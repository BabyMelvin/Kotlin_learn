package fun_lambda


//1.阻塞vs挂起
/**
 * TODO:协程计算可以被挂起无需阻塞
 * 协程挂起几乎无代价的。
 * 协程不能随机指令中挂起，只能所谓挂起点挂起，特别标记函数
 * */
//挂起函数
suspend fun doSomething(foo: Int): String {
    return null.toString()
}
 fun <T> async(block:suspend ()->T){}

fun main(args:Array<String>){
    async {
        doSomething(4)
    }

}