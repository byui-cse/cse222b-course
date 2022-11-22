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
    val test0 = task1(null)
    check(test0 == 0) { "aString is null, the result should be zero"}

    val test1 = task1("this is fine")
    check(test1 == 12) { "aString should have a length of 12"}

    val test2 = task1("")
    check(test2 == 0) { "aString is empty, so it should also return 0"}
}

fun testTask2() {
    val test0 = task2("4")
    check(test0 == 4) { "the string '4' is easily converted into a string" }

    val test1 = task2("not a number")
    check(test1 == null) { "cannot be converted to a strign" }
}

fun testTask3() {

    val functions = task3()
    val test1 = functions[0](1, 1)
    check(test1 == 2) { "1 plus 1 equals 2" }

    val test2 = functions[1](2, 2)
    check(test2 == 4) { "2 times 2 equals 4" }

    val test3 = functions[2](1, 2)
    check(test3 == -1) { "one is less than 2" }

    val test4 = functions[2](2, 1)
    check(test4 == 1) { "one is less than 2" }

    val test5 = functions[2](1, 1)
    check(test5 == 0) { "one and one are equal" }

    val test6 = functions[3](1, 3)
    check(test6 == -1) { "one and three are both odd" }

    val test7 = functions[3](2, 4)
    check(test7 == 1) { "two and four are both even" }

    val test8 = functions[3](1, 2)
    check(test8 == 0) { "one is odd and two is even" }
}

fun testTask4() {
    val test0 = task4(listOf(1, 2, 3, 4))
    check(test0[3] == "4") { "please convert each value into a string"}
}

fun testTask5() {
    val test0 = task5(listOf(1, 2, 3, 4))
    check(test0[0] == 2) { "please remove all odd values" }
}

fun testTask6() {
    val test0 = task6(listOf(1, 2, 3, 4, 5))
    check(test0 == 15) { "please return the sum of the input array" }
}

fun testTask7() {}
fun testTask8() {}
fun testTask9() {}
