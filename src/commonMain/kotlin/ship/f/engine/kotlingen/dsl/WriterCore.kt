package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.Import
import ship.f.engine.kotlingen.dsl.types.Package
import ship.f.engine.kotlingen.dsl.types.Space
import ship.f.engine.kotlingen.dsl.types.TypeAlias
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.Var

class WriterCore {
    fun toCode(code: Code): String {
        return when (code) {
            is EntireFile -> {
                """
${toCode(code.packageName)}

${code.imports.map { toCode(it) }.toMultiString()}

${code.children.map { toCode(it) }.toMultiString()}
    
""".trimIndent()
            }

            is Package -> {
                """
package ${code.name}
""".trimIndent()
            }

            is Import -> {
                """
import ${code.name}
""".trimIndent()
            }

            is TypeAlias<*> -> {
                """
typealias ${code.name} $assign ${code.type?.c?.kType}
""".trimIndent()
            }

            is Val<*> -> {
                """
${code.visibility.toCode()}val ${code.name}${code.type?.c?.type.toType()}
""".trimIndent()
            }

            is AssignedVal<*> -> {
                """
${code.visibility.toCode()}val ${code.name}${code.type?.c?.type.toType()}${code.type?.value?.let { " $assign $it" } ?: ""}
""".trimIndent()
            }

            is Var<*> -> {
                """
${code.visibility.toCode()}val ${code.name}${code.type?.c?.type.toType()}${code.type?.value?.let { " $assign $it" } ?: ""}
    ${code.getter?.let { "getter(): ${code.type?.c?.type} {\n    return}" } ?: ""}
""".trimIndent()
            }

            is Space -> {
                """
                    
""".trimIndent()
            }

            else -> {
                "partial file"
            }
        }
    }

    private fun List<String>.toMultiString(transform: (String) -> String = { it }): String {
        if (isEmpty()) return ""
        return filter { it.isNotBlank() }.joinToString("\n") { transform(it) }
    }

    private fun String.indent(indent: Int = 0): String {
        val lines = lines()
        return lines.joinToString("") { "${(0..indent).joinToString { " " }}${it.trimIndent()}" }
    }

    private fun String?.toType() = this?.let{ "$typeSeparator $it" } ?: ""

    companion object {
        private const val assign = "="
        private const val typeSeparator = ":"
    }
}