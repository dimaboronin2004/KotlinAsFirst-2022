@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1


import java.io.File
import java.lang.StringBuilder

// Урок 7: работа с файлами
// Урок интегральный, поэтому его задачи имеют сильно увеличенную стоимость
// Максимальное количество баллов = 55
// Рекомендуемое количество баллов = 20
// Вместе с предыдущими уроками (пять лучших, 3-7) = 55/103

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    var currentLineLength = 0
    fun append(word: String) {
        if (currentLineLength > 0) {
            if (word.length + currentLineLength >= lineLength) {
                writer.newLine()
                currentLineLength = 0
            } else {
                writer.write(" ")
                currentLineLength++
            }
        }
        writer.write(word)
        currentLineLength += word.length
    }
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            writer.newLine()
            if (currentLineLength > 0) {
                writer.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(Regex("\\s+"))) {
            append(word)
        }
    }
    writer.close()
}

/**
 * Простая (8 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Некоторые его строки помечены на удаление первым символом _ (подчёркивание).
 * Перенести в выходной файл с именем outputName все строки входного файла, убрав при этом помеченные на удаление.
 * Все остальные строки должны быть перенесены без изменений, включая пустые строки.
 * Подчёркивание в середине и/или в конце строк значения не имеет.
 */
fun deleteMarked(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val lines = File(inputName).readLines()
    for (line in lines) {
        if (!line.startsWith("_")) {
            writer.write(line)
            writer.newLine()
        }
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val text = File(inputName).readText().lowercase()
    val finalList = mutableMapOf<String, Int>()
    for (substring in substrings) {
        val st = mutableSetOf<Int>()
        for (j in text.indices) {
            val a = text.lowercase().indexOf(substring.lowercase(), j)
            st.add(a)
        }
        if (!text.endsWith(substring)) finalList[substring] = st.size - 1
        else finalList[substring] = st.size
    }
    return finalList
}


/**
 * Средняя (12 баллов)
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}


/**
 * Средняя (15 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val lines = File(inputName).readLines()
    var maxLength = -1
    for (line in lines) {
        if (line.trim().length > maxLength) maxLength = line.trim().length
    }
    for (line in lines) {
        writer.write("${" ".repeat((maxLength - line.trim().length) / 2)}${line.trim()}")
        writer.newLine()
    }
    writer.close()
}

/**
 * Сложная (20 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val lines = File(inputName).readLines()
    var max = -1
    for (line in lines) {
        if (line.trim().length > max) max = line.trim().length
    }
    for (line in lines) {
        if (line.isBlank()) writer.newLine()
        else {
            val splitted = line.trim().split(" ").toMutableList()
            if (splitted.size == 1) {
                writer.write(line.trim())
                writer.newLine()
            } else {
                var counter = 0
                for (element in splitted) {
                    counter += element.trim().length
                }
                val splitted1 = mutableListOf<String>()
                for (element in splitted) {
                    if (element.isNotEmpty()) splitted1.add(element)
                }
                val builder = StringBuilder()
                val average = (max - counter) / (splitted1.size - 1)
                var rest = (max - counter) % (splitted1.size - 1)
                for (str in splitted1) {
                    if (line.trim().length != max) {
                        if (rest > 0) builder.append("${str.trim()}${" ".repeat(average + 1)}")
                        else builder.append("${str.trim()}${" ".repeat(average)}")
                        rest -= 1
                    } else builder.append("${str.trim()} ")
                }
                writer.write(builder.toString().trim())
                writer.newLine()
            }
        }
    }
    writer.close()
}

/**
 * Средняя (14 баллов)
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 * Вернуть ассоциативный массив с числом слов больше 20, если 20-е, 21-е, ..., последнее слова
 * имеют одинаковое количество вхождений (см. также тест файла input/onegin.txt).
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя (14 баллов)
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val strings = File(inputName).readLines()
    val listOfLengths = mutableListOf<Int>()
    val listOfWords = mutableListOf<String>()
    val resultList = mutableListOf<String>()
    for (string in strings) {
        if (string.length == string.lowercase().toSet().size) {
            listOfWords.add(string)
            listOfLengths.add(string.length)
        }
    }
    for (word in listOfWords) {
        if (word.length == listOfLengths.max()) resultList.add(word)
    }
    if (resultList.size == 1) writer.write(resultList.joinToString())
    else writer.write(resultList.joinToString(separator = ", "))
    writer.close()
}

/**
 * Сложная (22 балла)
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    writer.write("<html>")
    writer.write("<body>")
    writer.write("<p>")
    val text = File(inputName).readText()
    val builder = StringBuilder()
    val stack = mutableListOf<String>()
    val forStack = mapOf("*" to "!", "**" to "?", "***" to "%", "~~" to "#", "\n" to "&")
    var i = 0
    while (i < text.toCharArray().size ) {
        if (text.toCharArray()[i] == '~' && text.toCharArray()[i + 1].toString() == "~") {
            if (stack.isEmpty() || stack.last() != forStack["~~"]) {
                builder.append("<s>")
                stack.add(forStack["~~"]!!)
                i += 2
                continue
            }
            if (stack.last() == forStack["~~"]) {
                builder.append("</s>")
                stack.remove(forStack["~~"])
                i += 2
                continue
            }
        } else if (text.toCharArray()[i] == '*' && text.toCharArray()[i + 1]!= '*') {
            if (stack.isEmpty() || stack.last() != forStack["*"]) {
                builder.append("<i>")
                stack.add(forStack["*"]!!)
                i += 1
                continue
            }
            if (stack.last() == forStack["*"]) {
                builder.append("</i>")
                stack.remove(forStack["*"])
                i += 1
                continue
            }
        } else if (text.toCharArray()[i] == '*' && text.toCharArray()[i + 1] == '*' && text.toCharArray()[i + 2] != '*') {
            if (stack.isEmpty() || stack.last() != forStack["**"]) {
                builder.append("<b>")
                stack.add(forStack["**"]!!)
                i += 2
                continue
            }
            if (stack.last() == forStack["**"]) {
                builder.append("</b>")
                stack.remove(forStack["**"])
                i += 2
                continue
            }
        } else if (text.toCharArray()[i] == '*' && text.toCharArray()[i + 1] == '*' && text.toCharArray()[i + 2] == '*'){
            if (stack.isEmpty() || stack.last() != forStack["***"] ) {
                builder.append("</b></i>")
                stack.add(forStack["***"]!!)
                i += 3
                continue
            }
            if (stack.last() == forStack["***"]) {
                builder.append("<b><i>")
                stack.remove(forStack["***"])
                i += 3
                continue
            }
        } else if (text.toCharArray()[i].code == 13 && text.toCharArray()[i + 1].code == 10
            && text.toCharArray()[i + 2].code == 13 && text.toCharArray()[i + 3].code == 10) {
            builder.append("</p><p>")
            i += 4
            continue
        } else {
            builder.append(text.toCharArray()[i])
            i += 1
            continue
        }
    }
    writer.write(builder.toString())
    writer.write("</p>")
    writer.write("</body>")
    writer.write("</html>")
    writer.close()
}

/**
 * Сложная (23 балла)
 *
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body><p>...</p></body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
2. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<p>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>Или колбаса</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>Фрукты
<ol>
<li>Бананы</li>
<li>Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</p>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    val strings = File(inputName).readLines()
    val stack = mutableListOf<String>()
    val builder = StringBuilder()
    stack.add((strings[0].trimStart()).first().toString())
    builder.append("<html><body><p>")
    if (isOrdered((strings[0].trimStart()))) builder.append("<ol>") else builder.append("<ul>")
    builder.append(tagged(strings[0].trimStart().substring(2, strings[0].trimStart().length)))
    for (i in 1 until strings.size) {
        if (countWhitespaces(strings[i]) > countWhitespaces(strings[i - 1])) {

            stack.add(strings[i].trimStart().first().toString())
            if (isOrdered(strings[i].trimStart())) builder.append("<ol>") else builder.append("<ul>")
            builder.append(tagged(strings[i].trimStart().substring(1, strings[i].trimStart().length)))
            continue
        } else if (countWhitespaces(strings[i]) == countWhitespaces(strings[i - 1])) {
            builder.append(tagged(strings[i].trimStart().substring(1, strings[i].trimStart().length)))
            continue
        } else {
            val c = pop(stack)
            if (c == "*") builder.append("</ul>") else builder.append("</ol>")
            builder.append(tagged(strings[i].trimStart().substring(1, strings[i].trimStart().length)))
            continue
        }
    }
    while (stack.isNotEmpty()) {
        if (pop(stack) == "*") builder.append("</ul>") else builder.append("</ol>")
    }
    builder.append("</p></body></html>")
    writer.write(builder.toString())
    writer.close()
}

fun countWhitespaces(string: String): Int {
    var k = 0
    for (char in string) {
        if (char == ' ') k += 1
    }
    return k
}

fun pop(stack: MutableList<String>): String {
    var a = stack.last()
    stack.remove(a)
    return stack.last()
}

fun isOrdered(string: String): Boolean = !string.startsWith("*")
fun tagged(string: String): String = "<li>$string</li>"

/**
 * Очень сложная (30 баллов)
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя (12 баллов)
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная (25 баллов)
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    val writer = File(outputName).bufferedWriter()
    if (lhv >= rhv) {
        val digits = lhv.toString().split("").toMutableList()
        digits.remove(digits.first())
        digits.remove(digits.last())
        var main = 0
        while (main < rhv) {
            main = main * 10 + digits[0].toInt()
            digits.remove(digits[0])
        }
        var space = main.toString().length + 1
        val begin = digits.size
        digits.add("0")
        val list = mutableListOf<String>()
        while (digits.isNotEmpty()) {
            list.add(("-" + (rhv * (main / rhv))))
            list.add("-".repeat((rhv * (main / rhv)).toString().length + 1))
            main = (main % rhv) * 10 + digits[0].toInt()
            if (main >= 10) list.add(main.toString())
            else list.add("0$main")
            digits.remove(digits[0])
        }
        writer.write(" $lhv | $rhv\n")
        writer.write("${list.first()}${" ".repeat(begin + 3)}${lhv / rhv}\n")
        for (i in 1 until list.size - 1) {
            if ((i + 1) % 3 == 0) space += 1
            writer.write("${" ".repeat(space - list[i].length)}${list[i]}\n")
        }
        writer.write("${" ".repeat(space - list.last().length + 1)}${lhv % rhv}")
    } else {
        writer.write(" $lhv | $rhv\n")
        writer.write("${" ".repeat(lhv.toString().length - 1)}-0   ${(lhv / rhv)}\n")
        writer.write("${"-".repeat(lhv.toString().length + 1)}\n")
        writer.write(" $lhv")
    }
    writer.close()
}


