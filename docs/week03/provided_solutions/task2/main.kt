/**
 * main.kt
 * week03_task_2
 */

fun main(args: Array<String>) {
    /** This is the view code for the code driver app*/


//generating the data to drive the classes created and the generateNeededHires function
    val (managerData,unassignedProgrammers) = generateData(53, 123)

    val needs = generateNeededHires(managerData)

    println("\n\nResults:")
    println("\tProgrammers Not Yet Assigned: ${unassignedProgrammers.count()}")
    println("\tNeeded Assignments: $needs")
}



