import java.io.InputStreamReader

/*
 * Helper class for use with the MockServer we use in unit tests.
 * Reads the json file specified under the Apps Resources folder.
 */
class MockResponseFileReader(path: String) {

    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}