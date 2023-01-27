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
    val operation = task4()
    check(operation != null) { "Please return an operation lambda from task0"}
    val test1 = operation(OperationType.ADD, AnyValue(2), AnyValue(2))
    check(test1.first == OperationStatus.VALID && test1.second?.intValue() == 4) { "2 + 2 is 4" }

    val test2 = operation(OperationType.ADD, AnyValue(2.75), AnyValue(2.75))
    check(test2.first == OperationStatus.VALID && test2.second?.doubleValue() == 5.5) { "2.75 + 2.75 is 5.5" }

    val test3 = operation(OperationType.CONCATENATE, AnyValue("Merry"), AnyValue(" Christmas"))
    check(test3.first == OperationStatus.VALID && test3.second?.stringValue() == "Merry Christmas") { "Make sure to concat the string values" }

    val test4 = operation(OperationType.ADD, AnyValue(1), AnyValue(2.75))
    check(test4.first == OperationStatus.VALID && test4.second?.doubleValue() == 3.75) { "1 + 2.75 is 3.75" }

    val test5 = operation(OperationType.SUBTRACT, AnyValue(2.75), AnyValue(1))
    check(test5.first == OperationStatus.VALID && test5.second?.doubleValue() == 1.75) { "2.75 - 1 is 1.75" }

    val test6 = operation(OperationType.SUBTRACT, AnyValue(2.75), AnyValue("merry christmas!"))
    check(test6.first == OperationStatus.INVALID) { "double and string cannot be subtracted" }

    val test7 = operation(OperationType.ADD, AnyValue("Happy Holidays"), AnyValue(1))
    check(test7.first == OperationStatus.INVALID) { "Int and String cannot be added together" }
}
