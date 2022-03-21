class Request(val req : ((Response) -> Boolean), var text: String = "", var n: Int = 0) {
}

class Response(val n: Int = 0, val text: String = ""){
}