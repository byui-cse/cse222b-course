// *************************
//  Do not modify this file!
// *************************
//  You are welcome to read this file and to try to understand it, but you should not make edits.
//  Your assignment is to edit tasks.kt and then run this file, assuming tasks.kt is in the same project as main.kt
//  The code in this file will test your task functions and indicate which task functions pass.
//
//  main.kt
//  Week 5 Tasks
//

fun main(args: Array<String>) {
    testTask0()
    testTask1()
    testTask2()
    testTask3()
    testTask4()

    println("")
    println("*********************************")
    println("All Tasks Completed Successfully!")
    println("*********************************")
    println("")
}

fun testTask0() {
    val operations = task0()
    val test1 = operations[IntOperationType.ADD]?.let { it(2, 2) }
    check(test1 == 4) { "Make sure to add an addition function to the map"}

    val test2 = operations[IntOperationType.SUBTRACT]?.let { it(2, 2) }
    check(test2 == 0) { "Make sure to add an subtraction function to the map"}

    val test3 = operations[IntOperationType.MULTIPLY]?.let { it(4, 4) }
    check(test3 == 16) { "Make sure to add an multiply function to the map"}

    val test4 = operations[IntOperationType.DIVIDE]?.let { it(4, 2) }
    check(test4 == 2) { "Make sure to add an divide function to the map"}
}

fun testTask1() {
    val add = task1(2, 2)
    val test1 = add?.let { it() }
    check(test1 == 4) { "Your function should add the two values together" }

    val test2 = add?.let { it() }
    check(test2 == 4) { "Your function should add the two values together" }
}

fun testTask2() {
    val exec1 = task2 { 2 + 2 }
    val test1 = exec1?.let { it() }
    check(test1 == 4) { "Your function should simply call the parameter function and return its value" }

    val exec2 = task2 { 2 * 4 }
    val test2 = exec2?.let { it() }
    check(test2 == 8) { "Your function should simply call the parameter function and return its value" }
}

fun testTask3() {
    val test1 = task3(IntOperationType.ADD, { 2 }, { 2 })
    check(test1 != null)
    check(test1() == 4) { "Make sure to run the operation requested"}

    val test2 = task3(IntOperationType.SUBTRACT, { 2 }, { 1 })
    check(test2 != null)
    check(test2() == 1) { "Make sure to run the operation requested"}

    val test3 = task3(IntOperationType.MULTIPLY, { 3 }, { 2 })
    check(test3 != null)
    check(test3() == 6) { "Make sure to run the operation requested"}

    val test4 = task3(IntOperationType.DIVIDE, { 4 }, { 2 })
    check(test4 != null)
    check(test4() == 2) { "Make sure to run the operation requested"}
}

fun testTask4() {
    val operationMap = task4()
    check(operationMap != null) { "Please return an operation lambda from task0"}

    val test1 = AnyValue("Hello, World!")
    check(!test1.isInt)
    check(test1.isString)

    val test2 = AnyValue(1)
    check(test2.isInt)
    check(!test2.isString)

    val test3 = operationMap(OperationType.ADD, AnyValue(2), AnyValue(2))
    check(test3.first == OperationStatus.VALID && test3.second?.intValue() == 4) { "2 + 2 is 4" }

    val test4 = operationMap(OperationType.CONCATENATE, AnyValue("Merry"), AnyValue(" Christmas"))
    check(test4.first == OperationStatus.VALID && test4.second?.stringValue() == "Merry Christmas") { "Make sure to concat the string values" }

    val test5 = operationMap(OperationType.SUBTRACT, AnyValue(2), AnyValue("merry christmas!"))
    check(test5.first == OperationStatus.INVALID) { "double and string cannot be subtracted" }

    val test6 = operationMap(OperationType.ADD, AnyValue("Happy Holidays"), AnyValue(1))
    check(test6.first == OperationStatus.INVALID) { "Int and String cannot be added together" }
}
