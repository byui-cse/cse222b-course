// *************************
//  Do not modify this file!
// *************************
//  You are welcome to read this file and to try to understand it, but it may include
//  elements of Swift that have not yet been taught in the course at this point.
//  Your assignment is to edit task.swift and then run this file and task.swift in a project.
//  The code in this file will test your task functions and indicate which task functions pass.
//  You should definitely not try to just copy code from a test here to your task,
//  but instead, understand how to correctly create a task that meets the needs of the test.
//
//  main.swift
//  Week 2 Tasks
//
//  Created by Layne Moseley on 11/21/22.
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
}

fun testTask0() {
    val test0 = task0(true, 0, 1, "four")
    check(test0 == TestResult.SUCCESS) { "All conditions should pass" }

    val test1 = task0(false, 0, 1, "four")
    check(test1 == TestResult.INVALID_INPUT) { "aBool must be true" }

    val test2 = task0(true, -1, 1, "four")
    check(test2 == TestResult.INVALID_INPUT) { "int1 must be 0 or greater" }

    val test3 = task0(true, 2, 1, "four")
    check(test3 == TestResult.INVALID_INPUT) { "int2 must be greater than int1" }

    val test4 = task0(true, 0, 1, "one")
    check(test4 == TestResult.INVALID_INPUT) { "aString must have exactly 4 characters" }
}

fun testTask1() {
    val test5 = task1(null)
    check(test5 == 0) { "aString is null, the result should be zero"}

    val test6 = task1("this is fine")
    check(test6 == 12) { "aString should have a length of 12"}

    val test7 = task1("")
    check(test7 == 0) { "aString is empty, so it should also return 0"}
}

fun testTask2() {}
fun testTask3() {}
fun testTask4() {}
fun testTask5() {}
fun testTask6() {}
fun testTask7() {}
fun testTask8() {}
fun testTask9() {}
