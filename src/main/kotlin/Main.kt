import kotlin.reflect.KProperty

fun main() {
//    println(topLevelFinalProp)
//    println(FinalPropContainer().innerFinalProp)
    println(nonFinalProp)
    nonFinalProp = 2
    println(nonFinalProp)
}

val topLevelFinalProp: String by FinalDelegate()

class FinalPropContainer {
    val innerFinalProp: String by FinalDelegate()
}

class FinalDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String =
        if (thisRef === null) {
            "${property.name} is top-level"
        }
        else "${property.name} is inside $thisRef"
}

var nonFinalProp: Int by NonFinalDelegate()

class NonFinalDelegate(private var num: Int = 1) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = num
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        num = value
    }
}