package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.PartialFile
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun EntireFile.save(): String {
    val cache = copy()
    block?.let { cache.it(this) }
    return WriterCore().toCode(cache).apply { println(this) }
}

@OptIn(ExperimentalUuidApi::class)
fun PartialFile.save(): String {
    val cache = copy()
    return WriterCore().toCode(cache).apply { println(this) }
}
