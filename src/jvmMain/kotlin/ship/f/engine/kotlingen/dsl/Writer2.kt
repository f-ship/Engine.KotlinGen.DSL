package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.util.KType

fun main() {
//    val entireFile = EntireFile("EntireFileMock")
//
//    val partialFile = PartialFile("PartialFileMock")
//    File(partialFile.name + ".kt").apply { writeText(partialFile.save()) }

    val entireFile = named EntireFile "FileA" {
        add Package "ship.f.engine.kotlingen.dsl" //DONE
        add Import "kotlin.String" //DONE

        add TypeAlias "exampleTypeAlias" assign t<List<String>>(KType("List<String>")) //Use when using concrete types can be referenced by type //DONE
        space()
        add Val "exampleVal"(t<String>())
        add Public_Val "examplePublicVal"(t<String>())
        add Internal_Val "exampleInternalVal"(t<Int>())
        add Private_Val "examplePrivateVal"(t<Int>())

        define Val "exampleVal"(t<String>())
        define Public_Val "examplePublicVal"(t<String>())
        define Internal_Val "exampleInternalVal"(t<Int>())
        define Private_Val "examplePrivateVal"(t<Int>())
        space()
        add Val "exampleAssignedVal"(t<Int>()) assign v("1")
        add Public_Val "examplePublicAssignedVal"(t<Int>()) assign v("2")
        add Internal_Val "exampleInternalAssignedVal"(t<Int>()) assign v("3")
        add Private_Val "examplePrivateAssignedVal"(t<Int>()) assign v("4")
        space()
        add Var "exampleVar"(t<Int>())
        add Public_Var "examplePublicVar"(t<Int>())
        add Internal_Var "exampleInternalVar"(t<Int>())
        add Private_Var "examplePrivateVar"(t<Int>())
        space()
        add Var "exampleAssignedVar"(t<Int>()) assign v("1")
        add Public_Var "exampleAssignedPublicVar"(t<Int>()) assign v("2")
        add Internal_Var "exampleAssignedInternalVar"(t<Int>()) assign v("3")
        add Private_Var "exampleAssignedPrivateVar"(t<Int>()) assign v("4")
        space()
        add Val "exampleGetterVal"(t<Int>()) getter v {
            v<Int>("1")
        }
        add Val "examplePublicGetterVal"(t<Int>()) getter v {
            v<Int>("2")
        }
        add Val "exampleInternalGetterVal"(t<Int>()) getter v {
            v<Int>("3")
        }
        add Val "examplePrivateGetterVal"(t<Int>()) getter v {
            v<Int>("4")
        }
        space()
        add Var "exampleGetterVar"(t<Int>()) getter v {
            v<Int>("1")
        }
        add Public_Var "examplePublicGetterVar"(t<Int>()) getter v {
            v<Int>("2")
        }
        add Internal_Var "exampleInternalGetterVar"(t<Int>()) getter v {
            v<Int>("3")
        }
        add Private_Var "examplePrivateGetterVar"(t<Int>()) getter v {
            v<Int>("4")
        }
        space()
        add Var "exampleSetterVar"(t<Int>()) setter v {
            add assign v("1")
        }
        add Public_Var "exampleSetterPublicVar"(t<Int>()) setter v {
            add assign v("2")
        }
        add Internal_Var "exampleSetterInternalVar"(t<Int>()) setter v {
            add assign v("3")
        }
        add Private_Var "exampleSetterPrivateVar"(t<Int>()) setter v {
            add assign v("4")
        }
        space()
        val baseClass = add Class "exampleBaseClass" { }
        val delegateClass = add Class "exampleDelegateClass" { }
        add Class "exampleClass" { }
        add Annotation_Class "exampleAnnotationClass" { }
        add Sealed_Class "exampleSealedClass" { }
        add Data_Class "exampleDataClass" { }

        val baseInterface = add Interface "exampleInterface" { }
        add Class "exampleClass" withTypes listOf(
            t<String>(KType("S : String")),
            t<Int>()
        ) constructor listOf(
            "val2"(t<String>(KType("S"))),
            "val3"(t<String>()),
        ) extends baseClass.withTypes(
            listOf(
                t<String>(KType("S")),
                t<Int>()
            )
        ) implements baseInterface by delegateClass body {
            add Val "exampleClassGetterVal"(t<Int>()) getter v {
                v<Int>("1")
            }
            add Val "exampleClassPublicGetterVal"(t<Int>()) getter v {
                v<Int>("2")
            }
            add Val "exampleClassInternalGetterVal"(t<Int>()) getter v {
                v<Int>("3")
            }
            add Val "exampleClassPrivateGetterVal"(t<Int>()) getter v {
                v<Int>("4")
            }
        }

        add Val "instanceOfClass"(t<Clazz>()) new baseClass withTypes listOf() constructor listOf(
            "val2"(v<String>()),
            "val3"(v<String>()),
            "val4"(v<Int>()),
        )

        val myMethod = add Fun "myMethod" withTypes listOf() constructor listOf(
            "val2"(v<String>()),
            "val3"(v<String>()),
        ) returns v<String>() block {

        }

        add Val "methodReturn"(t<String>()) assign myMethod withTypes listOf() calls listOf(
            "val2"(v<String>()),
            "val3"(v<String>()),
            "val4"(v<Int>()),
        )
        space()
        add When(wp<String>("hello")) {
            "branchArg1" {
                add Val "val1"(t<String>())
                v<String>(""""A"""")
            }
            "branchArg2" {
                add Val "val2"(t<String>())
                v<String>(""""B"""")
            }
            "branchArg2" {
                add Val "val3"(t<String>())
                v<String>(""""C"""")
            }
        }
        space()
        add When {
            "branchArgLess1" {
                add Val "val1"(t<String>())
                v<String>(""""A"""")
            }
            "branchArgLess2" {
                add Val "val2"(t<String>())
                v<String>(""""B"""")
            }
            "branchArgLess2" {
                add Val "val3"(t<String>())
                v<String>(""""C"""")
            }
        }
        space()
        add Val "when1"(t<String>()) assignWhen (wp<String>("hello")) {
            "branchArg1" {
                add Val "val1"(t<String>())
                v<String>(""""A"""")
            }
            "branchArg2" {
                add Val "val2"(t<String>())
                v<String>(""""B"""")
            }
            "branchArg2" {
                add Val "val3"(t<String>())
                v<String>(""""C"""")
            }
        }
        space()
        add Val "when2"(t<String>()) assign When {
            "branch1" {
                v<String>()
            }
        }

        If(v<Boolean>()) {
            v<Unit>()
        }.ElseIf(v<Boolean>()) {
            v<Unit>()
        }

        add Val "if1"(t<String>()) assign If(v<Boolean>(""""A" == exampleVal""")) {
            v<String>(""""AAA"""")
        }.ElseIf(v<Boolean>(""""B" == exampleVal""")) {
            v<String>(""""BBB"""")
        }.Else {
            v<String>(""""CCC"""")
        }

        For("""i in 1..10""") {
            v<String>()
        }

        While(v<Boolean>(""""W" == exampleVal""")) {
            v<String>()
        }

        DoWhile(v<Boolean>(""""D" == exampleVal""")) {
            v<String>()
        }
    }

    named PartialFile "FileB" {

    }

    entireFile.save()
//    File("$file${entireFile.name}.kt").apply { writeText(entireFile.save()) }
}

//TODO
