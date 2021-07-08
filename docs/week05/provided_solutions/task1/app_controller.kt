//
//  app_controller.kt
//  week05_task_1
//
//




/** a series of typedefs to ease code clarity*/
typealias DataMap = HashMap<String, Any>
typealias RequestFunc = (DataMap)->(DataMap?)
typealias CommandMap = HashMap<String,RequestFunc>


/** a series of values used by the handleRequest function to communicate success and errors*/
enum class RequestMessage {
    SUCCESS,
    FAILEDVALIDATION,
    FAILEDINTERACTION,
    FAILEDRESPONSE,
    NOSUCHCOMMAND
}

private var validationFunctions = CommandMap()
private var interactionFunctions = CommandMap()
private var responseFunctions = CommandMap()

/** This function executes a specified set of behaviors for a given command.
 *
 * The behaviors executed are those stored in the store method of this module.
 * The order of execution is 1)validation, 2)interaction, 3)response. If any
 * of the stored functions returns nil, this function returns without executing any
 * of the other stored functions with an appropriate error indicated in the returned value.
 * See the RequestMessage struct for all possible messages.
 *
 * This function has, as arameters, [command], a unique indicator of which set of behaviors to execute, 
 * and [data], any data required to act on the command. It returns (RequestMessage.success,\[String:Any\])
 * when all functions work without error and (RequestMessage,[\String:Any\]) otherwise.
 *
 * The function also has a complexity of O(1) plus the complexity of each of the validation,
 * interaction, and response functions.
 */
fun handleRequest(forCommand:String, withData:DataMap):Triple<Boolean,RequestMessage,DataMap?>{
    /** find each of the three functions for the command or return failure */
    val aValidationFunction = validationFunctions[forCommand]
                            ?: return Triple(false,RequestMessage.NOSUCHCOMMAND, withData)
    val anInteractionFunction = interactionFunctions[forCommand]
                            ?: return Triple(false,RequestMessage.NOSUCHCOMMAND, withData)
    val aResponseFunction = responseFunctions[forCommand]
                            ?: return Triple(false,RequestMessage.NOSUCHCOMMAND, withData)

    /** get the validation data or return failure */
    val theValidationData = aValidationFunction(withData)
                            ?: return Triple(false,RequestMessage.FAILEDVALIDATION,withData)

    /** get the interaction data or return failure */
    val theInteractionData = anInteractionFunction(theValidationData)
                            ?: return Triple(false,RequestMessage.FAILEDINTERACTION,theValidationData)

    /** get the response data or return failure */
    val theResponseData = aResponseFunction(theInteractionData)
                            ?: return Triple(false,RequestMessage.FAILEDRESPONSE,theInteractionData)

    /** everything worked as expected */
    return Triple(true,RequestMessage.SUCCESS,theResponseData)
}

/** This function stores a set of RequestFuncs for later execution.
 *
 * Each time the command and a DataMap is passed to handleRequest, the functions stored by this
 * function are executed. The parameters of this function are [validationFunction], a function
 * used to ensure the incoming data meets requirements and is verified as 'safe', [interactionFunction],
 * a function used to do any required business logic, [responseFunction], a function to generate a response
 * using the result of the interactionFunction, and [command], a unique indicator for this set of behaviors.
 *
 * The complexity of this function is O(1)
*/
fun store(validationFunction:RequestFunc, interactionFunction:RequestFunc,
          responseFunction:RequestFunc, forCommand:String){
    validationFunctions[forCommand] = validationFunction
    interactionFunctions[forCommand] = interactionFunction
    responseFunctions[forCommand] =  responseFunction
}

