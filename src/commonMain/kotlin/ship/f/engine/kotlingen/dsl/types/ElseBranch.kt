package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class ElseBranch<R> @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String = "ElseBranch", // will remove,
    val returnType: TypedValue<R>,
    val ifBlocks: List<IfBranch<R>> = listOf(),
    val elseBlock: ElseBranch<R>.() -> TypedValue<R>,
    override val id: Uuid = Uuid.random(),
) : Container(), Child {
    fun execute(){ // TODO How to get these if blocks to run here? Definitely just include the if's itself as we need the statement
        ifBlocks.forEach {
            it.ifBlock(it)
        }
        elseBlock(this)
    }
}
