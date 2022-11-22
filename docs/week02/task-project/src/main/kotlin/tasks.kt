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
fun task2(): Int? {
    return null
}