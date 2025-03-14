package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Branch(
    val arg: String? = null, // May turn into a typed string, in fact the Branch could be much more sophisticated in general
    override val name: String = "Branch",
    override val id: Uuid = Uuid.random(),
    override val children: MutableList<Code> = mutableListOf(),
) : Container(), Child {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this
}
