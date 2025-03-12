package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.AssignedVal
import ship.f.engine.kotlingen.dsl.types.TypedBlock
import ship.f.engine.kotlingen.dsl.types.Val
import ship.f.engine.kotlingen.dsl.types.Var

interface ClassBodyInterface {
    infix fun <T> Val<T>.getter(t: TypedBlock<T>): AssignedVal<T>
    infix fun <T> Var<T>.getter(t: TypedBlock<T>): Var<T>
    infix fun <T> Var<T>.setter(t: TypedBlock<T>): Var<T>
}