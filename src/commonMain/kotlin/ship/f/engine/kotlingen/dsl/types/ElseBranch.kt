package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ElseBranch<R> @OptIn(ExperimentalUuidApi::class) constructor(
    var returnValue: TypedValue<R>,
    val block: ElseBranch<R>.() -> TypedValue<R>,
    val previous: If,
    override val name: String = "ElseBranch", // will remove,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
) : Else() {
    fun execute() { // TODO How to get these if blocks to run here? Definitely just include the if's itself as we need the statement
        returnValue = block()
    }
}
