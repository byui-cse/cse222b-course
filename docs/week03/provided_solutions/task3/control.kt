import java.time.LocalDate
import java.time.Period

/**
 * control.kt
 * week03_task_3
  */



/**
 * This function evaluates a managee parameter and a manager parameter to make sure that
 * there is no conflict that would keep the managee from being assigned
 * to the manager. If there is no conflict, the managee becomes part of the
 * managees for the manager. If there is a conflict the assignment is not made.
 * The function returns (true,AssignmentError.none) if the assignment occured.
 * (false,AssignmentError.errorDescription) if the assignement could not be made.
 * This behavior has a complexity of O(n)
 */


fun add(managee:Employee, to:Manager) {
    if (to.managees.count() > 15) {
        throw AssignmentException("Error: violated add managee criteria")
    }

    val experiencedManagees = to.managees.filter{
        Period.between(it.hireDate, LocalDate.now()).years >= 1 && it.yearsExperience > 5
    }
    /**apply rules from problem description*/
    if(Period.between(managee.hireDate, LocalDate.now()).years < 5 &&
        experiencedManagees.count() == 0) {
        if (to.managees.count() == 2) {
           throw AssignmentException("Error: no Experienced managee")
        }
    }
    to.managees.add(managee)
}

/**
 * This function generates a list of hires that need to be made in order to meet the
 * criteria indicated by human resources. It has a single parameter [devLeads] that is a list of current Managers.
 * Each manager has a list of programmers they are currently managing.
 * This function returns a list of programmer types and the count of each required to meet the HR criteria and
 * has a complexity of O(n).
 */

fun generateNeededHires(devLeads:ArrayList<Manager>):ArrayList<Pair<String,Int>>{


    val numDevLeads = devLeads.count()

    /**produces a Pair lists, experienced and novice, of programmers from the lists of manager's managees*/

    val sortedProgrammers = devLeads.fold(Pair(ArrayList<Employee>(),ArrayList<Employee>())){ accumulator, manager ->
        /**let the first element of thee Pair be the experienced programmers and the second be the novices*/
        val experienced = manager.managees.filter{
            it.yearsExperience > 5 && Period.between(it.hireDate, LocalDate.now()).years >= 1
        }
        /** remove from a copy, not the original*/
        val novices = manager.managees.toMutableList()
        novices.removeAll(experienced)
        accumulator.first.addAll(experienced)
        accumulator.second.addAll(novices)
        accumulator
    }

    //calculate the needed programmers by type
    val numExperiencedNeeded = numDevLeads - sortedProgrammers.first.count()
    val numRemainingProgrammersNeeded = numDevLeads*3 - sortedProgrammers.first.count() - sortedProgrammers.second.count()

    return arrayListOf(Pair("Experienced",numExperiencedNeeded),Pair("Novice",numRemainingProgrammersNeeded))
}