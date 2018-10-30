import com.google.common.base.CaseFormat
import org.apache.commons.csv.CSVFormat
import java.io.FileReader


fun main(args: Array<String>) {
    val input = FileReader("/Users/altynbank/Desktop/projects/generator/src/old.sqlInfix.main/resources/Transactions2.csv")
    CSVFormat.DEFAULT.parse(input).forEach {
        val comment = if (it[4].isNotBlank()) " // " + it[4].trim().capitalize().replace("\n", " ") else ""
        val name = it[0].toCamelCase().trim()
        val desc = it[1].capitalize().replace("\n", " ")
        val required = if (it[2] == "=+") "@JsonProperty(required = true)\n" else ""
        val type = it[3].capitalize().trim()

        val result = "$required@ApiModelProperty(notes = \"$desc\")\n$type $name;$comment\n"
        println(result)
    }
}

fun String.toCamelCase(): String = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, this)



