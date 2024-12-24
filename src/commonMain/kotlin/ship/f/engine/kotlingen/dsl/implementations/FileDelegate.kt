package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.Code
import ship.f.engine.kotlingen.dsl.types.Import
import ship.f.engine.kotlingen.dsl.types.Package
import ship.f.engine.kotlingen.dsl.types.Space
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class FileDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : FileInterface {
    override infix fun Package(i: String) = Package(name = i).also {
        addChild(it)
        addChild(Space())
    }

    override infix fun Import(i: String) = Import(name = i).also {
        addChild(it)
    }
}