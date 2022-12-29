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

    var inStockMedications = mutableMapOf<String, MedicationContainer>()
    val count: Int
        get() = 0

    fun count(of: String): Int {
        return 0
    }

    fun addContainer(container: MedicationContainer): Boolean {
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
//  codes: [5 digits]-[3 digits]-[2 digits]. When testing your code you may use
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
//  The function returns a tuple with a Bool and an enum of Type AddMessage.
//  As you complete the method, parts of your code should return each of the
//  values in AddMessage except possibly .otherAddFailure. The others all
//  represent error (or success) conditions that you should detect as you
//  implement addContainers(). One  error you should detect would be found
//  by calling the function isFormattedAsNDCCode() to verify the format
//  of the NdcPackageCode used.
//
//  Hint: Remember to deal with both the case where there are currently no
//  containers matching the ndcPackageCode and the case where there are
//  already some containers matching the ndcPackageCode.
//
//  When you have completed and tested the code for addContainers(),
//  change task3() to return true rather than nil

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
//  ndcPackageCode. It returns a tuple with a Bool, an enum of Type StockMessage
//  and an optional array of MedicationContainers. Validate the ndcPackageCode format,
//  check if there are any containers of that type and if there are, return them in an
//  array. Since we should use up medications with the oldest expiration date first,
//  sort the array by expiration date before returning it so the older expiration
//  dates come first.
//
//  When you have completed and tested the code for currentStock(of:),
//  change task4() to return true rather than nil

enum class StockMessage {
    SUCCESS, NDC_CODE_FORMAT_ERROR, NO_INVENTORY, UNKNOWN_FAILURE
}

fun PharmaceuticalStockTracker.currentStock(of: String): Pair<StockMessage, Set<MedicationContainer>> {
    return Pair(StockMessage.SUCCESS, setOf())
}

fun task4(): PharmaceuticalStockTracker{
    return PharmaceuticalStockTracker()
}

