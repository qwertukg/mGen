package sqlInfix

import java.lang.StringBuilder

class Query {
    private val result = StringBuilder()
    fun add(value: Any) = apply { result.append(value.toString()) }
    override fun toString() = result.toString()
}

object SQL

class Select(val query: Query)
class From(val query: Query)
class Where(val query: Query)
class Equals(val query: Query)
class Update(val query: Query)
class Set(val query: Query)
class To(val query: Query)

infix fun Where.equals(value: String) = Equals(query.add("='$value'"))
infix fun Where.equals(value: Int) = Equals(query.add("=$value"))
infix fun Where.notEquals(value: String) = Equals(query.add("!='$value'"))
infix fun Where.notEquals(value: Int) = Equals(query.add("!=$value"))
infix fun Equals.and(column: String) = Where(query.add(" AND `$column`"))
infix fun Equals.or(column: String) = Where(query.add(" OR `$column`"))

infix fun SQL.select(column: String) = Select(Query().add("SELECT `$column`"))
infix fun Select.and(column: String) = Select(query.add(", `$column`"))
infix fun Select.from(table: String) = From(query.add(" FROM `$table`"))
infix fun From.where(column: String) = Where(query.add(" WHERE `$column`"))

infix fun SQL.update(table: String) = Update(Query().add("UPDATE `$table`"))
infix fun Update.set(column: String) = Set(query.add(" SET `$column`"))
infix fun Set.to(value: String) = To(query.add("='$value'"))
infix fun Set.to(value: Int) = To(query.add("=$value"))
infix fun To.and(column: String) = Set(query.add(", `$column`"))
infix fun To.where(column: String) = Where(query.add(" WHERE `$column`"))

