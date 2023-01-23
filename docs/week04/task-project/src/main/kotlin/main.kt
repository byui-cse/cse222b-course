import org.w3c.dom.css.Rect
import kotlin.math.roundToInt

// *************************
//  Do not modify this file!
// *************************
//  You are welcome to read this file and to try to understand it, but do not make edits.
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
    val expired = LiquidMedicationContainer("12345-1234-12", "med1", futureDate(-120),
        4.5,  2,  "ml")
    val notExpired = LiquidMedicationContainer("12345-1234-12", "med1", futureDate(120),
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
    check(test1) { "This is a valid code, task2 should return true" }

    val test2 = task2("")
    check(!test2) { "This is not valid code, task2 should return false" }

    val test3 = task2("33333-44-4444")
    check(!test3) { "This is not valid code, task2 should return false" }
}

fun testTask3() {
    val tracker = task3()
    val invalid = LiquidMedicationContainer("invalid", "med1", futureDate(120),
        4.5,  2,  "ml")
    val test1 = tracker.addContainer(invalid)
    check(!test1) { "Invalid ndc code, it should not add it"}

    val test2 = tracker.addContainers("55555-4444-22", setOf())
    check(test2 == AddMessage.EMPTY_CONTAINER_SET) { "Should not add an empty set of containers"}

    val test3 = tracker.addContainers("invalid", setOf(invalid))
    check(test3 == AddMessage.NDC_CODE_FORMAT_ERROR) { "Invalid codes shouldn't be added" }

    var one = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(120),
        4.5,  2,  "ml")
    var two = LiquidMedicationContainer("55555-4444-21", "med1", futureDate(120),
        4.5,  2,  "ml")

    val test4 = tracker.addContainers("55555-4444-22", setOf(one, two))
    check(test4 == AddMessage.MIXED_NDC_CODES) { "Mixed codes are not allowed" }

    one = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(120),
        4.5,  2,  "ml")
    two = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(120),
        4.5,  2,  "ml")

    val test5 = tracker.addContainers("55555-4444-22", setOf(one, two))
    check(test5 == AddMessage.SUCCESS) { "Everything should work at this point!"}
}

fun testTask4() {
    val tracker = task4()
    val test1 = tracker.currentStock("55555-4444-2")
    check(test1.first == StockMessage.NDC_CODE_FORMAT_ERROR) { "Bad code detected!"}

    // Add some stock to test
    val one = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(120),
        4.5,  2,  "ml")
    val two = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(120),
        4.5,  2,  "ml")

    tracker.addContainers("55555-4444-22", setOf(one, two))
    val test2 = tracker.currentStock("55555-4444-22")
    check(test2.first == StockMessage.SUCCESS) { "There should be stock in the stock tracker" }
    check(test2.second.count() == 2) { "There is a stock of 2 medications in there" }

    val test3 = tracker.currentStock("55555-4444-11")
    check(test3.first == StockMessage.NO_INVENTORY) { "There is no inventory for that ndc code" }
    check(test3.second.isEmpty()) { "There is no stock, so there should be an empty list" }
}

fun testTask5() {
    val tracker = task5()
    val test1 = tracker.sellContainers(0, "invalid")
    check(test1.first == SellMessage.NDC_CODE_FORMAT_ERROR) { "Should return SellMessage.NDC_CODE_FORMAT_ERROR on an invalid ndc code" }

    // Add some stock to test
    val one = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(90),
        4.5,  2,  "ml")
    val two = LiquidMedicationContainer("55555-4444-22", "med1", futureDate(120),
        4.5,  2,  "ml")

    tracker.addContainers("55555-4444-22", setOf(one, two))

    val test2 = tracker.sellContainers(3, "55555-4444-22")
    check(test2.first == SellMessage.NOT_ENOUGH_INVENTORY) { "You cannot sell more items than exist"}

    val test3 = tracker.sellContainers(0, "55555-4444-22")
    check(test3.first == SellMessage.INVALID_COUNT) { "You cannot sell 0 items"}

    val test4 = tracker.sellContainers(1, "55555-4444-21")
    check(test4.first == SellMessage.NO_INVENTORY) { "You cannot sell items that do not exist"}

    val test5 = tracker.sellContainers(1, "55555-4444-22")
    check(test5.first == SellMessage.SUCCESS) { "Should properly sell items if they exist to sell" }
    check(test5.second.count() == 1) { "One should be sold "}

    val test6 = tracker.sellContainers(2, "55555-4444-22")
    check(test6.first == SellMessage.NOT_ENOUGH_INVENTORY) { "After selling one, there should not be two left to sell" }
}

fun testTask6() {
    val test1 = task6(listOf(1, 2, 3))
    check(test1[1] == 1)
    check(test1[2] == 1)
    check(test1[3] == 1)

    val test2 = task6(listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
    check(test2[1] == 10)
}

fun testTask7() {
    val test1 = task7()
    check(test1.count() == 5) { "There should be 5 person objects" }
    val idNumbers = test1.map { it.idNumber }.toSet()
    check(idNumbers.count() == 5) { "All idNumbers should be unique" }

    val personOne = test1.first()
    val personTwo = test1.last()

    personOne.idNumber = "same"
    personTwo.idNumber = "same"
    val same = setOf(personOne, personTwo)
    check(same.count() == 1) { "If two PersonRecord objects have the same idNumber, both should not be allowed in a set"}
}

fun testTask8() {
    val c = Circle(10.0)
    val test1 = task8(c)
    check(test1.roundToInt() == 314) { "Area of circle should be around 314"}

    val t = Triangle(10.0, 10.0)
    val test2 = task8(t)
    check(test2.roundToInt() == 50) { "Area of the triangle should be 50"}

    val r = Rectangle(10.0, 10.0)
    val test3 = task8(r)
    check(test3.roundToInt() == 100) { "Area of the rectangle should be 100" }
}

fun testTask9() {
    val test1 = task9(listOf(1, 2, 3, 3, 4, 5, 6))
    check(test1 == listOf(1, 2, 3, 4, 5, 6)) { "There is an extra 3 in this list"}

    val bigList = listOf((0..1000000)).flatten()
    val test2 = task9(bigList)
    check(test2.count() == bigList.count()) { "There are no duplicates in here" }
}
