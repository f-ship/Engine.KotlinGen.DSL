package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Internal
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Private
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Public
import ship.f.engine.kotlingen.dsl.types.TypedValue.Value.CodeValue
import ship.f.engine.kotlingen.dsl.types.TypedValue.Value.StringValue
import ship.f.engine.kotlingen.dsl.types.util.KType
import kotlin.uuid.ExperimentalUuidApi

interface TypeAliasInterface {
    infix fun TypeAlias(a: String): TypeAlias<Any>
    infix fun <T, R> TypeAlias<T>.assign(t: TypedValue<R>): TypeAlias<R>
}

@OptIn(ExperimentalUuidApi::class)
class TypeAliasDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : TypeAliasInterface {
    override infix fun TypeAlias(a: String) = TypeAlias<Any>(name = a)
    override infix fun <T, R> TypeAlias<T>.assign(t: TypedValue<R>) =
        TypeAlias(name = name, type = t).also { addChild(it) }
}

interface ValInterface {
    infix fun <T : Any?> Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Public_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Internal_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Private_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>): Val<T>
    infix fun <T : Any?> Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T : Any?> Public_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T : Any?> Internal_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
    infix fun <T : Any?> Private_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>): Var<T>
}

@OptIn(ExperimentalUuidApi::class)
class ValDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : ValInterface {
    override infix fun <T> Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type).also { addChild(it) }

    override infix fun <T> Public_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type, visibility = Public).also { addChild(it) }

    override infix fun <T> Internal_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type, visibility = Internal).also { addChild(it) }

    override infix fun <T> Private_Val(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Val(name = v.name, type = v.type, visibility = Private).also { addChild(it) }

    override infix fun <T> Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type).also { addChild(it) }

    override infix fun <T> Public_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type, visibility = Public).also { addChild(it) }

    override infix fun <T> Internal_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type, visibility = Internal).also { addChild(it) }

    override infix fun <T> Private_Var(v: Bundle<Val<T>.() -> Unit, T, Unit>) =
        Var(name = v.name, type = v.type, visibility = Private).also { addChild(it) }
}

interface BodyInterface {
    infix fun Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Class(name: String): Clazz
    infix fun Annotation_Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Sealed_Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Data_Class(v: Bundle<Clazz, Any, Unit>): Clazz
    infix fun Interface(v: Bundle<Interface, Any, Unit>): Interface
}

@OptIn(ExperimentalUuidApi::class)
class BodyDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : BodyInterface {
    override infix fun Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Class(name: String) =
        Clazz(name = name).also { addChild(it) }

    override infix fun Annotation_Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Sealed_Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Data_Class(v: Bundle<Clazz, Any, Unit>) =
        Clazz(name = v.name).also { addChild(it) }

    override infix fun Interface(v: Bundle<Interface, Any, Unit>) =
        Interface(name = v.name).also { addChild(it) }
}

interface SpaceInterface {
    fun space()
}

@OptIn(ExperimentalUuidApi::class)
class SpaceDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : SpaceInterface {
    override fun space() {
        addChild(Space())
    }
}

interface FileInterface {
    infix fun Package(i: String): Package
    infix fun Import(i: String): Import
}

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

interface BlockInterface {

}

@OptIn(ExperimentalUuidApi::class)
class BlockDelegate(
    val children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : BlockInterface {

}

@OptIn(ExperimentalUuidApi::class)
@Suppress("FunctionName")
abstract class KotlinFile(
    override var children: MutableList<Code> = mutableListOf(),
    val addChild: (Code) -> Unit = { code -> children.add(code) },
) : Container(),
    TypeAliasInterface by TypeAliasDelegate(children, addChild),
    ValInterface by ValDelegate(children, addChild),
    BodyInterface by BodyDelegate(children, addChild),
    SpaceInterface by SpaceDelegate(children, addChild),
    FileInterface by FileDelegate(children, addChild) {

    val add get() = this

    val define get() = this.apply { children.add(Define()) }

    // Return type needs to be changed
    infix fun <R> KotlinFile.Fun(v: Bundle<Val<R>.() -> Unit, R, Unit>) =
        Fun(name = v.name, returnType = v<Fun<R>>())

    infix fun KotlinFile.Fun(n: String) = Fun(name = n, returnType = v<Any>())

//    fun <T, R> KotlinFile.When(
//        arg: TypedValue<T>,
//        block: When<T, R>.(TypedValue<T>?) -> R
//    ): When<T, R> = When(name = "When", arg = arg, block = block)

    operator fun <T, R> TypedArgValue<T>.invoke(block: When<T, R>.(TypedValue<T>?) -> WhenBranch<T,R>): When<T, R> =
        When(
            name = "When",
            arg = toTypedValue(),
            block = block,
        )

    infix fun <T, R> When(w: When<T, R>) {
        addChild(w)
    }

    infix fun <R> KotlinFile.When(block: When<Unit, R>.(TypedValue<Unit>?) -> WhenBranch<Unit,R>): When<Unit, R> =
        When(name = "When", block = block).also { addChild(it) }

//    fun <T,R> KotlinFile.When(bundle: Bundle<When<TypedValue<T>,TypedValue<R>>,TypedValue<T>, TypedValue<R>>){
//        When(name = "When", block = checkNotNull(bundle.block){ "Whoops why is bundle block null?" } )
//    }

    private fun <T> TypedArgValue<T>.toTypedValue() = TypedValue<T>(
        value = when (value) {
            is TypedArgValue.Value.CodeValue -> CodeValue(value.value)
            is TypedArgValue.Value.StringValue -> StringValue(value.value)
            null -> null
        },
        code = code,
        type = type,
        import = import,
        nullable = nullable,
        name = name,
        id = id,
    )

    inline fun <reified R> KotlinFile.If(
        arg: TypedValue<Boolean>,
        noinline block: IfBranch<R>.() -> TypedValue<R>
    ): IfBranch<R> {
        return IfBranch(
            returnValue = v<R>(),
            statement = arg,
            block = block,
        )
    }

    fun KotlinFile.For(
        arg: String,
        block: For.() -> Unit
    ): For {
        return For(
            statement = arg,
            block = block,
        ).also { addChild(it) }
    }

    fun KotlinFile.While(
        arg: TypedValue<Boolean>,
        block: While.() -> Unit,
    ): While {
        return While(
            statement = arg,
            block = block,
        ).also { addChild(it) }
    }

    fun KotlinFile.DoWhile(
        arg: TypedValue<Boolean>,
        block: DoWhile.() -> Unit,
    ): DoWhile {
        return DoWhile(
            statement = arg,
            block = block,
        ).also { addChild(it) }
    }

    // Used for complex types like List<String>
    inline fun <reified T : Any?> t(type: KType): TypedValue<T> = TypedValue(type = type.type)

    // Used for simple types like String
    inline fun <reified T : Any?> t(): TypedValue<T> = TypedValue(
        type = T::class.simpleName,
        import = T::class.qualifiedName,
    )

    // Used to create a typed value, the type will not actually get used.
    inline fun <reified T : Any?> v(string: String? = null): TypedValue<T> = TypedValue(
        type = T::class.simpleName,
        value = string?.let { StringValue(it) }
    )

    inline fun <reified T : Any?> v(noinline block: TypedBlock<T>.() -> TypedValue<T>): TypedBlock<T> =
        TypedBlock(block = block)

    inline fun <reified T : Any?> p(string: String? = null): TypedArgValue<T> = TypedArgValue(
        type = T::class.simpleName,
        value = string?.let { TypedArgValue.Value.StringValue(it) }
    )

//    // TypeAlias
//    infix fun <T, R> TypeAlias<T>.assign(t: TypedValue<R>) =
//        TypeAlias(name = name, type = t).also { addChild(it, shouldShowChild) }

    // Val
    infix fun <T> Val<T>.assign(t: TypedValue<T>) =
        AssignedVal(name = name, type = t, id = id).also { addChild(it) }

    infix fun <R> Val<R>.assign(t: Fun<R>) =
        AssignedVal(name = name, type = v<Fun<R>>(), id = id).also { addChild(it) }

//    infix fun <T> Val<T>.assign(t: When<T>) =
//        AssignedVal(name = name, type = v<When<T>>(), id = id).also { addChild(it) }

    infix fun <T> Val<T>.assign(t: ElseBranch<T>) =
        AssignedVal(
            name = name,
            type = TypedValue<T>(value = CodeValue(t)),
            id = id,
        ).also { addChild(it) }

    infix fun <T> Val<T>.getter(t: TypedBlock<T>) =
        AssignedVal(
            name = name,
            getter = t.copy(definition = "getter"),
            id = id
        ).also { addChild(it) }

    infix fun Val<Clazz>.new(clazz: Clazz) = Val<Clazz>(name = clazz.name)
    infix fun Val<Clazz>.withTypes(types: List<Bundle<*, *, *>>) = this
    infix fun Val<Clazz>.constructor(types: List<Bundle<*, *, *>>) = this

    // AssignedVal
    infix fun <R> AssignedVal<R>.withTypes(types: List<Bundle<out Any, Any, Any>>) = this
    infix fun <R> AssignedVal<R>.calls(args: List<Bundle<out Any, out Any, out Any>>) = this


    // Var
    infix fun <T> Var<T>.assign(t: TypedValue<T>) =
        Var(name = name, type = t, id = id).also { addChild(it) }

    infix fun <T> Var<T>.getter(t: TypedBlock<T>) =
        Var(name = name, getter = t.copy(definition = "getter"), id = id).also {
            addChild(it)
        }

    infix fun <T> Var<T>.setter(t: TypedBlock<T>) =
        Var(name = name, setter = t.copy(definition = "setter"), id = id).also {
            addChild(it)
        }

    // Class
    infix fun Clazz.withTypes(types: List<TypedValue<*>>) =
        this.copy(typeArgs = types, id = id).also { addChild(it) }

    infix fun Clazz.constructor(args: List<Bundle<out Any, out Any, out Any>>) =
        this.copy(args = args, id = id).also { addChild(it) }

    infix fun Clazz.extends(clazz: Clazz) =
        this.copy(superClass = clazz, id = id).also { addChild(it) }

    infix fun Clazz.implements(int: Interface) =
        this.copy(implementedInterfaces = implementedInterfaces + listOf(Pair(int, null)), id = id)
            .also { addChild(it) }

    infix fun Clazz.by(clazz: Clazz) = this.copy(
        implementedInterfaces = implementedInterfaces.subList(
            fromIndex = 0,
            toIndex = implementedInterfaces.lastIndex
        ) + Pair(implementedInterfaces.last().first, clazz),
        id = id,
    ).also { addChild(it) }

    infix fun Clazz.body(block: Clazz.() -> Unit) =
        this.copy(block = block, id = id).also { addChild(it) }

    // Fun
    infix fun <R> Fun<R>.withTypes(types: List<Bundle<out Any, Any, out Any>>) = this
    infix fun <R> Fun<R>.constructor(args: List<Bundle<out Any, out Any, out Any>>) = this
    infix fun <R, N> Fun<R>.returns(t: TypedValue<N>) = Fun(name = "", returnType = t)
    infix fun <R> Fun<R>.block(block: Fun<R>.() -> Unit) = this

    // IfBranch
    inline fun <reified R> IfBranch<R>.ElseIf(
        statement: TypedValue<Boolean>,
        noinline block: ElseIfBranch<R>.() -> TypedValue<R>
    ): ElseIfBranch<R> {
        return ElseIfBranch(
            returnValue = v<R>(), // Need to execute block
            statement = statement,
            block = block,
            previous = this,
        )
    }

    inline infix fun <reified R> IfBranch<R>.Else(
        noinline block: ElseBranch<R>.() -> TypedValue<R>
    ): ElseBranch<R> {
        return ElseBranch(
            returnValue = v<R>(),
            block = block,
            previous = this,
        )
    }

    inline fun <reified R> ElseIfBranch<R>.Else(
        noinline block: ElseBranch<R>.() -> TypedValue<R>
    ): ElseBranch<R> {
        return ElseBranch(
            returnValue = v<R>(), // Need to execute block
            block = block,
            previous = this,
        )
    }
}
