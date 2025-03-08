package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Branch @OptIn(ExperimentalUuidApi::class) constructor(
    val arg: String? = null, // May turn into a typed string, in fact the Branch could be much more sophisticated in general
    override val name: String = "Branch",
    override val id: Uuid = Uuid.random(),
    override var children: List<Code> = listOf(),
) : Container(), Child {

}
