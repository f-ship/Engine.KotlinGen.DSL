package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.implementations.BodyDelegate
import ship.f.engine.kotlingen.dsl.implementations.BodyInterface
import ship.f.engine.kotlingen.dsl.implementations.ClassBodyDelegate
import ship.f.engine.kotlingen.dsl.implementations.ClassBodyInterface
import ship.f.engine.kotlingen.dsl.implementations.ControlFlowDelegate
import ship.f.engine.kotlingen.dsl.implementations.ControlFlowInterface
import ship.f.engine.kotlingen.dsl.implementations.FileDelegate
import ship.f.engine.kotlingen.dsl.implementations.FileInterface
import ship.f.engine.kotlingen.dsl.implementations.MethodDelegate
import ship.f.engine.kotlingen.dsl.implementations.MethodInterface
import ship.f.engine.kotlingen.dsl.implementations.SpaceDelegate
import ship.f.engine.kotlingen.dsl.implementations.SpaceInterface
import ship.f.engine.kotlingen.dsl.implementations.TypeAliasDelegate
import ship.f.engine.kotlingen.dsl.implementations.TypeAliasInterface
import ship.f.engine.kotlingen.dsl.implementations.ValDelegate
import ship.f.engine.kotlingen.dsl.implementations.ValInterface
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
abstract class KotlinFile(
    override var children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : Container(),
    TypeAliasInterface by TypeAliasDelegate(children, addChild),
    ValInterface by ValDelegate(children, addChild),
    BodyInterface by BodyDelegate(children, addChild),
    SpaceInterface by SpaceDelegate(children, addChild),
    FileInterface by FileDelegate(children, addChild),
    ControlFlowInterface by ControlFlowDelegate(children, addChild),
    ClassBodyInterface by ClassBodyDelegate(children, addChild),
    MethodInterface by MethodDelegate(children, addChild) {
    override val add get() = this
    override val define get() = this.apply { children.add(Define()) }
    override fun execute() = this
}
