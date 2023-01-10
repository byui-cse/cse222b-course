import org.w3c.dom.css.Rect
import kotlin.math.roundToInt

// *************************
//  Do not modify this file!
// *************************
//  You are welcome to read this file and to try to understand it, but you should not make edits.
//  Your assignment is to edit tasks.kt and then run this file, assuming tasks.kt is in the same project as main.kt
//  The code in this file will test your task functions and indicate which task functions pass.
//
//  main.kt
//  Week 4 Tasks
//

fun main(args: Array<String>) {
    testTask0()
    testTask1()
    testTask2()
    testTask3()
    testTask4()
    testTask5()
    testTask6()
    testTask7()
    testTask8()
    testTask9()

    println("")
    println("*********************************")
    println("All Tasks Completed Successfully!")
    println("*********************************")
    println("")
}

fun testTask0() {
}

fun testTask1() {

}

fun testTask2() {

}

fun testTask3() {

}

fun testTask4() {

}

fun testTask5() {

}

fun testTask6() {

}

fun testTask7() {

}

fun testTask8() {

}

fun testTask9() {
    val c = Circle(10.0)
    val test1 = task9(c)
    check(test1.roundToInt() == 314) { "Area of circle should be around 314"}

    val t = Triangle(10.0, 10.0)
    val test2 = task9(t)
    check(test2.roundToInt() == 50) { "Area of the triangle should be 50"}

    val r = Rectangle(10.0, 10.0)
    val test3 = task9(r)
    check(test3.roundToInt() == 100) { "Area of the rectangle should be 100" }
}
