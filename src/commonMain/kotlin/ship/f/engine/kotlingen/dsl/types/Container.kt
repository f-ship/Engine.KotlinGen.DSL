package ship.f.engine.kotlingen.dsl.types

abstract class Container : Code() {
    var children: List<Code> = listOf()

    private fun replaceChild(code: Code) {
        children = children.map {
            if (it.name == code.name) { code } else it
        }
    }

    fun Code.replace() {
        replaceChild(this)
    }
}