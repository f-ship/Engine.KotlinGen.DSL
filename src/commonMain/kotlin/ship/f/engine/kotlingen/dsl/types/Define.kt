package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.getRandomString
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Define @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String = getRandomString(),
    override val id: Uuid = Uuid.random(),
) : Code() {

}