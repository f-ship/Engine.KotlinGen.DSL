package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class AnnotationStatement @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    override val id: Uuid = Uuid.random(),
) : Code(), Child {

}
