import kotlin.random.Random

enum class TestResult {
    SUCCESS,
    INVALID_INPUT
}

//  Task 0
//  Add code to this function using multiple statements to validate that
//  the parameters comply with the following rules:
//      aBool must be true
//      int1 must be greater than or equal to 0
//      int2 must be greater than int1
//      aString must be exactly 4 characters long
fun task0(aBool: Boolean, int1: Int, int2: Int, aString: String): TestResult {
    if (!aBool) {
        return TestResult.INVALID_INPUT
    }

    if (int1 < 0) {
        return TestResult.INVALID_INPUT
    }

    if (int1 > int2) {
        return TestResult.INVALID_INPUT
    }

    if (aString.length != 4) {
        return TestResult.INVALID_INPUT
    }

    return TestResult.SUCCESS
}

// Task 1
// Nullable types in Kotlin are a great way to ensure a value exists before you use it
// This function will return the length of an optional string
// If the string is null, return 0
// If the string is not null, return the length of the string
fun task1(aString: String?): Int {
    return aString?.length ?: 0
}

// Task 2
// Convert the input string into an integer. If the string cannot be converted into an integer, return  null
fun task2(aString: String): Int? {
    return aString.toIntOrNull()
}


//  Task 3
//  Built-in functions like map, filter, reduce and sort take a parameter that is a closure.
//
//  Inside this function is an array of closures that each take two Int parameters. Complete each
//  closure according to the requirements below.
//
//  Closure 0: return the sum of the two parameters
//  Closure 1: return the product of the two parameters
//  Closure 2: return -1 if the first parameter is less than the second parameter,
//              return 0 if they are equal
//              return 1 if the first parameter is greater than the second parameter
//  Closure 3: return -1 if both parameters are odd
//              return 1 if both parameters are even
//              return 0 if one parameter is odd and the other is even
typealias task3Func = (Int, Int) -> Int
fun task3(): List<task3Func> {
    return listOf(fun (lhs: Int, rhs: Int): Int {
        return lhs + rhs
    },fun (lhs: Int, rhs: Int): Int {
        return lhs * rhs
    },fun (lhs: Int, rhs: Int): Int {
        if (lhs < rhs) return -1
        if (lhs > rhs) return 1
        return 0
    },fun (lhs: Int, rhs: Int): Int {
        if (lhs % 2 == 0 && rhs % 2 == 0) return 1
        if (lhs % 2 != 0 && rhs % 2 != 0) return -1
        return 0
    })
}

// Task 4
// Using the built-in map function, convert each integer into a string
fun task4(input: List<Int>): List<String> {
    return input.map { it.toString() }
}

// Task 5
// Using the built-in filter method, remove all odd values from the array
fun task5(input: List<Int>): List<Int> {
    return input.filter { it % 2 == 0 }
}

// Task 6
// Using the built-in reduce method, return the sum of the input array
fun task6(input: List<Int>): Int {
    return input.reduce { acc, value -> acc + value }
}

// Task 7
// Given the 2-dimensional input array, return the count of null values
//
// Note: Both the sublists or the integers can be null
fun task7(input: List<List<Int?>?>): Int {
    var count = 0

    for (list in input) {
        if (list == null) {
            count++
        } else {
            for (value in list) {
                if (value == null) {
                    count++
                }
            }
        }
    }

    return count
}

// Task 9
// Return a 2-dimensional array with the appropriate rows and columns
// The max parameter specifies the  maximum value for the random numbers
//
// Hint: https://kotlinlang.org/docs/ranges.html
fun task8(rows: Int, columns: Int, max: Int): List<List<Int>> {
    var list = mutableListOf<List<Int>>()
    var rnd = Random.Default
    for (row in 0 until rows) {
        var sublist = mutableListOf<Int>()
        for (col in 0 until columns) {
            sublist.add(rnd.nextInt(max))
        }
        list.add((sublist))
    }

    return list
}

// Task 9
// Using the built-in map function, transform each row of data into the average of the row
// The input 2-dimensional array is like a spreadsheet, with rows and columns
// We need the average of each row
//
// Hint: List has a function called .average()
fun task9(data: List<List<Int>>): List<Double> {
    return data.map { it.average() }
}