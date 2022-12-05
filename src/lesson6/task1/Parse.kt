@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

// Урок 6: разбор строк, исключения
// Максимальное количество баллов = 13
// Рекомендуемое количество баллов = 11
// Вместе с предыдущими уроками (пять лучших, 2-6) = 40/54

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val list = mutableListOf<String>()
    val monthsToNums = mapOf(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )
    val daysToMonthLeap = mapOf(
        "января" to 31,
        "февраля" to 29,
        "марта" to 31,
        "апреля" to 30,
        "мая" to 31,
        "июня" to 30,
        "июля" to 31,
        "августа" to 31,
        "сентября" to 30,
        "октября" to 31,
        "ноября" to 30,
        "декабря" to 31
    )
    val daysToMonthUsual = mapOf(
        "января" to 31,
        "февраля" to 28,
        "марта" to 31,
        "апреля" to 30,
        "мая" to 31,
        "июня" to 30,
        "июля" to 31,
        "августа" to 31,
        "сентября" to 30,
        "октября" to 31,
        "ноября" to 30,
        "декабря" to 31
    )
    if (!(str.matches(Regex("""\d* [а-я]* \d*""")))) return ""
    val components = str.split(" ")
    val day = components[0]
    val month = components[1]
    val year = components[2].toInt()
    if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
        if (month in monthsToNums.keys) {
            if (day.toInt() <= daysToMonthLeap.get(month)!!) {
                if (day.toInt() <= 10 && "0" in day) list.add(day)
                else if (day.toInt() <= 10 && "0" !in day) list.add("0$day")
                else list.add(day)
                monthsToNums.get(month)?.let { list.add(it) }
                list.add(year.toString())
            } else return ""
        } else return ""
    } else {
        if (month in monthsToNums.keys) {
            if (day.toInt() <= daysToMonthUsual.get(month)!!) {
                if (day.toInt() >= 10) list.add(day) else list.add("0" + day)
                monthsToNums.get(month)?.let { list.add(it) }
                list.add(year.toString())
            } else return ""
        } else return ""
    }
    return list.joinToString(separator = ".")

}

/**
 * Средняя (4 балла)
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val list1 = mutableListOf<String>()
    val numsToMonths = mapOf(
        "01" to "января",
        "02" to "февраля",
        "03" to "марта",
        "04" to "апреля",
        "05" to "мая",
        "06" to "июня",
        "07" to "июля",
        "08" to "августа",
        "09" to "сентября",
        "10" to "октября",
        "11" to "ноября",
        "12" to "декабря"
    )
    val daysToMonthLeap1 = mapOf(
        "01" to 31,
        "02" to 29,
        "03" to 31,
        "04" to 30,
        "05" to 31,
        "06" to 30,
        "07" to 31,
        "08" to 31,
        "09" to 30,
        "10" to 31,
        "11" to 30,
        "12" to 31
    )
    val daysToMonthUsual1 = mapOf(
        "01" to 31,
        "02" to 28,
        "03" to 31,
        "04" to 30,
        "05" to 31,
        "06" to 30,
        "07" to 31,
        "08" to 31,
        "09" to 30,
        "10" to 31,
        "11" to 30,
        "12" to 31
    )
    if (!(digital.matches(Regex("""\d+\.\d+\.\d+""")))) return ""
    val parts = digital.split(".")
    val day1 = parts[0].toInt()
    val month1 = parts[1]
    val year1 = parts[2].toInt()
    if ((year1 % 400 == 0) || (year1 % 4 == 0 && year1 % 100 != 0)) {
        if (month1 in numsToMonths.keys) {
            if (day1 <= daysToMonthLeap1.get(month1)!!) {
                list1.add(day1.toString())
                numsToMonths.get(month1)?.let { list1.add(it) }
                list1.add(year1.toString())
            } else return ""
        } else return ""
    } else {
        if (month1 in numsToMonths.keys) {
            if (day1 <= daysToMonthUsual1.get(month1)!!) {
                list1.add(day1.toString())
                numsToMonths.get(month1)?.let { list1.add(it) }
                list1.add(year1.toString())
            } else return ""
        } else return ""
    }
    return list1.joinToString(separator = " ")

}

/**
 * Средняя (4 балла)
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val checkString = "+0123456789"
    val builder = StringBuilder()
    if (phone.contains(Regex("""\( *\)""")) ||
        phone.matches(Regex("""\+ ?\d""")) ||
        !phone.matches(Regex("""(\+? *\d[- \d]*(\([-\d ]+\)[-\d ]+)?)"""))
    ) return ""
    for (symbol in phone) {
        if (symbol in checkString) builder.append(symbol)
    }
    return builder.toString()
}

/**
 * Средняя (5 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (jumps.contains(Regex("""[^\d\s\-%]""")) ||
        jumps.contains(Regex("""([%\-])(%|-|\d)|(%|-|\d)([%\-])""")) ||
        jumps.isEmpty()
    )
        return -1
    val numsAndChars = jumps.split(" ")
    val notDigits = mutableListOf("-", "%")
    var max = -1
    for (element in numsAndChars) {
        if (element !in notDigits && element.toInt() > max) max = element.toInt()
    }
    return max
}

/**
 * Сложная (6 баллов)
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (!"$expression + ".matches(Regex("""(\d+ [+-] )+""")))
        throw IllegalArgumentException(expression)
    val numsAndChars = expression.split(" ")
    var res = 0
    res += (numsAndChars[0].toInt())
    for (i in 2 until numsAndChars.size) {
        if (numsAndChars[i - 1] == "+") res += numsAndChars[i].toInt()
        else if (numsAndChars[i - 1] == "-") res += ((numsAndChars[i].toInt()) * (-1))
    }
    return res
}

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше нуля либо равны нулю.
 */
fun mostExpensive(description: String): String {
    val splitted = description.split("; ")
    var max = -1.0
    val itemToPrice = mutableMapOf<Double, String>()
    if (description.isEmpty()) return ""
    for (string in splitted) {
        val sublist = string.split(" ")
        if (sublist.size == 2 && sublist[0].isNotEmpty() && sublist[1].toDouble() >= 0)
            itemToPrice.put(sublist[1].toDouble(), sublist[0])
        else return ""
    }
    for (cost in itemToPrice.keys) {
        if (cost > max) max = cost
    }
    return itemToPrice.get(max)!!
}

/**
 * Сложная (6 баллов)
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная (7 баллов)
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
