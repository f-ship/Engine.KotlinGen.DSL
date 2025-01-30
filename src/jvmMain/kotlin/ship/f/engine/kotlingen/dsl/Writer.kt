package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.invoke
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.KotlinFile
import ship.f.engine.kotlingen.dsl.types.KotlinFile.KType
import java.io.File

fun main() {
//    val entireFile = EntireFile("EntireFileMock")
//
//    val partialFile = PartialFile("PartialFileMock")
//    File(partialFile.name + ".kt").apply { writeText(partialFile.save()) }

    val entireFile = named EntireFile "FileA" {
        add Package "ship.f.engine.kotlingen.dsl" //DONE
        add Import "kotlin.String" //DONE
//        named TypeAlias "exampleTypeAlias" assign "List<String>" //Use when using generated types that can only be referenced by string
        add TypeAlias "exampleTypeAlias" assign t<List<String>>(KType("List<String>")) //Use when using concrete types can be referenced by type //DONE

//        named Val "exampleVal"(String::class) // Probably don't want to use this either
        add Val "exampleVal1"(t<String>()) // Will probably need dynamic type referencing as well
        add Public_Val "examplePublicVal1"(t<String>()) // Will probably need dynamic type referencing as well
//        named Public_Val "exampleInternalVal"(Int::class) // Will probably not do this
        add Internal_Val "exampleInternalVal"(t<Int>())
//        named Private_Val "exampleInternalVal"(Int::class) // Will probably not do this
        add Val "exampleVal2"(t<Int>()) assign t<Int>("1")
        add Private_Val "examplePrivateVal"(t<Int?>()) assign t("2")

//        named Var "exampleVar"(String::class) // Will probably not do this
//        named Public_Var "exampleInternalVar"(Int::class) // Will probably not do this
        add Internal_Var "exampleInternalVar"(t<Int>())
//        named Private_Var "exampleInternalVar"(Int::class) // Will probably not do this
        add Var "exampleVar"(t<Int>()) assign t<Int>("3")

//        named Public_Val "exampleInternalVar"(Int::class) getter t<KClass<Int>> { t<KClass<Int>>() } will probably not do this
        add Internal_Var "exampleInternalVar"(t<Int>()) getter t<Int> { t<Int>("4") }
        add Internal_Val "exampleInternalVar"(t<Int>()) setter t<Int> { named assign t<Int>("5") } // I don't think a Val should have a setter
        add Internal_Var "exampleInternalVar"(t<Int>()) setter t<Int> { named assign t<Int>("6") }

        val baseClass = add Class "exampleBaseClass" {}
        val baseInterface = add Interface "exampleInterface" {}

        add Annotation_Class "exampleAnnotationClass" { }
        add Sealed_Class "exampleSealedClass" { }
        add Data_Class "exampleDataClass" { }

        add Class "exampleClass" withTypes listOf() constructor listOf(
            "val2"(String::class),
            "val3"(String::class),
        ) extends baseClass by baseInterface implements baseInterface body {

        }

        add Class "exampleBaseClass" {

        }

        add Val "instanceOfClass"(t<Clazz>()) new baseClass withTypes listOf() constructor listOf(
            "val2"(String::class),
            "val3"(String::class),
            "val4"(t<Int>()),
        )

        val myMethod = add Fun "myMethod" withTypes listOf() constructor listOf(
            "val2"(String::class),
            "val3"(String::class),
        ) returns t<String>() block {

        }

        add Val "methodReturn"(t<String>()) assign myMethod withTypes listOf() calls listOf(
            "val2"(String::class),
            "val3"(String::class),
            "val4"(t<Int>()),
        )

        When {
            "branch1" {
                t<String>()
            }
        }

        add Val "when1"(t<String>()) assign When(t<String>("hello")) {
            "branch1" {
                t<String>()
            }
        }

        add Val "when2"(t<String>()) assign When {
            "branch1" {
                t<String>()
            }
        }

        If(t<Boolean>()) {
            t<String>()
        }

        add Val "if1"(t<String>()) assign If(t<Boolean>()) {
            t<String>()
        }.ElseIf(t<Boolean>()) {
            t<String>()
        }.Else {
            t<String>()
        }

        For(t<Boolean>()) {
            t<String>()
        }

        While(t<Boolean>()) {
            t<String>()
        }

        DoWhile(t<Boolean>()) {
            t<String>()
        }
    }

    named PartialFile "FileB" {

    }

    File("$file${entireFile.name}.kt").apply { writeText(entireFile.save()) }
}

const val file = "Engine/KotlinGen/DSL/src/commonMain/kotlin/ship/f/engine/kotlingen/dsl/"

//TODO
