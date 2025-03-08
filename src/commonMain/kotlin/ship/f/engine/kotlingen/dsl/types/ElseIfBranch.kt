package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ElseIfBranch<R> @OptIn(ExperimentalUuidApi::class) constructor(
    val statement: TypedValue<Boolean>,
    var returnValue: TypedValue<R>,
    val block: ElseIfBranch<R>.() -> TypedValue<R>, //May not make sense
    val previous: If,
    override val name: String = "ElseIfBranch",
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : If() {
    fun execute() {
        returnValue = block()
    }
}
