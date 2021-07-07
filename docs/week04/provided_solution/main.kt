import java.time.LocalDate

/**
 * Please remember that this is NOT the code you will have written. It is one way of
 * accomplishing the task. This example is available for you to learn from, not to compare
 * against your solution to see if you 'got it right.'
 *
 * You will probably see some things you haven't read about or seen. Take a few of them as
 * things you can learn more about.
 */

fun main(args: Array<String>) {
    /**
    * Here are some integration tests. These are NOT unit tests.
    */

    /**
     * These tests are not a complete set. They are examples of how to exercise
     * code. An industrial grade set would include other tests of the interactions
     * between the functions as well as detailed tests of the functions without
     * dependencies on the other functions (unit tests).
     */

    /**
     * Some of these tests use != and ||. Others use == and &&. This is just for example comparison.
     */


    /**
     * Testing add function in prepraration for testing the other functions.
     */
    println("TESTING ADD FUNCTION.")
    var testingSet = mutableSetOf<TabletMedicationContainer>()

    /**
     * Make sure the 'happy path' works correctly.
     */
    println("\tTesting Happy Path")

    for (dayOffset in 0..29L){
        testingSet.add(TabletMedicationContainer("12345-678-90", "Cures-All",
                        LocalDate.now().plusYears(-100+dayOffset),
                        500, 3.5, "mg"))
    }

    val (succeeded,message) = add("12345-678-90", testingSet)

    if (succeeded != true || message != StockingMessage.SUCCESS){
        println("\t\tFAILED:  (true,SUCCESS). Got ($succeeded, $message).")
    }
    else{
        println("\t\tPassed")
    }

    println("\tTesting Add Empty Set")
    val (added,addMessage) = add( "12345-678-90",  setOf())

    if (added == true || addMessage != StockingMessage.EMPTYCONTAINERSET){
        println("\t\tFAILED:  (false,EMPTYCONTAINERSET). Got ($added,$addMessage).")
    }
    else{
        println("\t\tPassed")
    }


    println("\tTesting Mixed Package Codes")
    testingSet.clear()

    testingSet.add(
        TabletMedicationContainer("12345-678-90",  "Cures-All",   LocalDate.now().plusYears(-100),  500,  3.5,  "mg")
    )
    testingSet.add(
        TabletMedicationContainer( "12333-333-99", "Stuff",  LocalDate.now().plusYears(-100), 500,  0.5,  "mg")
    )

    val (mixedSucceeded,mixedMessage) = add("12345-678-90",  testingSet)

    if (mixedSucceeded != false || mixedMessage != StockingMessage.MIXEDNDCCODES){
        println("\t\tFAILED:  (false,MIXEDNDCCODES). Got ($mixedSucceeded,$mixedMessage).")
    }
    else{
        println("\t\tPassed")
    }

    println("\tTesting Bad Declared NDC Code")

    testingSet.clear()

    testingSet.add(
        TabletMedicationContainer("12345-678-90", "Cures-All", LocalDate.now().plusYears(-100),  500,  3.5,  "mg")
    )

    val (badNDCSucceeded,badNDCMessage) = add( "123", testingSet)

    if (badNDCSucceeded != false || badNDCMessage != StockingMessage.POORLYFORMATTEDNDCCODE){
        println("\t\tFAILED:  (false,POORLYFORMATTEDNDCCOD). Got ($succeeded,$message).")
    }
    else{
        println("\t\tPassed")
    }

    var setupCompvale = 0

    /***
     * This test is done in conjunction with the add function.
     */
    println("TESTING CURRENTSTOCKOF FUNCTION")
    var stuffs = mutableSetOf<TabletMedicationContainer>()
//setup so there are several different types of meds in the 
    for (dayOffset in 0..29L){
        stuffs.add(
            TabletMedicationContainer("12333-333-99", "Stuff", LocalDate.now().plusYears(-10 + dayOffset), 500, 0.5, "mg")
        )
    }
    val (add1Succeeded,_) = add("12333-333-99", stuffs)
    if (add1Succeeded){
        setupCompvale += 1
    }
    var others = mutableSetOf<LiquidMedicationContainer>()
    for (dayOffset in 0..16){
        others.add(
            LiquidMedicationContainer("12333-344-19", "Other", LocalDate.now().plusYears(5L + dayOffset), 500.0,  5,  "mg/dl")
        )
    }
    val (add2Succeeded,_)  = add("12333-344-19", others)
    if (add2Succeeded){
        setupCompvale += 1
    }
    var whats = mutableSetOf<TabletMedicationContainer>()
    for (dayOffset in 0..110){
        whats.add(
            TabletMedicationContainer("22222-111-00", "What?", LocalDate.now().plusYears(45L + dayOffset), 500, 0.5, "mg")
        )
    }
    val (add3Succeeded,_)  = add("22222-111-00", whats)
    if (add3Succeeded){
        setupCompvale += 1
    }
    if (setupCompvale != 3){
        println("FAILED: setup incompvale.")
    }


    println("\tTesting happy path")
    var passed = true
    val (found,medications) = currentStockOf("22222-111-00")
    if (medications != null){
        val differenceCount = whats.subtract(medications).count()
        if (found != StockingMessage.SUCCESS || differenceCount != 0){
            passed = false
            println("\t\tFAILED: Expected SUCCESS with differenceCount = 0. Got $found and $differenceCount.")
        }
    }

    if (passed){
        println("\t\tPassed")
    }

    //reset for next test
    passed = true
    val (h2found,h2medications) = currentStockOf("12333-333-99")
    if (h2medications != null){
        val differenceCount = stuffs.subtract(h2medications).count()
        if (h2found != StockingMessage.SUCCESS || differenceCount != 0){
            passed = false
            println("\t\tFAILED: Expected SUCCESS with differenceCount = 0. Got $h2found and $differenceCount.")
        }
    }
    if (passed){
        println("\t\tPassed")
    }
    println("Testing non-existing NDC code")
    val (nonexfound,nonexmedications) = currentStockOf("11111-222-33")
    if (nonexfound != StockingMessage.NOSUCHNDSCODE || nonexmedications != null){
        println("\t\tFAILED: Expected noSuchNdcCode, nil. Got $nonexfound and ${nonexmedications?.count()}")
    }
    else{
        println("\t\tpassed")
    }
    println("TESTING SOLD")

    println("\tTesting happy path")

//selling whats
    val (h2soldMessage, h2soldMedications) = sold( 5, "22222-111-00")
    if (h2soldMedications != null && h2soldMessage == StockingMessage.SUCCESS && h2soldMedications.count() == 5
        && whats.containsAll(h2soldMedications)){
        println("\t\tpassed")
    }
    else{
        println("\t\tFAILED: Expected (SUCCESS,Set<MedicationContainer>). Got ($h2soldMessage),$h2soldMedications)).")
    }

//selling stuffs
    var (h3soldMessage, h3soldMedications) = sold(1,"12333-333-99")
    if (h3soldMedications != null && h3soldMessage == StockingMessage.SUCCESS && h3soldMedications.count() == 1
        && stuffs.containsAll(h3soldMedications)){
        println("\t\tpassed")
    }
    else{
        println("\t\tFAILED: Expected (SUCCESS,Set<MedicationContainer>). Got ($h2soldMessage),$h2soldMedications)).")
    }

//selling all of the others
    val (h4soldMessage, h4soldMedications) = sold(17,"12333-344-19")
    if (h4soldMedications != null && h4soldMessage == StockingMessage.SUCCESS && h4soldMedications.count() == 17
        && others.containsAll(h4soldMedications)){
        println("\t\tpassed")
    }
    else{
        println("\t\tFAILED: Expected (SUCCESS,Set<MedicationContainer>). Got ($h4soldMessage),$h4soldMedications)).")
    }

    println("\tTesting no more left")
//trying to get more others when all have been sold
    val (nlsoldMessage, _) = sold(10,"12333-344-19")
    if (nlsoldMessage == StockingMessage.INSUFFICIENTSTOCK){
        println("\t\tpassed")
    }
    else{
        println("\t\tFAILED: Expected INSUFFICIENTSTOCK. Got $nlsoldMessage.")
    }

    println("\tTesting bad NDC code")
    val (bcsoldMessage, bcsoldMedications) = sold(10,"00000-111-222")
    if (bcsoldMessage != StockingMessage.POORLYFORMATTEDNDCCODE){
        println("\t\tFAILED: Expected (POORLYFORMATTEDNDCCODE,nil). Got ($bcsoldMessage,$bcsoldMedications).")
    }
    else{
        println("\t\tpassed")
    }

    println("\tTesting incorrectly formated NDC code")
    val (ifsoldMessage, ifsoldMedications) = sold(10,"123")
    if (ifsoldMessage != StockingMessage.POORLYFORMATTEDNDCCODE){
        println("\t\tFAILED: Expected (POORLYFORMATTEDNDCCODE,nil). Got ($ifsoldMessage,$ifsoldMedications).")
    }
    else{
        println("\t\tpassed")
    }
}