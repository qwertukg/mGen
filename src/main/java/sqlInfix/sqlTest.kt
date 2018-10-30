package sqlInfix

fun main(args: Array<String>) {

    val sql1 = SQL select "first_name" and "last_name" from "users" where "id" equals 1 and "role" notEquals "admin"
    println(sql1.query)

    val sql2 = SQL update "users" set "first_name" to "Ivan" and "age" to 3 where "id" equals 1 and "role" equals "admin" or "f" notEquals 4
    println(sql2.query)

}