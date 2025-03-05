package ship.f.engine.kotlingen.dsl.types

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

abstract class Code {
    abstract val name: String
    @OptIn(ExperimentalUuidApi::class)
    abstract val id: Uuid
    sealed class Visibility {
        data object Public : Visibility()
        data object Internal : Visibility()
        data object Private : Visibility()
        data object Protected : Visibility()
        data object Unspecified : Visibility()
        fun toCode() = if (this is Unspecified) "" else "${this.toString().lowercase()} "
    }
}