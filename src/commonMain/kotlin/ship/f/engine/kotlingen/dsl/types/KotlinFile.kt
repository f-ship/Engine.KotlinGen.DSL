package ship.f.engine.kotlingen.dsl.types

import ship.f.engine.kotlingen.dsl.Bundle
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Internal
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Private
import ship.f.engine.kotlingen.dsl.types.Code.Visibility.Public
import kotlin.reflect.typeOf

@Suppress("FunctionName")
abstract class KotlinFile : Container() {
    override var name: String = ""
    lateinit var packageName: Package
    var imports: List<Import> = listOf()

    private fun Code.addFileChild() {
        children = children.plus(this)
    }

    private fun Code.replaceFileChild() {
        replace()
    }

    fun space() {
        children = children.plus(Space())
    }

    infix fun KotlinFile.Package(i: String) = Package(name = i).apply {
        packageName = this
    }

    infix fun KotlinFile.Import(i: String) = Import(name = i).apply {
        imports = imports.plus(this)
    }

    infix fun KotlinFile.TypeAlias(a: String) = TypeAlias<Any>(name = a).apply {
//        children = children.plus(this) don't do this here as it still needs to be assigned
    }

    infix fun <T> TypeAlias<T>.assign(t: String) = this.apply {
        simpleType = t
    } //May not use this

    infix fun <T, R> TypeAlias<T>.assign(t: TypedString<R>) =
        TypeAlias(name = name, type = t).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Public_Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type, visibility = Public).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Internal_Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type, visibility = Internal).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Private_Val(v: Bundle<Val<T>.() -> Unit, T>) =
        Val(name = v.name, type = v.type, visibility = Private).apply {
            addFileChild()
        }

    infix fun <T> Val<T>.assign(t: TypedString<T>) = AssignedVal(name = name, type = t).apply { replaceFileChild() }
    infix fun <R> Val<R>.assign(t: Fun<R>) = AssignedVal(name = name, type = t<Fun<R>>())
    infix fun <T> Val<T>.assign(t: When<T>) = AssignedVal(name = name, type = t<When<T>>())
    infix fun <T> Val<T>.assign(t: Else<T>) = AssignedVal(name = name, type = t<If<T>>())
    infix fun <T> Val<T>.getter(t: TypedBlock<T>) = AssignedVal(name = name, typedBlock = t)
    infix fun <T> Val<T>.setter(t: TypedBlock<T>) = AssignedVal(name = name, typedBlock = t)

    infix fun <T : Any?> KotlinFile.Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Public_Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type, visibility = Public).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Internal_Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type, visibility = Internal).apply {
            addFileChild()
        }

    infix fun <T : Any?> KotlinFile.Private_Var(v: Bundle<Val<T>.() -> Unit, T>) =
        Var(name = v.name, type = v.type, visibility = Private).apply {
            addFileChild()
        }

    infix fun <T> Var<T>.assign(t: TypedString<T>) = Var(name = name, type = t).apply { replaceFileChild() }
    infix fun <T> Var<T>.getter(t: TypedBlock<T>) = Var(name = name, typedBlock = t)
    infix fun <T> Var<T>.setter(t: TypedBlock<T>) = Var(name = name, typedBlock = t)

    infix fun KotlinFile.Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Class(name: String) = Clazz(name = name)
    infix fun KotlinFile.Annotation_Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Sealed_Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun KotlinFile.Data_Class(v: Bundle<Clazz, Any>) = Clazz(name = v.name)
    infix fun Clazz.withTypes(types: List<Bundle<Any, Any>>) = this
    infix fun Clazz.constructor(args: List<Bundle<*, Any>>) = this
    infix fun Clazz.extends(clazz: Clazz) = this
    infix fun Clazz.implements(int: Interface) = this
    infix fun Clazz.by(int: Interface) = this
    infix fun Clazz.body(block: Clazz.() -> Unit) = this

    infix fun KotlinFile.Interface(v: Bundle<Interface, Any>) = Interface(name = v.name)

    infix fun Val<Clazz>.new(clazz: Clazz) = Val<Clazz>(name = clazz.name)
    infix fun Val<Clazz>.withTypes(types: List<Bundle<*, *>>) = this
    infix fun Val<Clazz>.constructor(types: List<Bundle<*, *>>) = this

    infix fun <R> KotlinFile.Fun(v: Bundle<Val<R>.() -> Unit, R>) =
        Fun(name = v.name, returnType = t<Fun<R>>())

    infix fun KotlinFile.Fun(n: String) = Fun(name = n, returnType = t<Any>())
    infix fun <R> Fun<R>.withTypes(types: List<Bundle<out Any, Any>>) = this
    infix fun <R> AssignedVal<R>.withTypes(types: List<Bundle<out Any, Any>>) = this
    infix fun <R> Fun<R>.constructor(args: List<Bundle<out Any, out Any>>) = this
    infix fun <R> AssignedVal<R>.calls(args: List<Bundle<out Any, out Any>>) = this
    infix fun <R, N> Fun<R>.returns(t: TypedString<N>) = Fun(name = "", returnType = t)
    infix fun <R> Fun<R>.block(block: Fun<R>.() -> Unit) = this

    inline fun <T, reified R> KotlinFile.When(
        arg: TypedString<T>,
        block: When<R>.(TypedString<T>) -> TypedString<R>
    ): When<R> {
        val w = When(name = "", returnType = t<R>())
        block(When(name = "", returnType = t<R>()), arg)
        return w
    }

    inline fun <reified R> KotlinFile.When(block: When<R>.() -> TypedString<R>): When<R> {
        val w = When(name = "", returnType = t<R>())
        block(When(name = "", returnType = t<R>()))
        return w
    }

    inline fun <reified R> KotlinFile.If(
        arg: TypedString<Boolean>,
        block: If<R>.() -> TypedString<R>
    ): If<R> {
        val i = If(statement = "", returnType = t<R>())
        block(If(statement = "", returnType = t<R>()))
        return i
    }

    inline fun <reified R> If<R>.ElseIf(
        arg: TypedString<Boolean>,
        block: If<R>.() -> TypedString<R>
    ): If<R> {
        val i = If(statement = "", returnType = t<R>())
        block(If(statement = "", returnType = t<R>()))
        return i
    }

    inline infix fun <reified R> If<R>.Else(block: Else<R>.() -> Unit): Else<R> {
        val e = Else(name = "", returnType = t<R>())
        block(Else(name = "", returnType = t<R>()))
        return e
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

    inline fun <reified T : Any?> t(string: String? = null): TypedString<T> = TypedString(
        c = NullableKClass(
            type = T::class.simpleName,
            fullType = T::class.toString(),
            kType = typeOf<T>(),
        ),
        value = string
    )

    inline fun <reified T : Any?> t(type: KType): TypedString<T> = TypedString(
        c = NullableKClass(
            type = type.type,
            fullType = T::class.toString(),
            kType = typeOf<T>(),
        ),
    )

    inline fun <reified T : Any?> t(noinline block: TypedBlock<T>.() -> TypedString<T>): TypedBlock<T> =
        TypedBlock(
            c = NullableKClass(
                type = T::class.simpleName,
                fullType = T::class.toString(),
                kType = typeOf<T>()
            ),
        )

    data class KType(val type: String)
}