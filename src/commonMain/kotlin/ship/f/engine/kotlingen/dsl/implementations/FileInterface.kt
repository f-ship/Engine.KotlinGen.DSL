package ship.f.engine.kotlingen.dsl.implementations

import ship.f.engine.kotlingen.dsl.types.Import
import ship.f.engine.kotlingen.dsl.types.Package

interface FileInterface {
    infix fun Package(i: String): Package
    infix fun Import(i: String): Import
}