import EditableData.Companion.edit
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

data class EditData(
    val name: String = "",
    val nameIsValid: Boolean = true,
    val isDeleted: Boolean = false,
)

sealed interface EditableData<out T : Any> {
    data object Idle : EditableData<Nothing>

    data class Loaded<T : Any>(
        val data: T,
        val copy: T = data,
        val promtDisposeChanges: Boolean = false,
    ) : EditableData<T> {
        val isDirty: Boolean get() = data != copy
    }

    data class Error(val error: Throwable) : EditableData<Nothing>

    val loadedAndIsDirty: Boolean
        get() = this is Loaded && data != copy

    val loadedAndPromptDisposeChanges: Boolean
        get() = this is Loaded && this.promtDisposeChanges

    companion object {

        fun <T : Any> EditableData<T>.isLoadedAnd(predicate: Loaded<T>.() -> Boolean): Boolean {
            return this is Loaded && predicate()
        }

        fun <T : Any> EditableData<T>.runIfLoaded(block: (T) -> Unit) {
            if (this is Loaded) block(data)
        }
        fun <T : Any> EditableData<T>.edit(block: Loaded<T>.() -> Loaded<T>): EditableData<T> {
            return if (this is Loaded) this.block() else this
        }

        fun <T : Any> EditableData<T>.editData(block: T.() -> T): EditableData<T> {
            return if (this is Loaded) copy(data = block(data)) else this
        }

        @OptIn(ExperimentalContracts::class)
        fun <T : Any> EditableData<T>.isLoaded(): Boolean {
            contract { returns(true) implies (this@isLoaded is Loaded<T>) }
            return this is Loaded<T>
        }
    }
}

data class Person(val name: String, val wallet: EditableData<EditData> = EditableData.Idle)
data class Wallet(val money: Int)



fun Person.foo(y: Int) {
    val w = Wallet(3)
//    val p = Person("Foo", Wallet(42))
    val f = this.copy (
        wallet = wallet.edit {copy(promtDisposeChanges = !promtDisposeChanges)}
    )
}




fun main () {
    val person = Person("Foo Bar")
    person.foo(5)
}

//import java.io.OutputStream
//import java.net.HttpURLConnection
//import java.net.InetSocketAddress
//import com.sun.net.httpserver.HttpServer
//import com.sun.net.httpserver.HttpHandler
//import com.sun.net.httpserver.HttpExchange
//import kotlinx.coroutines.flow.flow
//import org.jetbrains.annotations.Nullable
//import test.Sas
//import kotlin.reflect.KFunction1
//
//val obj: String? = "hi"
//
//fun foo() = flow {
//    try {
//        var i = 0
//        do {
//            i++
//            val w = obj?.let {
//                networkCall(it)
//            } ?: run {
//                log().toString() // this is the error line
//            }
//        } while (i < 3)
//    } catch (ex: Exception) {
//        log()
//    }
//    emit(false)
//}
//
//fun networkCall(@Nullable bar: String?): String {
//    return "yes"
//}
//
//fun log() {
//    // no op
//}
//
//
//
//fun main() {
//
//    ses(String::toString)
////    val sas = Sas()
//    val x = if (true) "text" else Unit
//    when {
//        1 > 2 -> println("rst")
//        2 > 1 -> println("rst")
//        3 > 2 -> println("rst")
//    }
//
//    val server = HttpServer.create(InetSocketAddress(8080), 0)
//
//    // Обработка GET-запроса
//    server.createContext("/get") { exchange: HttpExchange ->
//        if (exchange.requestMethod == "GET") {
//            val response = "Hello from GET!"
//            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
//            val output: OutputStream = exchange.responseBody
//            output.write(response.toByteArray())
//            output.close()
//        } else {
//            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, -1)
//        }
//    }
//
//    // Обработка POST-запроса
//    server.createContext("/post") { exchange: HttpExchange ->
//        if (exchange.requestMethod == "POST") {
//            val inputStream = exchange.requestBody
//            val body = inputStream.reader().readText()  // Читаем тело запроса
//            val response = "Received POST with body: $body"
//            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
//            val output: OutputStream = exchange.responseBody
//            output.write(response.toByteArray())
//            output.close()
//        } else {
//            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, -1)
//        }
//    }
//
//    server.executor = null // Используем стандартный executor
//    server.start()
//    println("Server is listening on port 8080")
//}
//
//fun ses(function: KFunction1<String, String>) {}
//
//
////fun main2() {
////
////
////    foo {
////        println(it)
////    }
////    val sas = LotsOfData()
//////    ui {
//////        box()
//////        box()
//////        box()
//////    }.also {
//////        println(it)
//////    }
////}


fun foo(){
    println("hi")
}
