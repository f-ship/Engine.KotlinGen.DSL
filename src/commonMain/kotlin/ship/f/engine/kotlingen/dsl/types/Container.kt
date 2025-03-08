package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi

abstract class Container : Code() {
    abstract var children: List<Code>
    @OptIn(ExperimentalUuidApi::class)
    val uniqueChildren
        get() = children
            .reversed()
            .distinctBy { it.id }
            .reversed()

    private fun replaceChild(code: Code) {
        children = children.map {
            if (it.name == code.name) {
                code
            } else it
        }
    }

    fun Code.replace() {
        replaceChild(this)
    }
}