import java.time.LocalDate






open class MedicationContainer(val id:String, val name:String, val expirationDate:LocalDate)


class LiquidMedicationContainer(id:String, name:String, expirationDate: LocalDate, val volume:Double,
                                val concentration:Int, val concentrationUnits:String):MedicationContainer(id, name, expirationDate)

class TabletMedicationContainer(id:String, name:String, expirationDate: LocalDate,
                                val pillCount:Int, val potency:Double, val potencyUnits:String):MedicationContainer(id, name, expirationDate)