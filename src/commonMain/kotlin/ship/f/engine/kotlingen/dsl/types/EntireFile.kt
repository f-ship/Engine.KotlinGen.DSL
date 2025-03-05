package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class EntireFile @OptIn(ExperimentalUuidApi::class) constructor(
    val names: String,
    override val id: Uuid = Uuid.random(),
) : KotlinFile() {

}
