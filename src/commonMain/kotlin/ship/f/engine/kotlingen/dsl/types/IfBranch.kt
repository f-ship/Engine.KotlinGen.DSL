package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class IfBranch<R> @OptIn(ExperimentalUuidApi::class) constructor(
    val statement: String,
    val statementTypedString: TypedValue<Boolean>,
    var returnType: TypedValue<R>,
    val ifBlock: IfBranch<R>.() -> TypedValue<R>, //May not make sense
    val ifBlocks: List<IfBranch<R>> = listOf(),
    override val name: String = "IfBranch",
    override val id: Uuid = Uuid.random(),
) : Container(), Child
