package lesson12.task1
/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная, общая ценность в баллах -- 20.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
@Suppress("UNCHECKED_CAST", "KotlinConstantConditions")
class OpenHashSet<T>(val capacity: Int) {
    companion object {
        val UNINITIALIZED = Any()
    }
    override fun hashCode(): Int {
        var output = 1
        elements.forEach { pos ->
            if (pos != UNINITIALIZED) output += pos.hashCode()
        }
        output = 31 * output + size
        return output
    }

    private fun hash(element: T) = element.hashCode() % capacity

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { UNINITIALIZED }


    /**
     * Число элементов в хеш-таблице
     */
    var size: Int = 0
        private set

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean = size == 0

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        when {
            size == capacity -> return false
            elements[hash(element)] == UNINITIALIZED -> elements[hash(element)] = element
            else -> {
                (hash(element)..hash(element) + capacity).forEach { j ->
                    if (elements[j % capacity] == element) return false
                    when (UNINITIALIZED) {
                        elements[j % capacity] -> {
                            elements[j % capacity] = element
                            size += 1
                            return true
                        }
                    }
                }
            }
        }
        size += 1
        return true
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean = element in elements

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (!(other is OpenHashSet<*> && other.size == size)) return false
        else for (element in elements)
            if (element == UNINITIALIZED || contains(element as T)) return true
        return false
    }
}