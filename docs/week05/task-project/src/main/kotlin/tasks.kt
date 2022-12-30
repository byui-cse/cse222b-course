//
//  tasks.kt
//  Week 5 Tasks
//
//  You need to write code to complete the functions below to complete each task.
//  You can develop and test each function individually, but some sequentially
//  depend on the prior tasks.
//  As long as a function returns nil, it is assumed to not be implemented.
//
//
//  Due to the tests we need to perform, some lines in main.swift may generate
//  warning errors. If you get an unexplained warning error from main.swift, please
//  check if there is a comment near that line saying to ignore warning errors.
//

import java.util.*

class AnyValue {

    private val value: Any

    constructor(s: String) { value = s }
    constructor(i: Int) { value = i }
    constructor(d: Double) { value = d }

    fun stringValue(): String? {
        if (value is String) {
            return value
        }

        return null
    }

    fun intValue(): Int? {
        if (value is Int) {
            return value
        }

        return null
    }

    fun doubleValue(): Double? {
        if (value is Double) {
            return value
        }

        return null
    }
}

//  Task 0
//
//  Lambda functions in Kotlin are first-class citizens, meaning they can be assigned
//  to variables and passed as a parameter to a function.
//
//

enum class OperationStatus {
    VALID, INVALID
}

typealias Operation = (AnyValue, AnyValue) -> Pair<OperationStatus, AnyValue>
fun task0(): Operation {
    return { a: AnyValue, b: AnyValue ->
        Pair(OperationStatus.VALID, AnyValue(10))
    }
}
