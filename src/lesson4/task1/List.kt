@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs=0
    if (v.size>0) {
        for (i in 0 until v.size)
            abs = (abs + v[i] * v[i]).toInt()
    }
    else
        abs=0
    return sqrt(abs.toDouble())
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val amount = list.size
    var summ = 0
    for (i in 0 until list.size) {
        summ = (summ + list[i]).toInt()
    }
    if (amount>0) return (summ / amount).toDouble()
    else return 0.0
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val amount = list.size
        var summ = 0
        var d = 0
        if (amount > 0) {
            for (i in 0 until amount)
                summ = (summ + list[i]).toInt()
            d = d + summ / amount
            for (i in 0 until amount)
                list[i] = list[i] - d
        } else
            d = d + 0
        return list
    }
    else return mutableListOf()
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    val length=a.size
    var C=0
    if (length>0) {
        for (i in 0 until length)
            C = C + a[i] * b[i]
    }
    else
        C=0
    return C
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int):Int {
    var result=0
    val x1=x.toDouble()
    for (i in 0 until p.size) {
        result+=(p[i] * (x1.pow(i))).toInt()
    }
    return result
}
/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isNotEmpty()) {
        for (i in 1 until list.size) {
            var sum = 0
            for (i1 in 0 until i + 1){
                sum+=list[i1]
            }
            list[i]=sum
        }
        return list
    }
    else return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    return listOf(0,0)
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    return ""
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var digits= listOf<Int>()
    var n1=n
    while (n1>0) {
        digits=digits + (n%base)
        n1 /= base
    }
    return digits.asReversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    return ""
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result=0
    val n=digits.size
    for (i in 0 until n)
        result=result + digits[i] * base.toDouble().pow(n - i - 1).toInt()
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    return 0
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var s=""
    val d=n%10
    val c=(n/10)%10
    val b=(n/100)%10
    val a=n/1000
    if (a==0)
        s=s + ""
    else if (a==1)
        s=s  + "M"
    else if (a==2)
        s=s + "MM"
    else
        s=s + "MMM"
    if (b==0)
        s=s + ""
    else if (b==1)
        s=s + "C"
    else if (b==2)
        s=s + "CC"
    else if (b==3)
        s=s + "CCC"
    else if (b==4)
        s=s + "CD"
    else if (b==5)
        s=s + "D"
    else if (b==6)
        s=s + "DC"
    else if (b==7)
        s=s + "DCC"
    else if (b==8)
        s=s + "DCCC"
    else
        s=s + "CM"
    if (c==0)
        s=s + ""
    else if (c==1)
        s=s + "X"
    else if (c==2)
        s=s + "XX"
    else if (c==3)
        s=s + "XXX"
    else if (c==4)
        s=s + "XL"
    else if (c==5)
        s=s + "L"
    else if (c==6)
        s=s + "LX"
    else if (c==7)
        s=s + "LXX"
    else if (c==8)
        s=s + "LXXX"
    else
        s=s + "XC"
    if (d==0)
        s=s + ""
    else if (d==1)
        s=s + "I"
    else if (d==2)
        s=s + "II"
    else if (d==3)
        s=s + "III"
    else if (d==4)
        s=s + "IV"
    else if (d==5)
        s=s + "V"
    else if (d==6)
        s=s + "VI"
    else if (d==7)
        s=s + "VII"
    else if (d==8)
        s=s + "VIII"
    else
        s=s + "IX"
    return s


}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var s=""
    val f=n%10
    val e=(n/10)%10
    val d=(n/100)%10
    val c=(n/1000)%10
    val b=(n/10000)%10
    val a=n/100000
    if (a==0)
        s = s + ""
    else if (a == 1)
        s = s + "сто"
    else if (a == 2)
        s = s + "двести"
    else if (a == 3)
        s = s + "триста"
    else if (a == 4)
        s = s + "четыреста"
    else if (a == 5)
        s = s + "пятьсот"
    else if (a == 6)
        s = s + "шестьсот"
    else if (a == 7)
        s = s + "семьсот"
    else if (a == 8)
        s = s + "восемьсот"
    else
        s = s + "девятьсот"
    if (b==0)
        s = s + ""
    else if (b == 1)
        if (c==0) s=s+" десять тысяч" else if (c==1) s=s+" одиннадцать тысяч" else if (c==2) s=s+" двенадцать тысяч" else if (c==3) s=s+" тринадцать тысяч"
        else if (c==4) s=s+" четырнадцать тысяч" else if (c==5) s=s+" пятнадцать тысяч" else if (c==6) s=s+" шестнадцать тысяч"
        else if (c==7) s=s+" семнадцать тысяч" else if (c==8) s=s+" восемнадцать тысяч" else  s=s+" девятнадцать тысяч"
    else if (b == 2)
        s = s + " двадцать"
    else if (b == 3)
        s = s + " тридцать"
    else if (b == 4)
        s = s + " сорок"
    else if (b == 5)
        s = s + " пятьдесят"
    else if (b == 6)
        s = s + " шестьдесят"
    else if (b == 7)
        s = s + " семьдесят"
    else if (b == 8)
        s = s + " восемьдесят"
    else
        s = s + " девяносто"
    if (c==0) {
        if (a==0) s = s + ""
        else s = s + " тысяч"
    }
    else if (c==1 && b!=1)
        s=s + " одна тысяча"
    else if (c==2 && b!=1)
        s=s + " две тысячи"
    else if (c==3 && b!=1)
        s=s + " три тысячи"
    else if (c==4 && b!=1)
        s=s + " четыре тысячи"
    else if (c==5 && b!=1)
        s=s + " пять тысяч"
    else if (c==6 && b!=1)
        s=s + " шесть тысяч"
    else if (c==7 && b!=1)
        s=s + " семь тысяч"
    else if (c==8 && b!=1)
        s=s + " восемь тысяч"
    else if (c==9 && b!=1)
        s=s + " девять тысяч"
    if (d==0)
        s=s + ""
    else if (d==1)
        s=s + " сто"
    else if (d==2)
        s=s + " двести"
    else if (d==3)
        s=s + " триста"
    else if (d==4)
        s=s + " четыреста"
    else if (d==5)
        s=s + " пятьсот"
    else if (d==6)
        s=s + " шестьсот"
    else if (d==7)
        s=s + " семьсот"
    else if (d==8)
        s=s + " восемьсот"
    else
        s=s + " девятьсот"
    if (e==0)
        s=s + ""
    else if (e==1)
        if (f==0) s=s+" десять" else if (f==1) s=s+" одиннадцать" else if (f==2) s=s+" двенадцать" else if (f==3) s=s+" тринадцать"
        else if (f==4) s=s+" четырнадцать" else if (f==5) s=s+" пятнадцать" else if (f==6) s=s+" шестнадцать"
        else if (f==7) s=s+" семнадцать" else if (f==8) s=s+" восемнадцать" else  s=s+" девятнадцать"
    else if (e==2)
        s=s + " двадцать"
    else if (e==3)
        s=s + " тридцать"
    else if (e==4)
        s=s + " сорок"
    else if (e==5)
        s=s + " пятьдесят"
    else if (e==6)
        s=s + " шестьдесят"
    else if (e==7)
        s=s + " семьдесят"
    else if (e==8)
        s=s + " восемьдесят"
    else
        s=s + " девяносто"
    if (f==0)
        s=s + ""
    else if (f==1 && e!=1)
        s=s + " один"
    else if (f==2 && e!=1)
        s=s + " два"
    else if (f==3 && e!=1)
        s=s + " три"
    else if (f==4 && e!=1)
        s=s + " четыре"
    else if (f==5 && e!=1)
        s=s + " пять"
    else if (f==6 && e!=1)
        s=s + " шесть"
    else if (f==7 && e!=1)
        s=s + " семь"
    else if (f==8 && e!=1)
        s=s + " восемь"
    else if (f==9 && e!=1)
        s=s + " девять"
    return s.trim()

}


