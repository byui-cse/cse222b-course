////
////  tasks.kt
////  Week 5 Tasks
////
////  You need to write code to complete the functions below to complete each task.
////
////  task4() is meant to be open-ended. There are probably many ways to solve it. Use creativity
////  to come to a good solution
////
//
//enum class IntOperationType {
//    ADD, SUBTRACT, MULTIPLY, DIVIDE
//}
//
//typealias IntOperation = (Int, Int) -> Int
//
//typealias IntOperationMap = Map<IntOperationType, IntOperation>
//
////  Task 0
////
////  Lambda functions can be stored in a Map, just like any other type of variable
////
////  In this task, create a Map that contains operations for all types in IntOperationType
////  The key of the map will be an IntOperationType, and the value will be an IntOperation
//fun task0(): IntOperationMap {
//    return mapOf(IntOperationType.ADD to { a, b -> a + b },
//        IntOperationType.SUBTRACT to { a, b -> a - b },
//        IntOperationType.MULTIPLY to { a, b -> a * b },
//        IntOperationType.DIVIDE to { a, b -> a / b })
//}
//
//typealias IntResult = () -> Int
//
////  Task 1
////
////  Higher-order functions are functions that... wait for it... return another function
////
////  In this task, return a function that, when called, will add the two numbers passed _into_ the higher order function
//fun task1(a: Int, b: Int): IntResult? {
//    return { a + b }
//}
//
////  Task 2
////
////  Create a higher order function that simple executes the parameter that also is a function.
////  This gets _really_ confusing, so here is the best description I can come up with:
////      - task2 is a function, which has a single parameter: a function that returns an Int
////      - task2 returns a function, that when called, will return an Int
////      - So the return value of task2 is a function that should return the result of the parameter function
////
////  Is your head spinning yet?
//fun task2(execute: () -> Int): IntResult? {
//    return { execute() }
//}
//
////  Task 3
////
////  Like task2, you'll call the passed in function parameters and do an operation on them
////  The operation you do needs to be based on the "op" parameter
//fun task3(op: IntOperationType, first: () -> Int, second: () -> Int): IntResult? {
//    if (op == IntOperationType.ADD) {
//        return { first() + second() }
//    }
//
//    if (op == IntOperationType.SUBTRACT) {
//        return { first() - second() }
//    }
//
//    if (op == IntOperationType.MULTIPLY) {
//        return { first() * second() }
//    }
//
//    return { first() / second() }
//}
//

// AnyValue can represent any type of value, and provides simple mechanisms check the type of value,
// as well as perform different operations on the value
//
// To keep things simple, our AnyValue type only supports String and Int types
//class AnyValue {
//
//    private val value: Any
//
//    constructor(s: String) { value = s }
//    constructor(i: Int) { value = i }
//
//    fun stringValue(): String? { return value as? String  }
//    fun intValue(): Int? { return value as? Int }
//
//    val isString: Boolean
//        get() = stringValue() != null
//
//    val isInt: Boolean
//        get() = intValue() != null
//
//    fun canPerformOperation(other: AnyValue, type: OperationType): Boolean {
//
//        // If the operation is ADD or SUBTRACT, only number types can be added together
//        if (type == OperationType.ADD || type == OperationType.SUBTRACT) {
//            return isInt && other.isInt
//        }
//
//        if (type == OperationType.CONCATENATE) {
//            return isString && other.isString
//        }
//
//        return false
//    }
//
//    fun add(other: AnyValue): Pair<OperationStatus, AnyValue?> {
//        if (!canPerformOperation(other, OperationType.ADD)) {
//            return Pair(OperationStatus.INVALID, null)
//        }
//
//        val result = (intValue() ?: 0) + (other.intValue() ?: 0);
//        return Pair(OperationStatus.VALID, AnyValue(result))
//    }
//
//    fun subtract(other: AnyValue): Pair<OperationStatus, AnyValue?> {
//        if (!canPerformOperation(other, OperationType.SUBTRACT)) {
//            return Pair(OperationStatus.INVALID, null)
//        }
//
//        val result = (intValue() ?: 0) - (other.intValue() ?: 0);
//        return Pair(OperationStatus.VALID, AnyValue(result))
//    }
//
//    fun concat(other: AnyValue): Pair<OperationStatus, AnyValue?> {
//        if (!canPerformOperation(other, OperationType.CONCATENATE)) {
//            return Pair(OperationStatus.INVALID, null)
//        }
//
//        val result = stringValue() + other.stringValue()
//        return Pair(OperationStatus.VALID, AnyValue(result))
//    }
//}
//
//enum class OperationType {
//    ADD, SUBTRACT, CONCATENATE
//}
//
//enum class OperationStatus {
//    VALID, INVALID
//}
//
////  Task 4
////
////  Lambda functions in Kotlin are first-class citizens, meaning they can be assigned
////  to variables and passed as a parameter to a function.
////
////  For task4, you should return an OperationHandler with the following rules:
////      - Int types can be Added and Subtracted
////      - String types can be concatenated
////      - Strings _cannot_ be added and subtracted
////      - Int types _cannot_ be concatenated
////
////  If the values can have an operation applied to them, return a OperationStatus.VALID, as well as the result
////  If the values cannot have the operation applied, return OperationStatus.INVALID in the Pair, along with null
////
//
//typealias OperationHandler = (OperationType, AnyValue, AnyValue) -> Pair<OperationStatus, AnyValue?>
//fun task4(): OperationHandler? {
//
//    return { op: OperationType, a: AnyValue, b: AnyValue ->
//
//        when(op) {
//            OperationType.ADD -> a.add(b)
//            OperationType.SUBTRACT -> a.subtract(b)
//            OperationType.CONCATENATE -> a.concat(b)
//        }
//    }
//}
