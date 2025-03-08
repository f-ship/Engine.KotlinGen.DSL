package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.Child
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Internal
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Private
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Public
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Clazz @OptIn(ExperimentalUuidApi::class) constructor(
    override val name: String,
    val isData: Boolean = false,
    val isSealed: Boolean = false,
    val isOpen: Boolean = false,
    val visibility: Visibility = Visibility.Unspecified,
    val block: Clazz.() -> Unit = { }, //May not make sense
    val typeArgs: List<TypedValue<*>> = listOf(),
    val args: List<Bundle<out Any, out Any>> = listOf(),
    val definition: String = "{",
    val superClass: Clazz? = null,
    val implementedInterfaces: List<Pair<Interface, Clazz?>> = listOf(),
    override val id: Uuid = Uuid.random(),
    override val children: MutableList<Code> = mutableListOf(),
) : Container(), Child {

     val add
        get() = this

    val define
        get() = this

    fun execute(){
        block.invoke(this)
    }

    private fun addChild(child: Code) {
        children.add(child)
    }

    @OptIn(ExperimentalUuidApi::class)
    infix fun <T : Any?> Clazz.Val(v: Bundle<Val<T>.() -> Unit, T>) = Val(name = v.name, type = v.type).also { addChild(it) }
    @OptIn(ExperimentalUuidApi::class)
    infix fun <T : Any?> Clazz.Public_Val(v: Bundle<Val<T>.() -> Unit, T>) = Val(name = v.name, type = v.type, visibility = Public).also { addChild(it) }
    @OptIn(ExperimentalUuidApi::class)
    infix fun <T : Any?> Clazz.Internal_Val(v: Bundle<Val<T>.() -> Unit, T>) = Val(name = v.name, type = v.type, visibility = Internal).also { addChild(it) }
    @OptIn(ExperimentalUuidApi::class)
    infix fun <T : Any?> Clazz.Private_Val(v: Bundle<Val<T>.() -> Unit, T>) = Val(name = v.name, type = v.type, visibility = Private).also { addChild(it) }

    @OptIn(ExperimentalUuidApi::class)
    infix fun <T> Val<T>.getter(t: TypedBlock<T>) =
        AssignedVal(name = name, getter = t.copy(definition = "getter"), id = id).also { addChild(it) }
}