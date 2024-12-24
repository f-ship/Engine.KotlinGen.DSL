package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.DoWhile
import ship.f.engine.kotlingen.dsl.types.ElseBranch
import ship.f.engine.kotlingen.dsl.types.ElseIfBranch
import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.For
import ship.f.engine.kotlingen.dsl.types.IfBranch
import ship.f.engine.kotlingen.dsl.types.Import
import ship.f.engine.kotlingen.dsl.types.Package
import ship.f.engine.kotlingen.dsl.types.Space
import ship.f.engine.kotlingen.dsl.types.TypeAlias
import ship.f.engine.kotlingen.dsl.types.TypedBlock
import ship.f.engine.kotlingen.dsl.types.TypedValue
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import ship.f.engine.kotlingen.dsl.types.Var
import ship.f.engine.kotlingen.dsl.types.When
import ship.f.engine.kotlingen.dsl.types.WhenBranch
import ship.f.engine.kotlingen.dsl.types.While
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class WriterCore {
    fun toCode(code: Code, indent: Int = 0): String {
        return when (code) {
            is EntireFile ->
                """
                |${code.uniqueChildren.map { toCode(it) }.toMultiString()}
                """

            is Package ->
                """
                |package ${code.name}
                """

            is Import -> {
                """
                |import ${code.name}
                """
            }

            is TypeAlias<*> ->
                """
                |typealias ${code.name} $assign ${code.type?.type}
                """

            is Val<*> ->
                """
                |${indent()}${code.visibility.toCode()}val ${code.name}${code.type?.type.toType()}
                """

            is AssignedVal<*> ->
                """
                |${indent()}${code.visibility.toCode()}val ${code.name}${code.type?.type.toType()}${code.type?.value?.let { " $assign ${it.toCode()}" } ?: ""}
                |${code.getter?.let { toCode(it,indent + 4) } ?: removeLine}
                |"""

            is Var<*> ->
                """
                |${indent()}${code.visibility.toCode()}var ${code.name}${code.type?.type.toType()}${code.type?.value?.let { " $assign ${it.toCode()}" } ?: ""}
                |${code.getter?.let { toCode(it,indent + 4) } ?: removeLine}
                |${code.setter?.let { toCode(it,indent + 4) } ?: removeLine}
                """

            is Space ->
                """
                ||     
                """

            is TypedBlock<*> -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}${definition}(${args.toCode()}): ${returnValue?.toCode()} {
                |${indent()}    ${children.map { child -> toCode(child) }.toMultiString()}
                |${indent()}    return ${returnValue?.value.toCode()}
                |${indent()}}
                """
            }


            is Clazz -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}class ${name}${toTypeArgs()}${toArgs()}${toFirstSeparator()}${toSuperClass()}${toSecondSeparator() }${toInterfaces()}${toBodyStart()}
                |${indent()}${toBody()}
                |${indent()}${toBodyEnd()}
                """
            }

            is IfBranch<*> -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}if(${statement.value.toCode()}) {
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent()}    ${returnValue?.value?.toCode()}
                |${indent()}}
                """
            }

            is ElseIfBranch<*> -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}${toCode(previous)} else if(${statement.value.toCode()}) {
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent()}    ${returnValue?.value?.toCode()}
                |${indent()}}
                """
            }

            is ElseBranch<*> -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}${toCode(previous)} else {
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent(4)}${returnValue?.value?.toCode()}
                |${indent()}}
                """
            }

            is For -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}for(${statement}){
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent()}}
                """
            }

            is While -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}while(${statement.value.toCode()}){
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent()}}
                """
            }

            is DoWhile -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}do {
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent()}} while (${statement.value.toCode()})
                """
            }

            is When<*,*> -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}when${arg?.value?.toCode()?.let { arg -> "($arg)" } ?: ""} {
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent()}}
                """
            }

            is WhenBranch<*, *> -> code.copy().apply {
                execute()
            }.run {
                """
                |${indent()}${name} -> {
                |${indent()}${uniqueChildren.map { child -> toCode(child,4) }.toMultiString()}
                |${indent(4)}${returnValue?.value?.toCode() ?: removeLine}
                |${indent()}}
                """
            }

            else -> {
                "partial file"
            }
        }.removeWhitespace()
    }

    private fun Clazz.toTypeArgs() = if (typeArgs.isNotEmpty()) "<${typeArgs.map { arg -> arg.type ?: "" }.toSingleString(", ")}>" else ""
    private fun Clazz.toArgs(empty: String = "") = if (args.isNotEmpty()) "(${args.map { arg -> "${arg.name}: ${arg.type?.type}" }.toSingleString(", ")})" else empty
    private fun Clazz.toFirstSeparator() = if (superClass != null || implementedInterfaces.isNotEmpty()) " : " else ""
    private fun Clazz.toSecondSeparator() = if (superClass != null && implementedInterfaces.isNotEmpty()) ", " else ""
    private fun Clazz.toSuperClass() = superClass?.run { "${name}${toTypeArgs()}${toArgs("()")}" } ?: ""
    private fun Clazz.toInterfaces() = implementedInterfaces.map { int -> "${int.first.name}${int.second?.let{ by -> " by ${by.name}()" } ?: ""}" }.toSingleString(", ")
    private fun Clazz.toBodyStart() = if (children.isNotEmpty()) " {" else ""
    private fun Clazz.toBody() = if (children.isNotEmpty()) uniqueChildren.map { child -> toCode(child,4) }.toMultiString() else removeLine
    private fun Clazz.toBodyEnd() = if (children.isNotEmpty()) "}" else removeLine

    private fun TypedValue.Value?.toCode() = when(this){
        is TypedValue.Value.CodeValue -> toCode(value)
        is TypedValue.Value.StringValue -> value
        null -> ""
    }

    private fun List<Bundle<out Any, out Any, out Any>>.toCode() = joinToString(", ") { arg -> "${arg.name}${arg.type?.type.toType()}" }
    private fun ValTypedValue<*>?.toCode() = this?.let { it.type ?: "" }

    private fun List<String>.toMultiString(transform: (String) -> String = { it }): String {
        if (isEmpty()) return removeLine
        return filter { it.isNotBlank() }.joinToString("\n") { transform(it) }
    }

    private fun List<String>.toSingleString(separator: String = "", transform: (String) -> String = { it }): String {
        if (isEmpty()) return removeLine
        return filter { it.isNotBlank() }.joinToString(separator) { transform(it) }
    }

    private fun String.indent(indent: Int = 0): String {
        val lines = lines()
        return lines.joinToString("") { "${(0..indent).joinToString { "I" }}${it.trimIndent()}" }
    }

    private operator fun Int.invoke(more: Int = 0) = (0 until this + more).joinToString("") { " " }

    private fun String.removeWhitespace(): String =
        trimMargin()
            .trimEnd()
            .split("\n")
            .filter { !it.contains(removeLine) }
            .joinToString("\n")


    private fun <T> List<T>.preFilter(predicate: (a: T, b: T) -> Boolean): List<T> {
        var newList: List<T> = if (isNotEmpty()) listOf(get(0)) else listOf()
        for (i in 1 until size) {
            if (predicate(get(i - 1), get(i))) newList = newList.plus(get(i))
        }
        return newList
    }

    private fun <T> List<T>.preFilters(predicate: List<(a: T, b: T) -> Boolean>): List<T> {
        var newList: List<T> = if (isNotEmpty()) listOf(get(0)) else listOf()
        for (i in 1 until size) {
            if (predicate.map { it(get(i - 1), get(i)) }.all { it }) newList = newList.plus(get(i))
        }
        return newList
    }

    private fun String?.toType() = this?.let { "$typeSeparator $it" } ?: ""

    companion object {
        private const val assign = "="
        private const val typeSeparator = ":"
        private const val removeLine = "£$%REMOVE£$%"
    }
}