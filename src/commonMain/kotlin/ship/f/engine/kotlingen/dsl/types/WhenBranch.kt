package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Child
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class WhenBranch<T, R>(
    override val name: String = "WhenBranch",
    override val id: Uuid = Uuid.random(),
    override val children: MutableList<Code> = mutableListOf(),
    val arg: TypedValue<T>? = null,
    val addChild: (Code) -> Unit = { code -> children.add(code) },
    val block: WhenBranch<T, R>.(TypedValue<T>?) -> TypedValue<R>,
    var returnValue: TypedValue<R>? = null,
) : Container(), Child,
    ValInterface by ValDelegate(children, addChild)  {
    val add get() = this

    val define get() = this.apply { children.add(Define()) }

    inline fun <reified T : Any?> p(string: String? = null): TypedArgValue<T> = TypedArgValue(
        type = T::class.simpleName,
        value = string?.let { TypedArgValue.Value.StringValue(it) }
    )

    fun execute(){
        returnValue = block(this, arg)
    }
}
