import java.util.*

//  Tasks.kt
//  Week 3 Tasks
//
//  You need to write code to complete the functions below to complete each task.
//  You can develop and test each function individually.
//  Initially you may see some warnings about unused variables that you will address
//  when you implement those functions.
//
//  Due to the tests we need to perform, lines in main.kt may generate
//  warning errors. If you get an unexplained warning error from main.kt, please
//  check if there is a comment near that line saying to ignore warning errors.

//  Task 0
//  This week the project will not compile without errors until you complete task 0.
//  Please add classes and structs as defined in the UML document in the assignment
//  web page. After you have correctly completed task 0 the project should successfully
//  compile and acknowledge that task 0 passed.
//
//  Add the classes and structs here:

/* NOTE TO EDITOR: Delete this struct and these classes when creating tasks.swift. */
abstract class MedicationContainer(name: String, date: Date) {
    val id = UUID.randomUUID().toString()
    private val expirationDate = date
    val name = name
    val isExpired: Boolean
        get() = Date() >= expirationDate
}

class LiquidMedicationContainer(name: String, date: Date, volume: Double, concentration: Int, concentrationUnits: String): MedicationContainer(name, date) {

}

class TabletMedicationContainer(name: String, date: Date, pillCount: Int, potency: Double, potencyUnits: String): MedicationContainer(name, date) {

}

class PharmaceuticalStockTracker {
    private var inStockMedications = mutableListOf<MedicationContainer>()

    fun addContainer(container: MedicationContainer): Boolean {
        if (inStockMedications.contains(container)) return false
        inStockMedications.add(container)
        return true
    }

    fun count(name: String): Int {
        return inStockMedications.count { it.name == name }
    }
}
/* NOTE TO EDITOR: This is the end of section to delete for tasks.swift. */

//  You can use this, but there is no need to make edits to it
fun futureDate(daysFromNow: Int): Date {
    var calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysFromNow)
    return calendar.time
}

fun task0(): Pair<MedicationContainer, MedicationContainer> {
    // Print some sample dates
    println("Yesterday: ${futureDate(-1)}")
    println("Tomorrow: ${futureDate(1)}")

    // initialize a test variable of each class
    val aLiquidContainer = LiquidMedicationContainer("med1", futureDate(7), 4.5, 2, "ml")
    val aTabletContainer = TabletMedicationContainer("med2", futureDate(7), 90, 2.3, "mg")
    return Pair(aLiquidContainer, aTabletContainer)
}

//  Task 1
//  Add a computed property to MedicationContainer "isExpired: Bool" that
//  returns true if and only if the container has expired.
//
//  Return a liquid medication that has expired, and a tablet medication that has not expired
fun task1(): Pair<LiquidMedicationContainer, TabletMedicationContainer>? {

    // The following code used to validate the test code will be deleted for tasks.swift.
    val aLiquidContainer = LiquidMedicationContainer("med1", futureDate(-7), 4.5, 2, "ml")
    val aTabletContainer = TabletMedicationContainer("med2", futureDate(7), 90, 2.3, "mg")
    return Pair(aLiquidContainer, aTabletContainer)
}

//  Task 2
//  Add a method to PharmaceuticalStockTracker that looks like this:
//      fun addContainer(container: MedicationContainer): Bool
//  Your method should add the container to the inStockMedications
//  List, but only if that exact container is not already in the array.
//  Return true if we successfully added it to the array. Remember that
//  the MedicationContainer parameter could actually be a
//  LiquidMedicationContainer or a TabletMedicationContainer.
//  Note that having the same name does not mean it is the same MedicationContainer,
//  but if you correctly set up the id field, it will be unique for a MedicationContainer.
//
//  Add another method to PharmaceuticalStockTracker like this:
//      fun count(name: String): Int
//  This will count the number of items in MedicationContainers that have a
//  name that matches the parameter passed in. Note that it will be called
//  like this:
//      val aCount = aTracker.count("Aspirin")

//  Then change task2() to return true rather than null
fun task2(): PharmaceuticalStockTracker? {
    return PharmaceuticalStockTracker()
}

//  Task 3
//  A Range cannot step backwards, but sometimes we want to count down.
//  An Array can act like a sequence and be used many places that a Range
//  can be used. If the int1 <= int2 return the matching Range. Otherwise,
//  return an Array that has values counting from int1 down to and including
//  int 2.
fun task3(int1: Int, int2: Int): Any? {
    //    return null
    // The following code used to validate the test code will be deleted for tasks.swift.
    if (int1 <= int2) { return int1..int2 }
    return (int2 .. int1).toList().reversed()
}

//  Task 4
//  task4() is called with two Ints. Call task3() with the parameters received.
//  Return 1 if the value returned from task3() is a ClosedRange<Int>.
//  Return 2 if the value returned from task3() is an Array<Int>.
//  Otherwise return 0 (this case should not happen if task3() is correct)
fun task4(int1: Int, int2: Int): Int {
    //    return null

    // The following code used to validate the test code will be deleted for tasks.swift.
    val value = task3(int1, int2) ?: return 0
    if (value is IntRange) { return 1 }
    if (value is List<*>) { return 2 }
    return 0
}

//  Task 5
//  This function receives an array that might have any values in it
//      including null.
//  Use mapNotNull to process it as follows:
//      if the value received is an Int, put 1 in the Array
//      if the value received is a Double, put 2 in the Array
//      if the value received is a String, put 3 in the Array
//      otherwise put null in the array and let compactMap remove it
//  Then return that new array.
//  Note: the "is" keyword can be used to check a variable type
//  Note: you might need to use return@mapNotNull for the different cases
fun task5(anyArray: List<Any?>): List<Int> {
    //    return null

    return anyArray.mapNotNull {
        if (it is Int) { return@mapNotNull 1 }
        if (it is Double) { return@mapNotNull 2 }
        if (it is String) { return@mapNotNull 3 }
        null
    }
}

//  Task 6
//  This function receives the same array as Task5. This time use mapNotNull to
//  remove the optionals. Map it to an array of Double values that have the following values:
//      if the value received is an Int, convert it to a Double and put it in the Array.
//      if the value received is a Double, put it in the Array.
//      if the value received is a String, and it can be converted to a Double, put it
//          in the array, if not, put null in the array so compactMap removes this item.
//          Note that if Double() fails to convert a String it conveniently returns null.
//      if the value received is null, return null so compactMap removes it
//  Then return that new array.
//  Note: the "is" keyword can be used to check a variable type
//  Note: you might need to use return@mapNotNull for the different cases
fun task6(anyArray: List<Any?>): List<Double> {
    //    return null

    return anyArray.mapNotNull {
        if (it is Int) { return@mapNotNull it.toDouble() }
        if (it is Double) { return@mapNotNull it }
        if (it is String) { return@mapNotNull it.toDoubleOrNull() }
        null
    }
}

//  Task 7
//  Add a mutating method called setDates to DateSequencer with one parameter called
//  daysTuple that is a tuple of two Ints. Use them to set the values (in order)
//  of the two properties.
//
//  Then apply the additional protocol DateSequencerProtocol that will let the tests
//  conform that your solution has the correct functions implemented
//
//  Then change task3() to return true rather than null
//struct DateSequencer: DateSequencerProtocol  {
//
//    var sequenceCurrent = 0
//    var sequenceEnd = 10
//
//    /* NOTE TO EDITOR: Delete the following method when creating tasks.swift
//     so the struct will just have the two private properties above. */
//    mutating func setDates(daysTuple: (Int, Int)) {
//        (sequenceCurrent, sequenceEnd) = daysTuple
//    }
//}
//func task7() -> Bool? {
////    return null
//
//    // When creating tasks.swift, change this back to "return null"
//    return true
//}

//  Task 8
//  Edit the extension below to make the DateSequencer conform to the Sequence protocol.
//  Use the approach that has it also conforms to the IteratorProtocol.
//  Every time it generates a sequence element it should return a date optional that
//  is sequenceCurrent days in the future and also increment sequenceCurrent towards
//  sequenceEnd. That means if sequenceCurrent < sequenceEnd add one to sequenceCurrent
//  and if sequenceCurrent > sequenceEnd subtract one from sequenceCurrent. If it
//  is asked to generate another sequence element when sequenceCurrent == sequenceEnd
//  return null. Note that the date returned should be based on the value of sequenceCurrent
//  prior to its being incremented. You are welcome to call daysToMs() or futureDate()
//  or borrow code from them.
//
//  Then apply the additional protocol DateSequencerProtocol2 to the extension that
//  will let the tests conform that your solution has the correct functrion implemented
//
//  Then change task4() to return true rather than null
//
/* NOTE TO EDITOR: use the following version of the extension that is empty and
 without the list of protocols when creating tasks.swift
 */
// extension DateSequencer {
// }

//extension DateSequencer: Sequence, IteratorProtocol, DateSequencerProtocol2  {
//    mutating func next() -> Date? {
//        if sequenceCurrent < sequenceEnd {
//            sequenceCurrent += 1
//            return Date(timeIntervalSinceNow: daysToSeconds(sequenceCurrent-1))
//        } else if sequenceCurrent > sequenceEnd {
//            sequenceCurrent -= 1
//            return Date(timeIntervalSinceNow: daysToSeconds(sequenceCurrent+1))
//        } else {
//            return null
//        }
//    }
//}
//func task8() -> Bool? {
////    return null
//
//    // When creating tasks.swift, change this back to "return null"
//    return true
//}

//  Task 9
//  Add an empty variable of Type [Date] called returnValue.
//  Add code to task9() that creates a local object of type DateSequencer. Use the
//  input parameter with .setDates() to set the properties inside that DateSequencer
//  object. Add "for let aDate in yourObject" to create a loop where
//  "yourObject" is whatever you called your DateSequencer object.
//  Inside that loop, append each date returned by the DateSequencer
//  to the returnValue array. Then return the returnValue array instead of null.
//func task9(_ aTuple: (Int, Int)) -> [Date]? {
//    //    return null
//
//    // The following code used to validate the test code will be deleted for tasks.swift.
//    var returnValue: [Date] = []
//    var seqObj = DateSequencer()
//    seqObj.setDates(daysTuple: aTuple)
//    for aDate in seqObj { returnValue.append(aDate) }
//    return returnValue
//}
