import java.util.*

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val month: Int, val value: Int, val currency: Currency)

fun main() {
    //Завдання 1.1
    val array = intArrayOf(1, 2, 3, 4, 5)
    val arr = array.map { it * it }
    val sum = arr.sum()

    arr.forEach { print("$it, ") }

    println("\nSum = $sum")
    //Завдання 1.2
    val arr1 = intArrayOf(1, 2, 3, 4, 5)
    val arr2 = intArrayOf(1, 2, 3)
    val res = Array(arr1.size * arr2.size) { Array(2) {0} }
    var k = 0
    for (i in arr1){
        for (j in arr2){
            res[k][0] = i
            res[k][1] = j
            k++
        }
    }
    for (i in res){
        print("(${i[0]}, ${i[1]}) ")
    }
    println("")
    //Торговці та транзакції

    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")


    val transactions = listOf(
        Transaction(brian, 2011, 12, 300, Currency.getInstance("UAH")),
        Transaction(raoul, 2011, 11, 400, Currency.getInstance("USD")),
        Transaction(raoul, 2012, 10, 1000, Currency.getInstance("UAH")),
        Transaction(mario, 2012, 9, 710, Currency.getInstance("UAH")),
        Transaction(mario, 2012, 7, 700, Currency.getInstance("USD")),
        Transaction(alan, 2012, 4, 950, Currency.getInstance("EUR"))
    )

    val traders = listOf(raoul, mario, alan, brian)
    //усі транзакції за 2011 рік і посортувати за вартістю
    transactions.filter { it.year == 2011 }
        .sortedBy { it.value }
        .forEach {
            println("${it.trader.name} ${it.value}${it.currency.symbol} ${it.year}-${it.month}")
        }

    println("")

    //унікальні міста в яких працюють трейдери
    traders.map { it.city }.distinct().forEach { println("$it, ") }

    println("")

    //усі трейдери із Кембриджа та відсортуйте їх за назвою
    traders.filter { it.city == "Cambridge" }
        .sortedBy { it.name }
        .forEach { println("${it.name} ${it.city}") }

    println("")

    //Поверніть рядок імен усіх трейдерів, відсортованих за алфавітом
    traders.sortedBy { it.name }.forEach { println(it.name) }

    println("")

    //Чи є трейдери в Мілані?
    println("${traders.any{ it.city == "Milan" }}")

    println("")

    //Виведіть у консоль всі значення транзакцій від трейдерів, які проживають у Кембриджі
    transactions.filter { it.trader.city == "Cambridge" }.forEach { println("${it.value}, ") }

    println("")

    //Знайдіть транзакцію з найбільшою вартістю.
    transactions.maxOfOrNull { it.value }.let{println(it)}

    println("")

    //Згрупуйте всі транзакції за валютою.
    transactions.groupBy { it.currency }
        .forEach{
            println("\n${it.key}")
            it.value.forEach{
                println("${it.value}${it.currency.symbol} ${it.year}-${it.month}")
            }
        }

    println("")

    //Знайдіть суму транзакцій у гривнях.
    val exchangeRate = mapOf(
        "UAH" to 1.0,
        "USD" to 41.23,
        "EUR" to 43.71
    )
    transactions.sumOf { it.value * exchangeRate[it.currency.currencyCode]!! }
        .let{println("$it${Currency.getInstance("UAH").symbol}")}

    println("")

    //Створіть рядок, у якому буде виведена послідовність транзакцій
    //відсортована за датою у наступному вигляді
    //«<numOfCurrency>. <month>-<year>: <value> <currency> -> <next transaction>»
    transactions.groupBy { it.year }
        .forEach {
            it.value.sortedBy { it.month }
                .withIndex()
                .forEach { (i, trans) ->
                    print("${i + 1}. ${trans.month}-${trans.year}: ${trans.value} ${trans.currency.symbol} -> ")
                }
        }
}
