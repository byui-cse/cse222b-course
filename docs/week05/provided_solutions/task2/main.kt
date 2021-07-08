import java.lang.Math.log
import java.lang.Math.pow
import kotlin.random.Random

fun main(args: Array<String>) {
    /** setup of the triangular distribution stream */
    val triangularDefinition = mapOf("a" to 0.0, "b" to 1.0, "c" to 0.22)
    val triangularDistStream = RandomDistStream(triangularDefinition){
        val a = it["a"] as Double
        val b = it["b"] as Double
        val c = it["c"] as Double

        val u = Random.nextDouble(a,b)
        val v = Random.nextDouble(a,b)
        (1 - c)* java.lang.Double.min(u, v) + c* java.lang.Double.max(u, v)/** return value */
    }
    /** exercising the triangular stream */
    println("Triangularly distributed random values:")
    for(ignored in 0 until 5){
        println("\t${triangularDistStream.next()}")
    }

//setup of the logarithmic distribution stream
    val logarithmicDefinition = mapOf("p" to 0.85)
    val logarithmicDistStream = RandomDistStream( logarithmicDefinition){
        val p = it["p"] as Double
        val x = Random.nextDouble(0.0,1.0)
        pow(p,x)/(-x * log(1-p)) /** return value */
    }
//exercising the logarithmic stream
    println("Logarithmically  distributed random values:")
    for(ignored in 0 until 28){
        println("\t${logarithmicDistStream.next()}")
    }
}