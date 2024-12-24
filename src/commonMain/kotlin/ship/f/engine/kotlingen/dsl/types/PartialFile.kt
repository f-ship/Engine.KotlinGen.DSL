package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class PartialFile @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    override val id: Uuid = Uuid.random(),
    override var children: MutableList<Code> = mutableListOf(),
    val block: (PartialFile.(Any) -> Unit)?,
) : KotlinFile() {

}