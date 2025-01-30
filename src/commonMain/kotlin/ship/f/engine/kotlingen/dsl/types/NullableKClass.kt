package ship.f.engine.kotlingen.dsl.types

import kotlin.reflect.KClassifier
import kotlin.reflect.KType

class NullableKClass<T: Any?>(val type: String?, val fullType: String?, val kType: KType?) :
    KClassifier {

}