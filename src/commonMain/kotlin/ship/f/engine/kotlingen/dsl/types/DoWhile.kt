package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class DoWhile @OptIn(ExperimentalUuidApi::class) constructor(
    val statement: String,
    override val name: String = "DoWhile",
    override val id: Uuid = Uuid.random(),
) : Container(), Child {

}
