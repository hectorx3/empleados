package mx.edu.ucaribe.ssav2.company.usecases

interface UseCase<in T, out O> {
    fun handle(command: T): O
}