//
//  tasks.kt
//  Week 5 Tasks
//
//  You need to write code to complete the functions below to complete each task.
//
//  task4() is meant to be open-ended. There are probably many ways to solve it. Use creativity
//  to come to a good solution
//

enum class IntOperationType {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
}

typealias IntOperation = (Int, Int) -> Int

typealias IntOperationMap = Map<IntOperationType, IntOperation>

//  Task 0
//
//  Lambda functions can be stored in a Map, just like any other type of variable
//
//  In this task, create a Map that contains operations for all types in IntOperationType
//  The key of the map will be an IntOperationType, and the value will be an IntOperation
fun task0(): IntOperationMap {
    return mapOf()
}

typealias IntResult = () -> Int

//  Task 1
//
//  Higher-order functions are functions that... wait for it... return another function
//
//  In this task, return a function that, when called, will add the two numbers passed _into_ the higher order function
fun task1(a: Int, b: Int): IntResult? {
    return null
}

//  Task 2
//
//  Create a higher order function that simple executes the parameter that also is a function.
//  This gets _really_ confusing, so here is the best description I can come up with:
//      - task2 is a function, which has a single parameter: a function that returns an Int
//      - task2 returns a function, that when called, will return an Int
//      - So the return value of task2 is a function that should return the result of the parameter function
//
//  Is your head spinning yet?
fun task2(execute: () -> Int): IntResult? {
    return null
}

//  Task 3
//
//  Like task2, you'll call the passed in function parameters and do an operation on them
//  The operation you do needs to be based on the "op" parameter
fun task3(op: IntOperationType, first: () -> Int, second: () -> Int): IntResult? {
    return null
}

class AnyValue {

    private val value: Any

    constructor(s: String) { value = s }
    constructor(i: Int) { value = i }
    constructor(d: Double) { value = d }
}

enum class OperationType {
    ADD, SUBTRACT, CONCATENATE
}

enum class OperationStatus {
    VALID, INVALID
}

//  Task 4
//
//  Lambda functions in Kotlin are first-class citizens, meaning they can be assigned
//  to variables and passed as a parameter to a function.
//
//  For task0, you should return an OperationHandler with the following rules:
//      - Int and Number types can be Added and Subtracted
//      - String types can be concatenated
//      - Strings _cannot_ be added and subtracted
//      - Int and Number types _cannot_ be concatenated
//      - If an Int and Double have an operation applied, the result should _be_ a Double
//
//  If the values can have an operation applied to them, return a OperationStatus.VALID, as well as the result
//  If the values cannot have the operation applied, return OperationStatus.INVALID in the Pair, along with null
//
//  To make things easier to code, you should add several methods and computed properties to AnyValue,
//  such as the following for string types:
//      - fun stringValue(): String?
//      - val isString: Boolean
//
//  To get the tasks to compile, you must add the following methods:
//      - fun stringValue(): String?
//      - fun doubleValue(): Double?
//      - fun intValue(): Int?
//
//  NOTE: These functions do not _convert_ the value. They simply return the value if it is the type
//        or return null if it is not that type

typealias OperationHandler = (OperationType, AnyValue, AnyValue) -> Pair<OperationStatus, AnyValue?>
fun task4(): OperationHandler? {
    return null
}