package ship.f.engine.kotlingen.dsl.types

data class PartialFile(
    val names: String
) : KotlinFile() {
    val named = this
}