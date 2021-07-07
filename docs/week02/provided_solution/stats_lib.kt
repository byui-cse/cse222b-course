/**
 * Calculates and returns a list of lists. Each sub-list contains stats for a sub-list
 * in the list-of-lists parameter. Within each returned sublist are the mean, maximum, and
 * minimum values statistics representing the matching sub-list in the list-of-lists parameter.
 *
 * Complexity - O(n^2)
 */
fun generateArrayStats(data:ArrayList<ArrayList<Int>>):ArrayList<ArrayList<Double>>{
    var stats = ArrayList<ArrayList<Double>>()
    var min = Double.MAX_VALUE
    var max = -Double.MAX_VALUE
    var sum  = 0.0
    for (row:ArrayList<Int> in data){
        for (pressure in row){
            sum += pressure
            if (pressure > max){
                max = pressure.toDouble()
            }
            else if (pressure < min){
                min = pressure.toDouble()
            }
        }
        stats.add(arrayListOf<Double>(sum/row.size,max,min))
    }
    return stats
}

