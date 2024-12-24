package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.TypeAlias
import ship.f.engine.kotlingen.dsl.types.ValTypedValue

interface TypeAliasInterface {
    infix fun TypeAlias(a: String): TypeAlias<Any>
    infix fun <T, R> TypeAlias<T>.assign(t: ValTypedValue<R>): TypeAlias<R>
}