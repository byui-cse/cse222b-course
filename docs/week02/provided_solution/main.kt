/**
 * The entry point for the application.
 *
 * Complexity - O(n^2)
 */
fun main(args: Array<String>) {

    /*
     * Generate a list-of-lists to hold simulated diastolic blood pressures
     */
    var diastolicBloodPressures = arrayListOf<ArrayList<Int>>()

    for (count in 0..100){
        var row = arrayListOf<Int>()
        for (i in 0..40){
            row.add((60..130).random())//random numbers in [60,130).
        }
        diastolicBloodPressures.add(row)
    }
    //generate the statitics
    val theStats = generateArrayStats(diastolicBloodPressures)
    //print out the statistics requested by the user.
    repeatedlyPrintRequestedStats(theStats)
}

