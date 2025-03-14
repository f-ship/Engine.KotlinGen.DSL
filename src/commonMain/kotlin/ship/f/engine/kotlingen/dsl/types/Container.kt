package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
@OptIn(ExperimentalUuidApi::class)
abstract class Container : Code() {
    abstract val children: MutableList<Code>

    val uniqueChildren
        get() = children
            .reversed()
            .distinctBy { it.id }
            .reversed()
            .filterNotMultiple(filterCount = 2) { it is Define }
            .let {
                val importIndex = it.indexOfLast { child -> child is Import }
                if (importIndex != -1) it.subList(0, importIndex + 1) + listOf(Space()) + it.subList(importIndex + 1, it.size)
                else it
            }

    private fun <T> List<T>.filterNotMultiple(filterCount: Int = 1, predicate: (a: T) -> Boolean, ): List<T> {
        var newList: List<T> = listOf()
        var index = 0
        while (index < size) {
            if (predicate(get(index))) {
                index += filterCount
            } else {
                newList = newList.plus(get(index)).also { index++ }
            }
        }
        return newList
    }

    abstract val add: Any
    abstract val define: Any
    abstract fun execute(): Any
}
