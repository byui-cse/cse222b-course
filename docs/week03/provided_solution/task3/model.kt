import java.lang.Exception
import java.time.LocalDate
import java.util.UUID
import kotlin.collections.ArrayList

/**
 * model.kt
 * week03_task_3
 */

enum class EmployeeType{
    PROGRAMER, DEVLEAD

}
/**This has to be done using Is_a type inheritance in order to work with the try-catch system*/
class AssignmentException(message:String): Exception(message) {}

data class Employee(var name:String, val hireDate: LocalDate, var yearsExperience:Short, val type:EmployeeType){
    var id: UUID = UUID.randomUUID()
}



data class Manager(var name:String, val hireDate:LocalDate, var yearsExperience:Short, var type:EmployeeType,
                   var canAddManageeCriteriaFunc:((ArrayList<Employee>)->Boolean)){
    var employee = Employee(name, hireDate, yearsExperience, type)
    val managees = ArrayList<Employee>()
}


