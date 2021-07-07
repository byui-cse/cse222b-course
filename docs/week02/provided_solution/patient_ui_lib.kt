import java.lang.Exception

/**
 * This function is the 'view' part of this application. The user enters the 'patient number' which
 * is interpreted as the index number of the list-of-lists parameter. If the user enters a valid
 * patient number, the mean, maximum, and minimum diastolic blood pressures are printed out. Otherwise
 * the user is notified that they entered an invalid and asked for another patient number.
 *
 * Complexity - O(1)
 */
fun repeatedlyPrintRequestedStats(stats:ArrayList<ArrayList<Double>>){

    while (true) {
        print("Enter a patient number in 0 to ${stats.size}: ")
        val indexString = readLine()
        if (indexString == "done") {
            return
        }
        try{
            var index = indexString!!.toInt()
            while (index < 0 || index >= stats.count()) {
                print("\n$index is not a valid patient number. Enter patient number: ")
                try {
                    index = readLine()!!.toInt()
                   }
                catch (e:Exception){
                    continue
                }
            }
            println("Patient: $index BP Stats: Mean-${stats[index][0]}, Maximum-${stats[index][1]},Minimum-${stats[index][2]},")

        }
        catch (e:Exception){
            print("\n$indexString is not a valid patient number.")
        }

    }
}