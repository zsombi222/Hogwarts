class Request(val req: ((Response) -> Boolean), var text: String = "", var n: Int = 0) {
}

class Response(var n: Int = 0, var text: String = "") {
}