package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.Interface
import ship.f.engine.kotlingen.dsl.types.ValTypedValue

interface BodyInterface {
    infix fun Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Class(name: String): Clazz
    infix fun Annotation_Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Sealed_Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Data_Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Interface(v: Bundle<Interface, Any, Unit>): Interface
    infix fun Clazz.withTypes(types: List<ValTypedValue<*>>): Clazz
    infix fun Clazz.constructor(args: List<Bundle<out Any, out Any, out Any>>): Clazz
    infix fun Clazz.extends(clazz: Clazz): Clazz
    infix fun Clazz.implements(int: Interface): Clazz
    infix fun Clazz.by(clazz: Clazz): Clazz
    infix fun Clazz.body(block: Clazz.() -> Unit): Clazz
}
