// *************************
//  Do not modify this file!
// *************************
//  You are welcome to read this file and to try to understand it, but you should not make edits.
//  Your assignment is to edit tasks.kt and then run this file, assuming tasks.kt is in the same project as main.kt
//  The code in this file will test your task functions and indicate which task functions pass.
//
//  main.kt
//  Week 3 Tasks
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
    val liquid = test1.first
    val tablet = test1.second

    check(liquid is LiquidMedicationContainer) { "The first value from the pair must be a LiquidMedicationContainer"}
    check(tablet is TabletMedicationContainer) { "The first value from the pair must be a TabletMedicationContainer"}
}

fun testTask1() {
    val test1 = task1()
    check(test1 != null) { "You must return Pair<LiquidMedicationContainer, TabletMedicationContainer>"}

    val liquid = test1.first
    val tablet = test1.second

    check(liquid.isExpired) { "The liquid medication must be expired" }
    check(!tablet.isExpired) { "The tablet medication should not be expired" }
}

fun testTask2() {
    val tracker = task2()
    check(tracker != null) { "Please return a PharmaceuticalStockTracker rom task2" }

    val liquid = LiquidMedicationContainer("liquid", futureDate(7), 4.5, 2, "ml")
    var tablet = TabletMedicationContainer("tablet", futureDate(7), 90, 2.3, "mg")

    tracker.addContainer(liquid)
    check(tracker.count("liquid") == 1) { "There should be a single medication named liquid"}
    check(tracker.count("tablet") == 0) { "There should not be any tablet medications"}

    tracker.addContainer(tablet)

    check(tracker.count("liquid") == 1) { "There should be a single medication named liquid"}
    check(tracker.count("tablet") == 1) { "There should be a single medication named tablet"}
}

fun testTask3() {
    val test1 = task3(0, 100)
    check(test1 is IntRange) { "if int1 is smaller than int2, return a IntRange"}
    val test2 = task3(100, 0)
    check(test2 is List<*>) { "If int1 is bigger than int2, return a List from int2 to int1" }
    check(test2[0] == 100) { "int1 is 100, that means that the first element in the list should be 100" }
    check(test2.last() == 0) { "int2 is 0, which means the last element in the array should be 0" }
}

fun testTask4() {
    val test1 = task4(0, 100)
    check(test1 == 1) { "You should return 1 if task3 returns an IntRange" }
    val test2 = task4(100, 0)
    check(test2 == 2) { "You should return 2 if task3 returns an List<Int>" }
}

fun testTask5() {
    val test1 = task5(listOf(1, 2.005, "5.223", null, null, "ok", 1000))
    check(test1[0] == 1) { "Int values should convert to 1"}
    check(test1[1] == 2) { "Double values should convert to 2"}
    check(test1[2] == 3) { "String values should convert to 3"}
    check(test1[3] == 3) { "String values should convert to 3"}
    check(test1[4] == 1) { "Int values should convert to 1"}
}

fun testTask6() {
    val test1 = task6(listOf(10, 5.35, "5.223", null, null, "ok", 1))
    check(test1[0] == 10.0) { "Int can convert to a double, should be 10.0"}
    check(test1[1] == 5.35) { "Double needs no conversion"}
    check(test1[2] == 5.223) { "String can be converted to a double if they are the correct format"}
    check(test1[3] == 1.0) { "Int values should convert to 1"}
}

fun testTask7() {
    val meds = task7()
    check(meds != null) { "Make sure to return a pair from task7() and not null!"}
    val test1 = meds.first.toString()
    val test2 = meds.second.toString()
    check(test1 == "Liquid: ${meds.first.id}") { "toString() should look like this: Liquid: ${meds.first.id}"}
    check(test2 == "Tablet: ${meds.second.id}") { "toString() should look like this: Tablet: ${meds.second.id}"}
}

fun testTask8() {
    val meds = task8()
    val liquids = meds.first
    val tablets = meds.second
    check(liquids.first == liquids.second) { "Both liquids have the same id, they should be equal"}
    check(tablets.first != tablets.second) { "The tablets are not the same id, they shouldn't be equal"}
}

fun testTask9() {
    val tracker = task9()
    tracker.addContainer(LiquidMedicationContainer("med1", futureDate(7), 4.5, 2, "ml"))
    check(tracker.count("med1") == 1) { "There should be one medication in the tracker"}
    tracker.removeAllContainers()
    check(tracker.count("med1") == 0) { "removeAllContainers should remove all of the containers"}
}
