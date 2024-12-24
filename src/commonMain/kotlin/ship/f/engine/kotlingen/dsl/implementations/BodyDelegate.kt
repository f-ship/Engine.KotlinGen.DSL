package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.Clazz
import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.Interface
import ship.f.engine.kotlingen.dsl.types.ValTypedValue
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class BodyDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : BodyInterface {
    override infix fun Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Class(name: String) =
        Clazz(name = name).also { addChild(it) }

    override infix fun Annotation_Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Sealed_Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Data_Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Interface(v: Bundle<Interface, Any, Unit>) =
        Interface(name = v.name).also { addChild(it) }

    override infix fun Clazz.withTypes(types: List<ValTypedValue<*>>) =
        this.copy(typeArgs = types, id = id).also { addChild(it) }

    override infix fun Clazz.constructor(args: List<Bundle<out Any, out Any, out Any>>) =
        this.copy(args = args, id = id).also { addChild(it) }

    override infix fun Clazz.extends(clazz: Clazz) =
        this.copy(superClass = clazz, id = id).also { addChild(it) }

    override infix fun Clazz.implements(int: Interface) =
        this.copy(implementedInterfaces = implementedInterfaces + listOf(Pair(int, null)), id = id)
            .also { addChild(it) }

    override infix fun Clazz.by(clazz: Clazz) = this.copy(
        implementedInterfaces = implementedInterfaces.subList(
            fromIndex = 0,
            toIndex = implementedInterfaces.lastIndex
        ) + Pair(implementedInterfaces.last().first, clazz),
        id = id,
    ).also { addChild(it) }

    override infix fun Clazz.body(block: Clazz.() -> Unit) =
        this.copy(block = block, id = id).also { addChild(it) }
}