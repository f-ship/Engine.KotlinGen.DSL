// KotlinFile names must be string references so they can be dynamically generated, same thing goes for anything that needs to by dynamically named
// named EntireFile "FileA" { } DONE
// named PartialFile "FileB" { } DONE

// Current
// Import("kotlin.String") IMPROVED

// Potential
// named Import "kotlin.String" DONE

// Current
// named TypeAlias "exampleTypeAlias"(String) // Can I do better here? IMPROVED

// Potential
// named TypeAlias "exampleTypeAlias" assign "String" DONE
// named TypeAlias "exampleTypeAlias" assign t<List<String>> DONE

// Current
// named Val "exampleVal"(String::class) DONE

// Potential
// named Val "exampleVal" t<String>() DONE
// named Val "exampleAssignedVal"("String").nullable() Tag "example val assign t<String>(""""Party time""""") WILL NOT DO

// Current
// named Protected_Val "exampleProtectedVal"(Int::claas) assign t<Int>("55")
// named Public_Val "examplePublicVal"(Int::class) assign t<Int>("55") DONE
// named Private_Val "examplePrivateVal"(Int::class) assign t<Int>("55") DONE
// named Internal_Val "exampleInternalVal"(Int::class) assign t<Int>("55") DONE

// Potential Not Currently Understood to be Possible
// named Protected Val "exampleProtectedVal"(Int::class) assign t<Int>("55")
// named Public Val "examplePublicVal"(Int::class) assign t<Int>("55")
// named Private Val "examplePrivateVal"(Int::class) assign t<Int>("55")
// named Internal Val "exampleInternalVal"(Int::class) assign t<Int>("55")

// Current
// named Protected_Var "exampleProtectedVar"(Int::claas) assign t<Int>("55")
// named Public_Var "examplePublicVar"(Int::class) assign t<Int>("55") DONE
// named Private_Var "examplePrivateVar"(Int::class) assign t<Int>("55") DONE
// named Internal_Var "exampleInternalVar"(Int::class) assign t<Int>("55") DONE

// Potential Not Currently Understood to be Possible
// named Protected Var "exampleProtectedVar"(Int::class) assign t<Int>("55")
// named Public Var "examplePublicVar"(Int::class) assign t<Int>("55")
// named Private Var "examplePrivateVar"(Int::class) assign t<Int>("55")
// named Internal Var "exampleInternalVar"(Int::class) assign t<Int>("55")

// Current
// named Open_Protected Var "exampleProtectedVar"(Int::class) assign t<Int>("55")
// named Override_Var "examplePrivateVar"(Int::class) assign t<Int>("55")
// named Abstract_Public_Var "examplePublicVar"(Int::class)

// Potential Not Currently Understood to be Possible
// named Open Protected Var "exampleProtectedVar"(Int::class) assign t<Int>("55")
// named Override Var "examplePrivateVar"(Int::class) assign t<Int>("55")
// named Abstract Public Var "examplePublicVar"(Int::class)

// Current
// named Protected_Var "exampleProtectedVar"(Int::class) getter { t<Int>("55") } DONE
// named Public_Var "examplePublicVar"(Int::class) getter { t<Int>("55") } DONE
// named Private_Var "examplePrivateVar"(Int::class) setter { value assigns t<Int>("55") } DONE
// named Internal_Var "exampleInternalVar"(Int::class) setter { value assigns t<Int>("55") } DONE

// Potential Not Currently Understood to be Possible
// named Protected Var "exampleProtectedVar"(Int::class) getter { t<Int>("55") }
// named Public Var "examplePublicVar"(Int::class) getter { t<Int>("55") }
// named Private Var "examplePrivateVar"(Int::class) setter { value assigns t<Int>("55") }
// named Internal Var "exampleInternalVar"(Int::class) setter { value assigns t<Int>("55") }

// Potential
// named Class "exampleClass" withTypes() (
//    "val2"(String::class),
//    "val3"(String::class),
// ) extends "exampleBaseClass" interface "exampleInterface" {
//
// }

// Current DONE
// named Class "exampleClass" withTypes listOf() constructor listOf(
//    "val2"(String::class),
//    "val3"(String::class),
// ) extends baseClass by baseInterface implements baseInterface body {
//
// }

// Potential DONE
// named Val "instanceOfClass"(t<Clazz>()) new baseClass withTypes listOf() constructor listOf(
//     "val2"(String::class),
//     "val3"(String::class),
//     "val4"(t<Int>()),
// )
//

// Potential cannot do as of now
// named Annotation Class "exampleAnnotationClass" { }
// named Sealed Class "exampleSealedClass" { }
// named Data Class "exampleDataClass" { }
// named Object "exampleObject" { }
// named Interface "exampleInterface" { }

// Current DONE
// named Annotation_Class "exampleAnnotationClass" { }
// named Sealed_Class "exampleSealedClass" { }
// named Data_Class "exampleDataClass" { }

// Current
// named Fun "MockFun"(
//    "A"(Nothing::class isTypeArg = true),
//    ""(Int::class, isReturnArg = true),
//    "val2"(String::class),
//    "val3"(String::class),
// ) {
//
// }

// Potential... Not sure I want to do this as it adds more complexity than is worth,
// we should stick to coding like we are in a javascript environment with help to avoid silly mistakes
// class StrictFun(
//     valA: String,
//     valB: String,
//     valC: String,
// ) : FunDefinition<String>() {
//
// }
//

// Current DONE
// named Fun "myMethod" withTypes listOf() constructor listOf(
//    "val2"(String::class),
//    "val3"(String::class),
// ) returns t<String>() block {
//
// }

// Potential WONT DO
// named withTypes(vararg types: Type) Fun "MockFun"(
//    "A"(Nothing::class isTypeArg = true),
//    "val2"(String::class),
//    "val3"(String::class),
// ) returns t<String>(""""Party time"""") {
//
// }

// Potential WONT DO
// named Val "exampleFuncVal" assign Call "MockFun" withTypes() (
//    "val2"(String::class),
//    "val3"(String::class),
// )

// DONE
// When { 
//     "1" Branch {
//
//     }
// }

// DONE
// Potential
// When(t<String>("hello")) { // type in constructor must be the same as first arg in Branch
//     "1" Branch {
//
//      }
// }

// DONE
// Potential
// ... assigns/returns When(t<String>("hello")) { // additionally when assign is used, the When becomes typed and each branch must return a typed string.
//     "1" Branch {
//
//      }
// }

// Current Improved
// If(""""I like cheese"""") {
//
// }.ElseIf(""""I like cheese"""") {
//
// }.Else {
//
// }

// ElseIf can't be an infix, oh well the dot notation is good enough
// Potential A
// If(""""I like cheese"""") {
//
// } ElseIf(""""""""") {
//
// } Else {
//
// }
//

// Potential B DONE
// If(t<Boolean>(""""")) {
//
// } ElseIf(t<Boolean>(""""")) {
//
// } Else {
//
// }
//

// DONE
// For("""statement""") { // potentially make this an iterator of some sort
//
// }

// DONE
// While("""statement""") {
//
// }

// DONE
// DoWhile("""statement""") {
//
// }

// Current
// named Protected_Val "exampleProtectedVal"(Int::claas) assign t<Int>("55")

// Potential Not Currently Understood to be Possible
// named Protected Val "exampleProtectedVal"(Int::class) assign t<Int>("55")

// Current
// named Protected_Var "exampleProtectedVar"(Int::claas) assign t<Int>("55")

// Current
// named Open_Protected Var "exampleProtectedVar"(Int::class) assign t<Int>("55")
// named Override_Var "examplePrivateVar"(Int::class) assign t<Int>("55")
// named Abstract_Public_Var "examplePublicVar"(Int::class)

Refactor effort
1) Clean up the Writer file to include a proper example of each thing I am trying to demonstrate DONE
2) Clean up the arguments for each of the types DONE
3) Experiment with delegation to see if we can reuse methods CANCEL
4) Find a better solution for dealing with replace children and addChildren DONE
5) Look into differentiating between using a typed string as a type and a typed string as a value, maybe use v for the value DONE
6) actually implement the difference between add and define DONE
7) Make vals addable even if they are not assigned DONE
8) Break up the Kotlin File into definable things DONE
9) Factor Bundle out by replacing things with T CANCEL (Don't think I can remove bundle)
10) Improve KType to string/type CANCEL (Don't think I can reliably rely on reflection)
11) Create a system for automatic imports using declared types POSTPONE
12) Create a base lib that includes a lot of common imports POSTPONE

----------------------------------------------------------------------------------------------------

1) Break up the Kotlin File into definable things DONE
2) Actually implement the difference between add and define DONE

----------------------------------------------------------------------------------------------------
Complete WriterCore for all currently defined methods
1) MONDAY Get intent working DONE
2) TUESDAY Create Typed strategy for typed block and values a sealed class DONE
3) WEDNESDAY Complete If IfElse Else, Create a better id naming system DONE
   ID a) create a random id function, b) add to all classes as default, c) share id between code DONE
4) THURSDAY Complete For DONE
5) FRIDAY Complete While DONE
6) SATURDAY Complete DoWhile DONE
7) SUNDAY Complete Class block + definition
   Definition
   Visibility
   Sealed/Data
   Block Methods POSTPONE TO after ALPHA-1

----------------------------------------------------------------------------------------------------