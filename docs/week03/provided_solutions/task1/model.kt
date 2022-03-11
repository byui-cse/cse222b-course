import java.lang.Exception
import java.time.LocalDate
import java.util.UUID
import kotlin.collections.ArrayList

/**
 * model.kt
 * week03_task_1
 */

enum class EmployeeType{
    PROGRAMER, DEVLEAD

}

class AssignmentException(message:String): Exception(message) {}

open class Employee(var name:String, val hireDate: LocalDate, var yearsExperience:Short, val type:EmployeeType){
    var id: UUID = UUID.randomUUID()
}



class Manager(name:String, hireDate:LocalDate, yearsExperience:Short, type:EmployeeType):Employee(name, hireDate, yearsExperience, type){
    val managees = ArrayList<Employee>()

}


