import java.util.*

//  Tasks.kt
//  Week 3 Tasks
//
//  You need to write code to complete the functions and classes below to complete each task.
//
//  Recommendation: Fix the compile errors first, and then work on getting the tests to pass

//  Task 0
//  This week the project will not compile without errors until you complete task 0.
//  Please add classes and structs as defined in the UML document in the assignment
//  web page.
//
//  Add the classes and structs here:

//  You can use this, but there is no need to make edits to it
fun futureDate(daysFromNow: Int): Date {
    var calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysFromNow)
    return calendar.time
}

// You do not need to modify the task0 function.
fun task0(): Pair<MedicationContainer, MedicationContainer> {
    // Print some sample dates
    println("Yesterday: ${futureDate(-1)}")
    println("Tomorrow: ${futureDate(1)}")

    // initialize a test variable of each class
    val aLiquidContainer = LiquidMedicationContainer("med1", futureDate(500), 4.5, 2, "ml")
    val aTabletContainer = TabletMedicationContainer("med2", futureDate(500), 90, 2.3, "mg")
    return Pair(aLiquidContainer, aTabletContainer)
}

//  Task 1
//  Add a computed property to MedicationContainer "isExpired: Bool" that
//  returns true if and only if the container has expired.
//
//  Return a liquid medication that has expired, and a tablet medication that has not expired
fun task1(): Pair<LiquidMedicationContainer, TabletMedicationContainer>? {
    return null
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

//  Return a new PharmaceuticalStockTracker object
fun task2(): PharmaceuticalStockTracker? {
    return null
}

//  Task 3
//  A Range cannot step backwards, but sometimes we want to count down.
//  A List can act like a sequence and be used many places that a Range
//  can be used. If the int1 <= int2 return the matching Range. Otherwise,
//  return a List that has values counting from int1 down to and including
//  int 2.
fun task3(int1: Int, int2: Int): Any? {
    return null
}

//  Task 4
//  task4() is called with two Ints. Call task3() with the parameters received.
//  Return 1 if the value returned from task3() is a ClosedRange<Int>.
//  Return 2 if the value returned from task3() is an Array<Int>.
//  Otherwise return 0 (this case should not happen if task3() is correct)
fun task4(int1: Int, int2: Int): Int {
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
//  https://kotlinlang.org/docs/returns.html#break-and-continue-labels
fun task5(anyArray: List<Any?>): List<Int> {
    return listOf()
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
    return listOf()
}

//  Task 7
//  When we print a MedicationContainer, the description is not very informative.
//  It makes it harder to print things and track down errors.
//
//  Assignment: Override the build in toString() on MedicationContainer.
//  It should include the name of the subclass as well as the id of the medication
//  You can use the "is" keyword to check the type of object
//
//  If the medication is a LiquidMedicationContainer, and it has an id of 5,
//  toString() should return:
//      Liquid: 5
//
//  If the medication is a TabletMedicationContainer, and it has an id of 5,
//  toString() should return:
//      Tablet: 5
//
fun task7(): Pair<LiquidMedicationContainer, TabletMedicationContainer>? {
    return null
}

//  Task 8
//  You can override the equals operator in Kotlin. This allows
//  you, the author of the code, to decide what it means for two objects to be
//  equal to each other.
//
//  Add an override of the equals method in MedicationContainer to compare the id
//  of the medication. If both have the same id, return true. Otherwise, return false.
//
//  https://kotlinlang.org/docs/operator-overloading.html
//
//  You don't need to modify the code in task8()
fun task8(): Pair<Pair<LiquidMedicationContainer, LiquidMedicationContainer>, Pair<TabletMedicationContainer, TabletMedicationContainer>> {
    val liquidOne = LiquidMedicationContainer("liquid medication 3", futureDate( 7), 4.5, 2, "ml")
    val liquidTwo = LiquidMedicationContainer("liquid medication 3", futureDate( 7), 4.5, 2, "ml")
    // Two objects with the same id should be equal to each other
    liquidTwo.id = liquidOne.id

    val tabletOne = TabletMedicationContainer("tablet medication 8", futureDate(7), 90, 2.3, "mg")
    val tabletTwo = TabletMedicationContainer("tablet medication 9", futureDate(7), 90, 2.3, "mg")


    return Pair(Pair(liquidOne, liquidTwo), Pair(tabletOne, tabletTwo))
}

// Task 9
// You can extend a class without inheritance by using an extension.
// For this task, you should add an extension method to the
// PharmaceuticalStockTracker class that remove all medications from the stock tracker
// It will look something like this:
//  fun PharmaceuticalStockTracker.removeAllContainers
fun task9(): PharmaceuticalStockTracker {
    return PharmaceuticalStockTracker()
}