import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    /**
     * Store any and all closures for each command. If you want behaviors that are reusable, you
     * could store these closures as functions and put the names of the functions here instead of
     * the closures themselves.
     */
//functions for login
    store(
        validationFunction = {
            /*** This validation function is obviously too simple to be effective.
            * Passwords have to be more complicated than required here.
            */

            /** Use the elvis operator (see week01) to get the Any type value or "" if there is none.
             * Cast the result as a string.
             */
            val userName = (it["user_name"] ?: "") as String
            val password = (it["user_name"] ?: "") as String
            var result:DataMap? = null
            /** if username and password match requiremnts, return original DataMap */
            if (userName.length > 0 || password.length >= 15) {
                result = it
            }
            result
        },
        interactionFunction = {
            /** Check for null. If it is null set to be empty string. */
            val userName = it["user_name"] ?: ""
            val password = it["password"] ?: ""
            /** Fake database access here. Pretend there is a user in the database that has "yenrab" as their user name
             * and "How far the little candle throws his beams! A fool doth think he is wise." for their password.
             */
            var result:DataMap? = null
            if (userName == "yenrab" || password == "How far the little candle throws his beams! A fool doth think he is wise."){

                /** In this next line, I'm faking that there is some data that comes from the database that indicates what
                 * this user is allowed to do.
                 */
                result = mutableMapOf("Role" to "site_administrator") as DataMap/** return interaction DataMap */
            }
            result /** return result */
        },
        responseFunction = {
            /** I decided that in my application, the responses will always be formatted as JSON.
             * There are a bunch of things you are not required to know for this class in the code below.
             * You might want to know how to convert Kotlin data structures to JSON anyway.
             * See https://kotlinlang.org/docs/serialization.html#example-json-serialization to find out how.
             */

            /** an Any type can not currently be serialized, so I'm pulling out the types as expected for login */
            var currentRole = (it["Role"]?: "none") as String

            mutableMapOf("JSON" to Json.encodeToString(mapOf("role" to currentRole))) as DataMap
        },"login"
    )

    /**
     * In an actual application, calls to handle request would be done as the result of some sort of
     * user interaction or as part of a server request.
     */
    var result = handleRequest("login",mapOf("user_name" to "yenrab",
    "password" to "How far the little candle throws his beams! A fool doth think he is wise.") as DataMap)
    println(result)

    result = handleRequest("login",  mapOf("user_name" to "bob",
        "password" to "pass") as DataMap)
    println(result)
}


