package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Annotation @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    override val id: Uuid = Uuid.random(),
    override val children: MutableList<Code> = mutableListOf(),
) : Container(), Child {

}
