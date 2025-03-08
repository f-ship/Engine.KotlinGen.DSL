package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class IfBranch<R> @OptIn(ExperimentalUuidApi::class) constructor(
    val statement: TypedValue<Boolean>,
    var returnValue: TypedValue<R>,
    val block: IfBranch<R>.() -> TypedValue<R>, //May not make sense
    override val name: String = "IfBranch",
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : If() {
    fun execute() {
        returnValue = block()
    }
}
