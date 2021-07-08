/**
 * generate_data.kt
 * week03_task_3
 */

import java.lang.Double.max
import java.lang.Double.min
import java.lang.Exception
import java.time.LocalDate
import java.time.Period
import kotlin.collections.ArrayList
import kotlin.random.Random


/**
 * This data class represents a stream of random numbers for a given triangular distribution as
 * described at(https://www.statology.org/triangular-distribution/ ).
 *The least value is a, the greatest value is b, and the peak value is c. All are required at instantiation. This stream implements the
 * Stein, Keblis (2009) (https://www.sciencedirect.com/science/article/pii/S0895717708002665) algorithm.
 *This data class is file private.
 */
private data class TriangularDistributionStream(val a:Double, val b:Double, val c:Double) {

    /**
     * This method gets the next value in the distribution stream. All values returned are Doublees in [a,b)
     * The complexity of this function is O(n).
     */
    fun next():Double {
        val u = Random.nextDouble(a,b)//O(n)
        val v = Random.nextDouble(a,b)//O(n)
        return(1-this.c)*min(u,v) + c*max(u, v)
    }
}

/**
 * This function generates a simulated set of Manager and Employee (programmer type) instances used to drive the
 * managee assignment and hiring needs code. No management assignments have been made for the programmers.
 * It has as parameters numbers as indicated in the problem requirements. It also returns a pair consisting of an
 * ArrayList of Manager instances and an ArrayList of Employees of type programmer.
 * This function has a complexity of O(n).
 */

fun generateData(numManagersNeeded:Int, numProgrammersNeeded:Int):Pair<ArrayList<Manager>,ArrayList<Employee>> {
    /**
     * Generate an Array of managers. Data generation of a specific number of items
     * is one of the few places where the use of for loops is defensible.
     */
    var existingManagers = buildManagers(numManagersNeeded)


    /**
     *  This value of c guarantees the number of experienced programmers is less than the number of managers
     *  as per the problem requirements. This line creates a stream of numbers in a triangular distribution.
     */

    val experienceStream = TriangularDistributionStream(0.0, 1.0, 0.22)

    //Generate an Array of programmers.
    var programmers:ArrayList<Employee> = buildProgrammers(numProgrammersNeeded, experienceStream)


    //Divide the programmer list into novice and experienced programmers.
    val experiencedProgrammers = programmers.filter{
        val yearsHere = Period.between(it.hireDate, LocalDate.now()).years
        Period.between(it.hireDate, LocalDate.now()).years >= 1 && it.yearsExperience > 5
    }
    //Remove from programmers rather than filter as an example of using another method available
    //from the Array API.
    programmers.removeAll(experiencedProgrammers)
    println("Generated Testing Data")
    println("\tThis is provided as a service to aid you in debugging your code.\n\tDo NOT use it for calculations.")
    println("\tManagers: ${existingManagers.count()}")
    println("\tNovice Programmers: ${programmers.count()}")
    println("\tExperienced Programmers: ${experiencedProgrammers.count()}")

    //distribute experienced programmers to managers
    existingManagers = assignExperiencedEmployee(ArrayList(experiencedProgrammers), existingManagers)

    var unassignedProgrammers = ArrayList<Employee>()
    /**distrubute novice programmers to managers*/
    for ( index in 0..programmers.count()-1){
        val programmerIndex = index
        var suceeded = false
        while (!suceeded) {
            try {
                add(programmers[index], existingManagers[index % existingManagers.count()])
                suceeded = true
            } catch (e: Exception) {
            }
        }
        if (!suceeded){
            unassignedProgrammers.add(programmers[index])
            println("Couldn't add a novice Programmer ${programmerIndex} to a Manager.")
        }
    }
    return Pair(existingManagers,unassignedProgrammers)
}

/**
 * This function generates the required number of programmers as per the problem statement. It has as
 * parameters numNoviceProgrammersNeeded, the remaining number of programmers needed, and experienceStream,
 * a triangular distribution used to determine the years of experience for each generated programmer.
 * This function returns a list of programmer type employees, is file private, and has a complexity of O(n).
 */


private fun buildProgrammers(numNoviceProgrammersNeeded:Int, experienceStream:TriangularDistributionStream):ArrayList<Employee>{
    if (numNoviceProgrammersNeeded == 0) {
        return ArrayList<Employee>()
    }
    val aHireDate = randomHireDate()
    var yearsAtCompany = Period.between(aHireDate, LocalDate.now()).years.toShort()
    val aYearsExperience = (experienceStream.next()*10).toInt().toShort()
    if (aYearsExperience < yearsAtCompany){
        yearsAtCompany = aYearsExperience
    }
    var employees = arrayListOf<Employee>(Employee(randomName(), aHireDate, aYearsExperience, EmployeeType.PROGRAMER))
    employees.addAll(buildProgrammers( numNoviceProgrammersNeeded-1, experienceStream))
    return employees
}
/**
 * This file private function instantiates a series of Manager instances according to the problem requirement. Each
 * Manager instance has no managees. It has as parameters,  numManagersNeeded, the remaining number of managers that need to be
 * instantiated and returns a list of instantiated managers with no managees.
 * This function has a complexity of O(n).
 *
 */
private fun buildManagers(numManagersNeeded:Int): ArrayList<Manager> {
    if (numManagersNeeded == 0){
        return ArrayList<Manager>()
    }
    val aManager = Manager(randomName(), LocalDate.now(), (0..21).random().toShort(), EmployeeType.DEVLEAD)
    var managers = arrayListOf<Manager>(aManager)
    managers.addAll(buildManagers(numManagersNeeded - 1))
    return managers
}
/**
 * In this simulated data there are fewer experienced employees than managers. This is as per
 * the problem description. Therefore this file private function assignees experienced employees to managers
 * until there are no more experienced employees left to assigned. This implies that there will
 * be some managers that do not have an experienced employee that they are currently assigned to manage.
 * The parameters for this function are, [employees], the remaining experienced employees that have not been assigned to a manager,
 * and [managers], the remaining managers that don't have an experienced employee assigned to them.
 * When this function completes, it returns a list of managers with zero or one experienced employee assigned to them
 * It also has a complexity of O(n).
 */
private fun assignExperiencedEmployee(employees:ArrayList<Employee>,managers:ArrayList<Manager>):ArrayList<Manager> {
    if (employees.count() == 0) {
        return managers
    }
    val employeeHead = employees.removeAt(0)
    val managerHead = managers.removeAt(0)
    val headList = arrayListOf(managerHead)
    try{
        add(employeeHead, managerHead)
        headList.addAll(assignExperiencedEmployee(employees, managers))
    }catch (e:Exception){}
    return headList
}

/**
 * This file private function generates a random string of six characters.
 * It returns this string to be used as a name. The name need not make sense in any language.
 */
private val charPool : List<Char> = ('a'..'z') + ('A'..'Z')
private fun randomName():String {
    return  (1..6)
        .map { _ -> kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("");
}

/**
 *  This file private function generates a LocalDate used as a determinator for if an employee is a novice or is experienced.
 *  This function is module private, returns a simulated hire date in the range 0 to 10 years, and has a complexity
 *  of O(1).
 */

private fun randomHireDate():LocalDate{
    return LocalDate.now().plusYears(-(0L..10L).random())
}
