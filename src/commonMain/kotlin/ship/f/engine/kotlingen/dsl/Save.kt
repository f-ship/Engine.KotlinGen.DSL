package ship.f.engine.kotlingen.dsl

import ship.f.engine.kotlingen.dsl.types.EntireFile
import ship.f.engine.kotlingen.dsl.types.PartialFile

fun EntireFile.save(): String {
    val cache = copy()
    cache.block()
    return WriterCore().toCode(cache).apply { println(this) }
}

fun PartialFile.save(): String {
    val cache = copy()
    return WriterCore().toCode(cache).apply { println(this) }
}
