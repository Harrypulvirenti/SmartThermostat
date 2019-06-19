package com.tess.core.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import org.junit.Test
import java.util.Arrays

class ObjectParserImplTest {

    private val sut = ObjectParserImpl()

    @Test
    fun `Should parse json string to object`() {
        val json = "{\"string\":\"string\",\"int\":1,\"boolean\":true,\"long\":3}"

        val actual = sut.parseJson<TestJsonClass>(json)

        val expected = TestJsonClass("string", 1, true, 3, null)

        actual.fold(
            {
                fail()
            },
            {

                assertEquals(expected, it)
            }
        )
    }

    @Test
    fun `Should parse json string complex object`() {
        val json =
            "{\"string\":\"string\",\"int\":\"1\",\"boolean\":\"true\",\"long\":\"3\",\"obj\":{\"newString\":\"newString\",\"newInt\":\"3\"}}"

        val actual = sut.parseJson<TestJsonClass>(json)

        val expected = TestJsonClass(
            "string", 1, true, 3,
            TestInternalJsonClass(
                "newString",
                3
            )
        )

        actual.fold(
            {
                fail()
            },
            {

                assertEquals(expected, it)
            }
        )
    }

    @Test
    fun `Should fail parsing string without crash`() {
        val json = ""


        sut.parseJson<TestJsonClass>(json).fold(
            {

            },
            {

                fail()
            }
        )
    }

    @Test
    fun `Should parse json map to object`() {
        val json = mapOf(
            "string" to "string",
            "int" to "1",
            "boolean" to "true",
            "long" to "3"
        )

        val actual = sut.parseJson<TestJsonClass>(json)

        val expected = TestJsonClass("string", 1, true, 3, null)

        actual.fold(
            {
                fail()
            },
            {

                assertEquals(expected, it)
            }
        )
    }

    @Test
    fun `Should parse json map complex object`() {
        val json = mapOf(
            "string" to "string",
            "int" to "1",
            "boolean" to "true",
            "long" to "3",
            "obj" to mapOf(
                "newString" to "newString",
                "newInt" to "3"
            )
        )

        val actual = sut.parseJson<TestJsonClass>(json)

        val expected = TestJsonClass(
            "string", 1, true, 3,
            TestInternalJsonClass(
                "newString",
                3
            )
        )

        actual.fold(
            {
                fail()
            },
            {

                assertEquals(expected, it)
            }
        )
    }

    @Test
    fun `Should fail parsing map without crash`() {
        val json: Map<String, String> = mapOf()


        sut.parseJson<TestJsonClass>(json).fold(
            {

            },
            {

                fail()
            }
        )
    }

    @Test
    fun `Should convert object to json string`() {

        val actual = sut
            .toJSONString(TestJsonClass("string", 1, true, 3, null))

        val expected = "{\"string\":\"string\",\"int\":1,\"boolean\":true,\"long\":3}"

        actual.fold(
            {
                fail()
            },
            {

                assertEquals(expected, it)
            }
        )
    }

    @Test
    fun `Should convert object to json byte array`() {

        val actual = sut
            .toJSONBytes(TestJsonClass("string", 1, true, 3, null))

        val expected = "{\"string\":\"string\",\"int\":1,\"boolean\":true,\"long\":3}".toByteArray()

        actual.fold(
            {
                fail()
            },
            {

                assertTrue(Arrays.equals(expected, it))
            }
        )
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
private data class TestJsonClass(
    @JsonProperty("string") val string: String,
    @JsonProperty("int") val int: Int,
    @JsonProperty("boolean") val boolean: Boolean?,
    @JsonProperty("long") val long: Long,
    @JsonProperty("obj") val obj: TestInternalJsonClass? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
private data class TestInternalJsonClass(
    @JsonProperty("newString") val string: String? = null,
    @JsonProperty("newInt") val int: Int? = null
)