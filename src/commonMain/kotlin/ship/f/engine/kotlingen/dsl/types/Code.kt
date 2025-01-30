package ship.f.engine.kotlingen.dsl.types

abstract class Code {
    abstract val name: String
    //    abstract var indent: Int
    sealed class Visibility {
        data object Public : Visibility()
        data object Internal : Visibility()
        data object Private : Visibility()
        data object Protected : Visibility()
        data object Unspecified : Visibility()
        fun toCode() = if (this is Unspecified) "" else "${this.toString().lowercase()} "
    }
}