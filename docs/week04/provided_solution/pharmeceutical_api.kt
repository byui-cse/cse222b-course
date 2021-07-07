/**
 * A MutableSt of the MedicationContainers that are currently in stock.
 * In a 'real' application, this would be replaced with calls to a database containing
 * all of this information.
 */
private val inStockMedications = mutableMapOf<String, Set<MedicationContainer>>()

/**
 * A message set that indicates success or failure when one of these API functions is used.
 */
enum class StockingMessage{
    SUCCESS,
    POORLYFORMATTEDNDCCODE,
    NOSUCHNDSCODE,
    EMPTYCONTAINERSET,
    MIXEDNDCCODES,
    INVALIDSALECOUNT,
    INSUFFICIENTSTOCK
}


/**private functions are not part of the published API.*/
private fun isFormattedAsNDCCode(code:String):Boolean{
    return code.matches(Regex(pattern = """\d{5}-\d{3}-\d{2}"""))
}
/** Adding liquid or tablet type medication containers requires an NDC package code be used.
 * The package code must be passed to the function as the first parameter to assure that
 * all medications being added are of the correct type. This 'double factor authentication'
 * type of behavior is used to decrease the probability of the wrong medications being sold
 * so as to avoid death or damaged health of those taking the medications sold.
 *
 * This function has two parameters [expectedNdcCode], a String formatted as "\[5 digits\]-\[3 digits\]-\[2 digits\]
 * that is the NDC code for the medication, and [containersToAdd], a set of MedicationContainers, Tablet or Liquid,
 * that each have the same NDC code as the expectedNdcPackageCode parameter.
 *
 * This function returns  (true, StockingMessage.SUCCESS) when the addition succeeds or (false,StockingMessage) on failure.
 * See StockingMessage for possible failure indicators. It also has a complexity of O(n).
*/
fun  add(expectedNdcCode:String, containersToAdd:Set<MedicationContainer>):Pair<Boolean,StockingMessage>{
    if (!isFormattedAsNDCCode(expectedNdcCode)){
        return Pair(false,StockingMessage.POORLYFORMATTEDNDCCODE)
    }
    else if (containersToAdd.count() == 0){
        return Pair(false,StockingMessage.EMPTYCONTAINERSET)
    }
    val matchingContainers = containersToAdd.filter { it.id == expectedNdcCode }
    if (matchingContainers.count() != containersToAdd.count()){
        return Pair(false,StockingMessage.MIXEDNDCCODES)
    }
    val existingContainers = inStockMedications[expectedNdcCode]
    if (existingContainers != null){
        inStockMedications[expectedNdcCode] = existingContainers.union(containersToAdd)
    }
    else{
        inStockMedications[expectedNdcCode] = containersToAdd
    }
    return Pair(true,StockingMessage.SUCCESS)
}
/** When medications are sold, the tracking system must be updated. After the sale is confirmed, this function
 * removes the stock from the system.
 * This function has two parameters, [count], the number of containers sold, and [ndcPackageCode],a String
 * formatted as "\[5 digits\]-\[3 digits\]-\[2 digits\] that is the NDC code for the medication.
 * This function returns (StockingMessage.success,\[MedicationContainer]) on success, (StockingMessage,nil) otherwise.
 * See StockingMessage for possible failure indicators. It also has a complexity of O(n).
*/
fun sold(count:Int, ndcPackageCode:String):Pair<StockingMessage,List<MedicationContainer>?>{
    if (count <= 0){
        return Pair(StockingMessage.INVALIDSALECOUNT,null)
    }
    if (!isFormattedAsNDCCode(ndcPackageCode)){
        return Pair(StockingMessage.POORLYFORMATTEDNDCCODE,null)
    }
    val existingStock = inStockMedications[ndcPackageCode]
    if (existingStock == null){
        return Pair(StockingMessage.NOSUCHNDSCODE,null)
    }
    if (existingStock.count() < count){
        return Pair(StockingMessage.INSUFFICIENTSTOCK,null)
    }
    val soldContainers = existingStock.sortedByDescending { it.expirationDate }.take(count)
    inStockMedications[ndcPackageCode] = existingStock.minus(soldContainers)
    return Pair(StockingMessage.SUCCESS, soldContainers)
}

/** The current containers of medication for a specific NDC code are returned as a set so as
 * to easily allow set functions to be applied to the results. This function has as one parameter
 * [ndcPackageCode],a String formatted as "\[5 digits\]-\[3 digits\]-\[2 digits\] that is the NDC code for the medication.
 * It returns (StockingMessage.success,\[MedicationContainer\]) on success (StockingMessage,nil) otherwise.
 * See StockingMessage for possible failure indicators. It also has a complexity of O(n).
*/
fun currentStockOf(ndcPackageCode:String):Pair<StockingMessage, List<MedicationContainer>?>{

    if (!isFormattedAsNDCCode(ndcPackageCode)){
        return Pair(StockingMessage.POORLYFORMATTEDNDCCODE,null)
    }
    val existingStock = inStockMedications[ndcPackageCode]
    if (existingStock == null){
        return Pair(StockingMessage.NOSUCHNDSCODE,null)
    }
    return Pair(StockingMessage.SUCCESS, existingStock.sortedByDescending { it.expirationDate })
}