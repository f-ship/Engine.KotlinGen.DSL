package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.getRandomString

data class Space(
    override val name: String = getRandomString()
) : Code() {

}