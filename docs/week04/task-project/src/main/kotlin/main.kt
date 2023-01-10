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
    val test1 = task0()
    check(test1.first is LiquidMedicationContainer) { "The project should compile at this point" }
    check(test1.second is TabletMedicationContainer) { "The project should compile at this point" }
}

fun testTask1() {
    val tracker = task1()
    val expired = LiquidMedicationContainer("12345-123-12", "med1", futureDate(-120),
        4.5,  2,  "ml")
    val notExpired = LiquidMedicationContainer("12345-123-12", "med1", futureDate(120),
        4.5,  2,  "ml")
    tracker.addContainer(expired)
    tracker.addContainer(notExpired)
    check(tracker.count == 2) { "The PharmaceuticalStockTracker should have 2 medication in it at this point" }

    // Remove all expired, there should be one afterwards
    tracker.removeExpired()
    check(tracker.count == 1) { "There should only be one left after removing the expired ones" }
}

fun testTask2() {
    val test1 = task2("55555-4444-22")
    check(test1) { "This is a valid code, task2 should return false" }

    val test2 = task2("")
    check(!test2) { "This is not valid code, task2 should return false" }

    val test3 = task2("33333-44-4444")
    check(!test3) { "This is not valid code, task2 should return false" }
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
