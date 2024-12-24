package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class WhenTypedValue<T : Any?>(
    override val value: Value? = null,
    override val code: Code? = null,
    override val type: String? = null,
    override val import: String? = null,
    override val nullable: Boolean = false,
    override val name: String = "WhenTypedValue",
    override val id: Uuid = Uuid.random(),
) : TypedValue<T>() {

}
