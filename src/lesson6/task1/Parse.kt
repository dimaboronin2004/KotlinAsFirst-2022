@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

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
    val str1=str
    val parts=str.split(" ")
    val day=parts[0].toInt()
    val month=parts[1].toInt()
    val year=parts[2].toInt()
    if ((year%400==0) || ((year%4==0) && (year%100!=0))) {
        if ((day > 0 && day < 32) && (month == 1)) {
            str1.replace(" января ", ".01.")
            return str1
        } else if ((day > 0 && day < 30) && (month == 2)) {
            str1.replace(" февраля ", ".02")
            return str1
        } else if ((day > 0 && day < 32) && (month == 3)) {
            str1.replace(" марта ", ".03.")
            return str1
        } else if ((day > 0 && day < 31) && (month == 4)) {
            str1.replace(" апреля ", ".04.")
            return str1
        } else if ((day > 0 && day < 32) && (month == 5)) {
            str1.replace(" мая ", ".05.")
            return str1
        } else if ((day > 0 && day < 31) && (month == 6)) {
            str1.replace(" июня ", ".06.")
            return str1
        } else if ((day > 0 && day < 32) && (month == 7)) {
            str1.replace(" июля ", ".07.")
            return str1
        } else if ((day > 0 && day < 32) && (month == 8)) {
            str1.replace(" августа ", ".08.")
            return str1
        } else if ((day > 0 && day < 31) && (month == 9)) {
            str1.replace(" сентября ", ".09.")
            return str1
        } else if ((day > 0 && day < 32) && (month == 10)) {
            str1.replace(" октября ", ".10.")
            return str1
        } else if ((day > 0 && day < 31) && (month == 11)) {
            str1.replace(" ноября ", ".11.")
            return str1
        } else if ((day > 0 && day < 32) && (month == 12)) {
            str1.replace(" декабря ", ".12.")
            return str1
        } else
            return ""
    }

    else {
        if ((day>0 && day<32) && (month==1)) {
            str1.replace(" января ", ".01.")
            return str1
        }
        else if ((day>0 && day<29) && (month==2)) {
            str1.replace(" февраля ", ".02.")
            return str1
        }
        else if ((day>0 && day<32) && (month==3)) {
            str1.replace(" марта ", ".03.")
            return str1
        }
        else if ((day>0 && day<31) && (month==4)) {
            str1.replace(" апреля ", ".04.")
            return str1
        }
        else if ((day>0 && day<32) && (month==5)) {
            str1.replace(" мая ", ".05.")
            return str1
        }
        else if ((day>0 && day<31) && (month==6)) {
            str1.replace(" июня ", ".06.")
            return str1
        }
        else if ((day>0 && day<32) && (month==7)) {
            str1.replace(" июля ", ".07.")
            return str1
        }
        else if ((day>0 && day<32) && (month==8)) {
            str1.replace(" августа ", ".08.")
            return str1
        }
        else if ((day>0 && day<31) && (month==9)) {
            str1.replace(" сентября ", ".09.")
            return str1
        }
        else if ((day>0 && day<32) && (month==10)) {
            str1.replace(" октября ", ".10.")
            return str1
        }
        else if ((day>0 && day<31) && (month==11)) {
            str1.replace(" ноября ", ".11.")
            return str1
        }
        else if ((day>0 && day<32) && (month==12)) {
            str1.replace(" декабря ", ".12.")
            return str1
        }
        else
            return ""
    }
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
    val str1=digital
    val parts=digital.split(" ")
    val day=parts[0].toInt()
    val month=parts[1].toInt()
    val year=parts[2].toInt()
    if ((year%400==0) || ((year%4==0) && (year%100!=0))) {
        if ((day > 0 && day < 32) && (month == 1)) {
            str1.replace(".01.", " января ")
            return str1
        } else if ((day > 0 && day < 30) && (month == 2)) {
            str1.replace(".02.", " февраля ")
            return str1
        } else if ((day > 0 && day < 32) && (month == 3)) {
            str1.replace(".03.", " марта ")
            return str1
        } else if ((day > 0 && day < 31) && (month == 4)) {
            str1.replace(".04.", " апреля ")
            return str1
        } else if ((day > 0 && day < 32) && (month == 5)) {
            str1.replace(".05.", " мая ")
            return str1
        } else if ((day > 0 && day < 31) && (month == 6)) {
            str1.replace(".06.", " июня ")
            return str1
        } else if ((day > 0 && day < 32) && (month == 7)) {
            str1.replace(".07.", " июля ")
            return str1
        } else if ((day > 0 && day < 32) && (month == 8)) {
            str1.replace(".08.", " августа ")
            return str1
        } else if ((day > 0 && day < 31) && (month == 9)) {
            str1.replace(".09.", " сентября ")
            return str1
        } else if ((day > 0 && day < 32) && (month == 10)) {
            str1.replace(".10.", " октября ")
            return str1
        } else if ((day > 0 && day < 31) && (month == 11)) {
            str1.replace(".11.", " ноября ")
            return str1
        } else if ((day > 0 && day < 32) && (month == 12)) {
            str1.replace(".12.", " декабря ")
            return str1
        } else
            return ""
    }

    else {
        if ((day>0 && day<32) && (month==1)) {
            str1.replace(".01.", " января ")
            return str1
        }
        else if ((day>0 && day<29) && (month==2)) {
            str1.replace(".02.", " февраля ")
            return str1
        }
        else if ((day>0 && day<32) && (month==3)) {
            str1.replace(".03.", " марта ")
            return str1
        }
        else if ((day>0 && day<31) && (month==4)) {
            str1.replace(".04.", " апреля ")
            return str1
        }
        else if ((day>0 && day<32) && (month==5)) {
            str1.replace(".05.", " мая ")
            return str1
        }
        else if ((day>0 && day<31) && (month==6)) {
            str1.replace(".06.", " июня ")
            return str1
        }
        else if ((day>0 && day<32) && (month==7)) {
            str1.replace(".07.", " июля ")
            return str1
        }
        else if ((day>0 && day<32) && (month==8)) {
            str1.replace(".08.", " августа ")
            return str1
        }
        else if ((day>0 && day<31) && (month==9)) {
            str1.replace(".09.", " сентября ")
            return str1
        }
        else if ((day>0 && day<32) && (month==10)) {
            str1.replace(".10.", " октября ")
            return str1
        }
        else if ((day>0 && day<31) && (month==11)) {
            str1.replace(".11.", " ноября ")
            return str1
        }
        else if ((day>0 && day<32) && (month==12)) {
            str1.replace(".12.", " декабря ")
            return str1
        }
        else
            return ""
    }
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
    val phone_number=phone
    val check_string_1 = "+-()0123456789"
    val check_string_2 = "+0123456789"
    var mistake=0
    var result_string=""
    for (i in 0 until  phone_number.length) {
        if (phone_number[i] in check_string_1) mistake+=0
        else mistake+=1
    }
    if (mistake>0) return ""
    else {
        for (symbol in phone_number) {
            if (symbol in check_string_2) result_string+=symbol
        }
    }
    return result_string
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
    val results=jumps.split(" ")
    val jumps1=jumps
    val symbols= mutableListOf("-", "%")
    val final_results= mutableListOf<Int>()
    var mistake=0
    if (jumps1=="") return -1
    for (a in results) {
        if ((("0" in a) || ("1" in a) || ("2" in a) || ("3" in a) || ("4" in a) || ("5" in a) || ("6" in a) || ("7" in a) || ("8" in a) || ("9" in a) || (a in symbols))) mistake+=0
        else mistake+=1
    }
    if (mistake==0) {
        for (element in results) {
            if (element in symbols) final_results.add(0)
            else final_results.add(element.toInt())
        }
        return final_results.max()
    }
    else return -1
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
fun plusMinus(expression: String): Int = TODO()

/**
 * Сложная (6 баллов)
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val stroka=str
    var k=0
    val list= mutableListOf<Int>()
    val words=str.split(" ")
    for (i in 0 until words.size-1) {
        if (words[i] == words[i + 1]) {
            val s = words[i]
            val symbol = s[0]
            k += 1
            list.add(stroka.indexOf(symbol))
        }
    }
    if (k>0) return list[0]
    else return -1
}

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
    val list_of_items=description.split("; ")
    var prices= mutableListOf<Int>()
    for (item in list_of_items) {
        val name_and_cost=item.split(" ")
        prices= (prices + name_and_cost[1].toInt()) as MutableList<Int>
    }
    val s=""
    if ("-" in description) return s
    else return (prices.max().toString())
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
