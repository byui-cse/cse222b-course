/** This is a generic class that allows for the creation of any
 * random distribution stream.
 *
 * The values produced by this stream are determined by the interaction of
 * the provided definition data and the behavior closure.
 */

class RandomDistStream(val definitionData:Map<String,Any>, val behavior: (Map<String,Any>) -> Any) {

    fun next():Any{
        return behavior(definitionData)
    }
}