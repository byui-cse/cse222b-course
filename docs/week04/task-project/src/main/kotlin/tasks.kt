//
//  tasks.kt
//  Week 4 Tasks
//
//  You need to write code to complete the functions below to complete each task.
//  You can develop and test each function individually, but some sequentially
//  depend on the prior tasks.

//  Due to the tests we need to perform, some lines in main.swift may generate
//  warning errors. If you get an unexplained warning error from main.swift, please
//  check if there is a comment near that line saying to ignore warning errors.
//

//  !!!!!!!!
//  !!!!!!!!
//
//  NOTE: You will need to complete parts of task8 in order for the program to compile.
//
//  !!!!!!!!
//  !!!!!!!!

import java.util.*
import kotlin.math.pow

//  Task 0 Assignment
//  This week the project will not compile without errors until you complete task 0.
//  We have included the classes from last week as we left them except for a few changes,
//  mostly to match the new UML diagram:
//
//      1) PharmaceuticalStockTracker is now a class
//
//      1) MedicationContainer has a new property ndcPackageCode that identifies
//      the specific type of medication container (more about that later in task 2).
//
//      2) Changed "inStockMedications" to a Map. The key is a String and
//      the value is a MutableSet of MedicationContainers
//      each of which have an ndcPackageCode attribute that matches the key.
//
//      3) Added a new computed property called "count"
//
//      4) Changed the parameter to count(of:) from "name" to "ndcPackageCode".
//
//  Your Task 0 assignment is to get the file to compile by doing the following:
//
//      1) Implement the computed property "count" that should return a
//      total count of all MedicationContainers with any ndcPackageCode
//      stored in inStockMedications.
//
//      2) Change count(of:) to correctly count how many containers of a
//      specific ndcPackageCode are stored in inStockMedications.
//
//      3) Change addContainer() to work correctly for the new data model
//      using the ndcPackageCode inside the MedicationContainer parameter
//      as the key. Be sure to address both the case where we are adding
//      this ndcPackageCode for the first time and the case where one or
//      more MedicationContainers with the same ndcPackageCode have
//      already been added.
//

class PharmaceuticalStockTracker {

    var inStockMedications = mutableMapOf<String, MutableSet<MedicationContainer>>()
    val count: Int
        get() {
            return 0
        }

    fun count(ndcPackageCode: String): Int {
        return 0
    }

    fun addContainer(container: MedicationContainer): Boolean {
        return false
    }
}

 abstract class MedicationContainer(ndcPackageCode: String, name: String, date: Date) {
     var id = UUID.randomUUID().toString()

     val ndcPackageCode = ndcPackageCode
     val expirationDate = date
     var name = name
     val isExpired: Boolean
        get() = Date() >= expirationDate

     override fun toString(): String {
         if (this is LiquidMedicationContainer) {
             return "Liquid: ${this.id}"
         }

         if (this is TabletMedicationContainer) {
             return "Tablet: ${this.id}"
         }

         return "Generic: ${this.id}"
    }

     override fun equals(other: Any?): Boolean {
         if (other is MedicationContainer) {
             return other.id == this.id
         }

         return false
     }
}
 class LiquidMedicationContainer(ndcPackageCode: String, name: String, date: Date, volume: Double, concentration: Int, concentrationUnits: String): MedicationContainer(ndcPackageCode, name, date) {

 }

 class TabletMedicationContainer(ndcPackageCode: String, name: String, date: Date, pillCount: Int, potency: Double, potencyUnits: String): MedicationContainer(ndcPackageCode, name, date) {

 }

//  You can use this, but there is no need to make edits to it
fun futureDate(daysFromNow: Int): Date {
    var calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysFromNow)
    return calendar.time
}

fun task0(): Pair<MedicationContainer, MedicationContainer> {
    val aCode = "12345-123-12"
    val aLiquidContainer = LiquidMedicationContainer(aCode, "med1", futureDate(120),
     4.5,  2,  "ml")
    val aTabletContainer = TabletMedicationContainer( aCode,  "med2",  futureDate( 90),
     90,  2.3,  "mg")
    return Pair(aLiquidContainer, aTabletContainer)
}

//  Task 1
//  Create a function extension on PharmaceuticalStockTracker that
//  will remove all expired medications
//
fun PharmaceuticalStockTracker.removeExpired() {}

fun task1(): PharmaceuticalStockTracker {
    return PharmaceuticalStockTracker()
}

//  Task 2
//  The ndcPackageCode that tells what medication is in a container
//  follows the code pattern in the NDC database:
//      https://www.accessdata.fda.gov/scripts/cder/ndc/dsp_searchresult.cfm
//  Using regular expression pattern matching, add code to the isFormattedAsNDCCode()
//  function to return true if and only if the code is in a valid pattern for those
//  codes: [5 digits] - [4 digits] - [2 digits]. When testing your code you may use
//  real codes from the database along with real information, or you may make them up.
//  The only thing that matters for this exercise is matching the pattern, not
//  whether it is an actual code from the website. Your code should test for a
//  match on the entire property and not report a match if only a substring of
//  the property matches.
//
//  You should not need to modify task2(), only the isFormattedAsNDCCode() function

fun isFormattedAsNDCCode(code: String): Boolean {
    // Replace the following line with your code
    return false
}

fun task2(code: String): Boolean {
    return isFormattedAsNDCCode(code)
}

//  Task 3
//  Edit addContainer() to call isFormattedAsNDCCode() to validate
//  that the ndcPackageCode in the container to be added has a valid
//  NDCCode format. If not, do not add the container and return false.
//
//  Now fill out the method addContainers() below. This accepts as parameters
//  an expectedNdcPackageCode and a Set of MedicationContainers to be added to
//  the Stock Tracker. Since this system is supposed to be dealing with medications
//  there are extra layers of checking. All the containers in the Set passed
//  to addContainers() should have the same ndcPackageCode and that code should
//  match the expectedNdcPackageCode parameter.
//
//  The function returns the enum AddMessage
//  As you complete the method, parts of your code should return each of the
//  values in AddMessage except possibly .UNKNOWN_FAILURE. The others all
//  represent error (or success) conditions that you should detect as you
//  implement addContainers(). One  error you should detect would be found
//  by calling the function isFormattedAsNDCCode() to verify the format
//  of the NdcPackageCode used.
//
//  Hint: Remember to deal with both the case where there are currently no
//  containers matching the ndcPackageCode and the case where there are
//  already some containers matching the ndcPackageCode.

enum class AddMessage {
    SUCCESS, NDC_CODE_FORMAT_ERROR, EMPTY_CONTAINER_SET, MIXED_NDC_CODES, UNKNOWN_FAILURE
}

fun PharmaceuticalStockTracker.addContainers(expectedNdcPackageCode: String, containersToAdd: Set<MedicationContainer>): AddMessage {
    return AddMessage.SUCCESS
}

fun task3(): PharmaceuticalStockTracker {
    return PharmaceuticalStockTracker()
}

//  Task 4
//  Implement the method currentStock(of:) below. This accepts as its parameter an
//  ndcPackageCode. It returns a tuple with an enum of Type StockMessage
//  and a set of MedicationContainers. Validate the ndcPackageCode format,
//  check if there are any containers of that type and if there are, return
//  them in a List

enum class StockMessage {
    SUCCESS, NDC_CODE_FORMAT_ERROR, NO_INVENTORY, UNKNOWN_FAILURE
}

fun PharmaceuticalStockTracker.currentStock(of: String): Pair<StockMessage, List<MedicationContainer>> {
    return Pair(StockMessage.SUCCESS, listOf())
}

fun task4(): PharmaceuticalStockTracker{
    return PharmaceuticalStockTracker()
}

//  Task 5
//  Fill out the method sellContainers(count:of) below. This accepts as parameters a
//  count of containers to sell, and an ndcPackageCode.
//  It returns a SellMessage and a list of MedicationContainers.
//  Like usual, this should validate the format of the ndcPackageCode, then find out
//  if there is any inventory of the ndcPackageCode. If so, confirm that there is
//  enough. If not, return a like this: Pair(SellMessage.NOT_ENOUGH_INVENTORY, listOf()). If there
//  is enough of inventory of the requested ndcPackageCode, be sure to sell those
//  with the earliest dates first. Return a sorted array of the containers sold.
//  If the sale results in the Set being emptied out, remove that dictionary entry.

enum class SellMessage {
    SUCCESS, INVALID_COUNT, NDC_CODE_FORMAT_ERROR, NO_INVENTORY, NOT_ENOUGH_INVENTORY
}

fun PharmaceuticalStockTracker.sellContainers(count: Int, ndcPackageCode: String): Pair<SellMessage, List<MedicationContainer>> {
    return Pair(SellMessage.SUCCESS, listOf())
}

fun task5(): PharmaceuticalStockTracker {
    return PharmaceuticalStockTracker()
}

//  Task 6
//  Given a list of numbers, return the number of occurrences of each number
//
//  For example, if the list is [1, 2, 2, 2, 2, 2, 3]
//  You would return a Map that looks like this:
//      { 1: 1, 2: 5, 3: 1 }

fun task6(numbers: List<Int>): Map<Int, Int> {
    return mapOf()
}

//  Task 7
//
//  When objects are put into a set, uniqueness is evaluated based on the "hashCode" and "equals" methods
//  The following PersonRecord object has an idNumber as its only attribute
//  Override the necessary methods to ensure that uniqueness of the PersonRecord is based on the idNumber, not the object itself
class PersonRecord(var idNumber: String) {}

// Task 7.1
// Return a list of 5 PersonRecord objects, each with a unique idNumber
fun task7(): Set<PersonRecord> {
    return setOf()
}

//  Task 8
//  An interface in Kotlin allows a programmer to define a set of behaviors
//  that classes can adopt.
//
//  For example, let's say you wanted to have different types of objects to
//  all have a property called "area". You would create an interface like this:
interface Shape {
    fun area(): Double
}

// After you have defined the interface, other classes can adopt the interface
// like this:
class Square: Shape {
    private val size: Double = 10.0

    override fun area(): Double {
        return size * size
    }
}

// When a class adopts a protocol, you can then treat different objects as the same
// interface type, such as this function:
fun getArea(s: Shape): Double {
    return s.area()
}

// For Task 8, make the following 3 classes adopt the "Shape" protocol
//      - Circle
//      - Triangle
//      - Rectangle
class Circle(private val radius: Double) {}
class Triangle(private val base: Double, private val height: Double) {}
class Rectangle(private val width: Double, private val height: Double) {}

fun task8(s: Shape): Double {
    return getArea(s)
}

//  Task 9
//
//  Eliminate Duplicates
//  Given an array of numbers, eliminate all duplicates in the most efficient way possible

fun task9(numbers: List<Int>): List<Int> {
    return numbers
}