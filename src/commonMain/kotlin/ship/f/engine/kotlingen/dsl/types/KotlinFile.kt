package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Internal
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Private
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Public
import ship.f.engine.kotlingen.dsl.types.util.KType

@Suppress("FunctionName")
abstract class KotlinFile : Container() {
    private var shouldShowChild = true
    var block: EntireFile.() -> Unit = {
        println("not implemented")
    }

    val add
        get() = this.apply { shouldShowChild = true }

    val define
        get() = this.apply { shouldShowChild = false }

    override var name: String = ""
    lateinit var packageName: Package
    var imports: List<Import> = listOf()

    // File
    fun space() {
        children = children.plus(Space())
    }

    private fun addChild(child: Code) {
        if (shouldShowChild) {
            children = children.plus(child)
        }
    }

    infix fun KotlinFile.Package(i: String) = Package(name = i).also {
        packageName = it
    }

    infix fun KotlinFile.Import(i: String) = Import(name = i).also {
        imports = imports.plus(it)
    }

    infix fun KotlinFile.TypeAlias(a: String) = TypeAlias<Any>(name = a)

    infix fun <T : Any?> KotlinFile.Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Public_Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type, visibility = Public).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Internal_Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type, visibility = Internal).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Private_Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type, visibility = Private).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Public_Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type, visibility = Public).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Internal_Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type, visibility = Internal).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun <T : Any?> KotlinFile.Private_Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type, visibility = Private).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    infix fun KotlinFile.Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Class(name: String) = Clazz(name = name)
    infix fun KotlinFile.Annotation_Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Sealed_Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Data_Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Interface(v: Bundle<Interface, Any>) = Interface(name = v.name)

    infix fun <R> KotlinFile.Fun(v: Bundle<Val<R>.() -> Unit, R>) =
        Fun(name = v.name, returnType = v<Fun<R>>())

    infix fun KotlinFile.Fun(n: String) = Fun(name = n, returnType = v<Any>())

    inline fun <T, reified R> KotlinFile.When(
        arg: TypedString<T>,
        block: When<R>.(TypedString<T>) -> TypedString<R>
    ): When<R> {
        val w = When(name = "", returnType = v<R>())
        block(When(name = "", returnType = v<R>()), arg)
        return w
    }

    inline fun <reified R> KotlinFile.When(block: When<R>.() -> TypedString<R>): When<R> {
        val w = When(name = "", returnType = v<R>())
        block(When(name = "", returnType = v<R>()))
        return w
    }

    inline fun <reified R> KotlinFile.If(
        arg: TypedString<Boolean>,
        block: If<R>.() -> TypedString<R>
    ): If<R> {
        val i = If(statement = "", returnType = v<R>())
        block(If(statement = "", returnType = v<R>()))
        return i
    }

    inline fun <reified R> KotlinFile.For(
        arg: TypedString<Boolean>,
        block: For.() -> TypedString<R>
    ): For {
        val f = For(name = "")
        return f
    }

    inline fun <reified R> KotlinFile.While(
        arg: TypedString<Boolean>,
        block: While.() -> TypedString<R>
    ): While {
        val w = While(statement = "")
        return w
    }

    inline fun <reified R> KotlinFile.DoWhile(
        arg: TypedString<Boolean>,
        block: While.() -> TypedString<R>
    ): While {
        val w = While(statement = "")
        return w
    }

    // Used for complex types like List<String>
    inline fun <reified T : Any?> t(type: KType): TypedString<T> = TypedString(type = type.type)

    // Used for simple types like String
    inline fun <reified T : Any?> t(): TypedString<T> = TypedString(
        type = T::class.simpleName,
        import = T::class.qualifiedName,
    )

    // Used to create a typed value, the type will not actually get used.
    inline fun <reified T : Any?> v(string: String? = null): TypedString<T> = TypedString(
        type = T::class.simpleName,
        value = string
    )

    // Currently has no uses as needs to be implemented
    inline fun <reified T : Any?> v(noinline block: TypedBlock<T>.() -> TypedString<T>): TypedBlock<T> =
        TypedBlock(block = block)

    // TypeAlias
    infix fun <T, R> TypeAlias<T>.assign(t: TypedString<R>) =
        TypeAlias(name = name, type = t).also {
            if (shouldShowChild) {
                children = children.plus(it)
            }
        }

    // Val
    infix fun <T> Val<T>.assign(t: TypedString<T>) =
        AssignedVal(name = name, type = t).also { code ->
            this@KotlinFile.children = this@KotlinFile.children.map {
                if (it.name == code.name && shouldShowChild) {
                    code
                } else it
            }
        }

    infix fun <R> Val<R>.assign(t: Fun<R>) = AssignedVal(name = name, type = v<Fun<R>>())
    infix fun <T> Val<T>.assign(t: When<T>) = AssignedVal(name = name, type = v<When<T>>())
    infix fun <T> Val<T>.assign(t: Else<T>) = AssignedVal(name = name, type = v<If<T>>())
    infix fun <T> Val<T>.getter(t: TypedBlock<T>) = AssignedVal(name = name, typedBlock = t)

    infix fun Val<Clazz>.new(clazz: Clazz) = Val<Clazz>(name = clazz.name)
    infix fun Val<Clazz>.withTypes(types: List<Bundle<*, *>>) = this
    infix fun Val<Clazz>.constructor(types: List<Bundle<*, *>>) = this

    // AssignedVal
    infix fun <R> AssignedVal<R>.withTypes(types: List<Bundle<out Any, Any>>) = this
    infix fun <R> AssignedVal<R>.calls(args: List<Bundle<out Any, out Any>>) = this


    // Var
    infix fun <T> Var<T>.assign(t: TypedString<T>) =
        Var(name = name, type = t).also { code ->
            children = children.map {
                if (it.name == code.name && shouldShowChild) {
                    code
                } else it
            }
        }

    infix fun <T> Var<T>.getter(t: TypedBlock<T>) = Var(name = name, getter = t)
    infix fun <T> Var<T>.setter(t: TypedBlock<T>) = Var(name = name, setter = t)

    // Class
    infix fun Clazz.withTypes(types: List<Bundle<Any, Any>>) = this
    infix fun Clazz.constructor(args: List<Bundle<out Any, out Any>>) = this
    infix fun Clazz.extends(clazz: Clazz) = this
    infix fun Clazz.implements(int: Interface) = this
    infix fun Clazz.by(int: Interface) = this
    infix fun Clazz.body(block: Clazz.() -> Unit) = this

    // Fun
    infix fun <R> Fun<R>.withTypes(types: List<Bundle<out Any, Any>>) = this
    infix fun <R> Fun<R>.constructor(args: List<Bundle<out Any, out Any>>) = this
    infix fun <R, N> Fun<R>.returns(t: TypedString<N>) = Fun(name = "", returnType = t)
    infix fun <R> Fun<R>.block(block: Fun<R>.() -> Unit) = this

    // If
    inline fun <reified R> If<R>.ElseIf(
        arg: TypedString<Boolean>,
        block: If<R>.() -> TypedString<R>
    ): If<R> {
        val i = If(statement = "", returnType = v<R>())
        block(If(statement = "", returnType = v<R>()))
        return i
    }

    inline infix fun <reified R> If<R>.Else(block: Else<R>.() -> Unit): Else<R> {
        val e = Else(name = "", returnType = v<R>())
        block(Else(name = "", returnType = v<R>()))
        return e
    }
}
