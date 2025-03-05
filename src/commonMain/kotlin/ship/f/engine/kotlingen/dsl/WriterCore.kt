package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.ElseBranch
import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.Import
import ship.f.engine.kotlingen.dsl.types.Package
import ship.f.engine.kotlingen.dsl.types.Space
import ship.f.engine.kotlingen.dsl.types.TypeAlias
import ship.f.engine.kotlingen.dsl.types.TypedBlock
import ship.f.engine.kotlingen.dsl.types.TypedValue
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.Var
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class WriterCore {
    fun toCode(code: Code, indent: Int = 0): String {
        return when (code) {
            is EntireFile ->
                """
                |${toCode(code.packageName)}
                |
                |${code.imports.map { toCode(it) }.toMultiString()}
                |
                |${
                    code.uniqueChildren
                        .map { toCode(it) }
                        .toMultiString()
                }
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
                |${indent()}${code.visibility.toCode()}val ${code.name}${code.type?.type.toType()}${code.type?.value?.let { " $assign ${it.toCode()}" } ?: ""}${code.type?.code?.let { " $assign ${toCode(it)}" } ?: ""}
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
                |//
                |// 
                |//
                """

            is TypedBlock<*> -> code.copy().apply {
                execute()
            }.let {
                """
                |${indent()}${it.definition}(${it.args.toCode()}): ${it.returnValue?.toCode()} {
                |${indent()}    ${it.children.map { child -> toCode(child) }.toMultiString()}
                |${indent()}    return ${it.returnValue?.value.toCode()}
                |${indent()}}
                """
            }


            is Clazz -> code.copy().apply {
                execute()
            }.let {
                """
                |${indent()}class ${code.name}${if (code.args.isNotEmpty()) "(${code.args.toCode()})" else ""}${if (it.children.isNotEmpty()) " {" else ""}
                |${indent()}${if (it.children.isNotEmpty()) it.uniqueChildren.map { child -> toCode(child,4) }.toMultiString() else removeLine}
                |${indent()}${if (it.children.isNotEmpty()) "}" else removeLine}
                """
            }

//            is IfBranch<*> ->
//                """
//                |${indent()}if (${code.statementTypedString.value}) {
//                |${indent()}${toCode(code.ifBlock,indent + 4)}
//                |${indent()}}
//                """

            is ElseBranch<*> ->
                """
                |${indent()}if (${code.ifBlocks.first().statementTypedString.value.toCode()}) {
                |${indent()}
                |${indent()}}${"d"}
                """

            else -> {
                "partial file"
            }
        }.removeWhitespace()
    }

    private fun TypedValue.Value?.toCode() = when(this){
        is TypedValue.Value.CodeValue -> toCode(value)
        is TypedValue.Value.StringValue -> value
        null -> ""
    }

    private fun List<Bundle<out Any, out Any>>.toCode() = joinToString(", ") { arg -> "${arg.name}${arg.type?.type.toType()}" }
    private fun TypedValue<*>?.toCode() = this?.let { it.type ?: "" }

    private fun List<String>.toMultiString(transform: (String) -> String = { it }): String {
        if (isEmpty()) return removeLine
        return filter { it.isNotBlank() }.joinToString("\n") { transform(it) }
    }

    private fun String.indent(indent: Int = 0): String {
        val lines = lines()
        return lines.joinToString("") { "${(0..indent).joinToString { " " }}${it.trimIndent()}" }
    }

    private operator fun Int.invoke() = (0..this).joinToString("") { " " }

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