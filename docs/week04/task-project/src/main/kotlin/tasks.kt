//
//  tasks.kt
//  Week 4 Tasks
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
import kotlin.math.pow

//  Task 0 Assignment
//  This week the project will not compile without errors until you complete task 0.
//  We have included the classes from last week as we left them except for a few changes,
//  mostly to match the new UML diagram:
//
//      1) PharmaceuticalStockTracker is now a class
//
//      2) MedicationContainer has a new property ndcPackageCode that identifies
//      the specific type of medication container (more about that later in task 2).
//
//      3) Changed "inStockMedications" from an Array of MedicationContainers to
//      a Dictionary. The key is a String (actually an ndcPackageCode) and
//      the value is a Set of MedicationContainers each of which have an
//      ndcPackageCode attribute that matches the key.
//
//      4) Added two new computed properties "description" and "count" and
//      made PharmaceuticalStockTracker conform to the CustomStringConvertible
//      to activate the description property when needed.
//
//      5) Changed the parameter to count(of:) from "name" to "ndcPackageCode".
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
//      4) As explained in the reading, Elements of a set must conform to
//      the Hashable protocol. Mark the MedicationContainer class as
//      conforming to Hashable. Then make it actually conform to Hashable.
//      Note that since Equatable is a parent of Hashable, we must also
//      conform to Equatable. Fortunately, we already did that last week,
//      so part of the work to conform is already done.
//      Hint: The combine() approach to conforming to Hashable works even
//      if you are only "combining" one property,
//
//  After you correctly complete this task, the project should compile and
//  task0() should be shown as passing.

class PharmaceuticalStockTracker {

    var inStockMedications = mutableMapOf<String, MutableSet<MedicationContainer>>()
    val count: Int
        get() {
            val sets = inStockMedications.values
            var count = 0
            sets.forEach { count += it.count() }
            return count
        }

    fun count(ndcPackageCode: String): Int {
        val meds = inStockMedications[ndcPackageCode]
        if (meds != null) {
            return meds.count()
        }
        return 0
    }

    fun addContainer(container: MedicationContainer): Boolean {
        if (!isFormattedAsNDCCode(container.ndcPackageCode)) {
            return false
        }
        var current = inStockMedications[container.ndcPackageCode] ?: mutableSetOf()
        current.add(container)
        inStockMedications[container.ndcPackageCode] = current
        return true
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
    // Print some sample dates

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
fun PharmaceuticalStockTracker.removeExpired() {
    val keys = inStockMedications.keys
    keys.forEach {
        val meds = inStockMedications[it]
        if (meds != null) {
            inStockMedications[it] = meds.filter { v -> !v.isExpired }.toMutableSet()
        }
    }
}

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
    return Regex("\\d\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d").matches(code)
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
    if (containersToAdd.isEmpty()) {
        return AddMessage.EMPTY_CONTAINER_SET
    }

    val matches = containersToAdd.filter { it.ndcPackageCode == expectedNdcPackageCode }
    if (matches.count() != containersToAdd.count()) {
        return AddMessage.MIXED_NDC_CODES
    }

    val valid = containersToAdd.filter { isFormattedAsNDCCode(it.ndcPackageCode) }
    if (valid.count() != containersToAdd.count()) {
        return AddMessage.NDC_CODE_FORMAT_ERROR
    }

    if (valid.isEmpty()) {
        return AddMessage.EMPTY_CONTAINER_SET
    }

    valid.forEach { this.addContainer(it) }

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
//
//  When you have completed and tested the code for currentStock(of:),
//  change task4() to return true rather than nil

enum class StockMessage {
    SUCCESS, NDC_CODE_FORMAT_ERROR, NO_INVENTORY, UNKNOWN_FAILURE
}

fun PharmaceuticalStockTracker.currentStock(of: String): Pair<StockMessage, List<MedicationContainer>> {
    if (!isFormattedAsNDCCode(of)) {
        return Pair(StockMessage.NDC_CODE_FORMAT_ERROR, listOf())
    }

    val stock = inStockMedications[of] ?: return Pair(StockMessage.NO_INVENTORY, listOf())
    if (stock.isEmpty()) {
        return Pair(StockMessage.NO_INVENTORY, listOf())
    }

    return Pair(StockMessage.SUCCESS, stock.toList())
}

fun task4(): PharmaceuticalStockTracker{
    return PharmaceuticalStockTracker()
}

//  Task 5
//  Fill out the method sellContainers(count:of) below. This accepts as parameters a
//  count of containers to sell, and an ndcPackageCode.
//  It returns a Bool, a SellMessage and an optional array of MedicationContainers.
//  Like usual, this should validate the format of the ndcPackageCode, then find out
//  if there is any inventory of the ndcPackageCode. If so, confirm that there is
//  enough. If not, return a tuple of (false, .notEnoughInventory, nil). If there
//  is enough of inventory of the requested ndcPackageCode, be sure to sell those
//  with the earliest dates first. Return a sorted array of the containers sold.
//  If the sale results in the Set being emptied out, remove that dictionary entry.
//
//  When you have completed and tested the code for sellContainers(),
//  change task5() to return true rather than nil

enum class SellMessage {
    SUCCESS, INVALID_COUNT, NDC_CODE_FORMAT_ERROR, NO_INVENTORY, NOT_ENOUGH_INVENTORY
}

fun PharmaceuticalStockTracker.sellContainers(count: Int, ndcPackageCode: String): Pair<SellMessage, Set<MedicationContainer>> {
    return Pair(SellMessage.SUCCESS, setOf())
}

fun task5(): PharmaceuticalStockTracker{
    return PharmaceuticalStockTracker()
}

//  Task 6
//  Add a mutating method called setDates to the DateSequencer struct below
//  with a parameter called daysTuple. The parameter should be a tuple of Type
//  (Int, Int). The method should use the parameter to set the values (in order)
//  of the two predefined properties: currentDaysOut and lastDaysOut. The purpose
//  of this task is to set things up for the following tasks.
//
//  After you make the change, apply the additional protocol DateSequencerProtocol
//  change task6() to return true rather than nil to will let the tests run.
//
//struct DateSequencer  {
//
//    var currentDaysOut = 0
//    var lastDaysOut = 10
//
//    // your code goes here
//}
//func task6() -> Bool? {
//    return nil
//}

//  Task 7
//  Add to the extension below to make the DateSequencer conform to the Sequence
//  protocol. In conforming to this protocol, you can either have the object create
//  a separate iterator or you can have the object be its own iterator . For this
//  task, use the option where it is its own iterator  so in addition to conform
//  to Sequence, it needs to explicitly conform to IteratorProtocol. You will
//  need to add both of these protocols to the first line of the extension.
//
//  The next() function you implement to conform should return a date optional.
//      If currentDaysOut == lastDaysOut then it should return nil.
//      Otherwise, it should return a Date currentDaysOut in the future.
//  Returning nil is how it indicates that the sequence is complete.
//  It should then increment currentDaysOut "towards" lastDaysOut. That is:
//      if currentDaysOut < lastDaysOut add 1 to currentDaysOut
//      if currentDaysOut > lastDaysOut subtract 1 from currentDaysOut.
//  In implementing this, you are welcome to call either or
//  both of daysToMs() and futureDate() or to borrow code from them.
//
//  The result of your code will be objects that generate a sequence of dates.
//  If you call setDates(x, y) then the struct will behave as a sequence of dates
//  starting x days in the future and continuing up or down the calendar until
//  just before it would output y days into the future.
//
//  When you have completed and tested these changes apply the additional
//  protocol DateSequencerProtocol2 to the extension and change task7() to
//  return true rather than nil.
//

//extension DateSequencer  {
//    // add your code here
//}
//func task7() -> Bool? {
//    return nil
//}

//  Task 8
//  Lets demonstrate how having the DateSequencer object conform to
//  Sequence really adds value. In task8() below, create a DateSequencer
//  object. Call setDates() on your DateSequencer using the parameters passed
//  into task8(). Create an empty array of type [Date]. Then write a for-in
//  loop that loops on your DateSequencer and appends each value produced by
//  the for statement to your array of Dates. The result will be that for-in
//  uses your struct to help you create an array of dates that matches the
//  parameters.
//
//  When you are done with your code, change task8() to return the array produced
//  instead of returning nil.
//
//func task8(_ aTuple: (Int, Int)) -> [Date]? {
//    return nil
//}

//  Task 9
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

// For task 9, make the following 3 classes adopt the "Shape" protocol
//      - Circle
//      - Triangle
//      - Rectangle

class Circle(private val radius: Double): Shape {
    override fun area(): Double {
        return Math.PI * radius.pow(2.0)
    }
}

class Triangle(private val base: Double, private val height: Double): Shape {
    override fun area(): Double {
        return .5 * base * height
    }
}

class Rectangle(private val width: Double, private val height: Double): Shape {
    override fun area(): Double {
        return width * height
    }
}

fun task9(s: Shape): Double {
    return getArea(s)
}
