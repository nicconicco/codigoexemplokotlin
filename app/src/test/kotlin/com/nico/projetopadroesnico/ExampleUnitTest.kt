package com.nico.projetopadroesnico

import com.nico.projetopadroesnico.Features.Mockito.Model.FirstNameExtractor
import junit.framework.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {

    companion object {
        val NICO = "Nico"
    }

    @Test
    fun extractFirstName_NullInput_ReturnEmptyString() {
        Assert.assertEquals(FirstNameExtractor().extractFirstName(null), "")
    }

    @Test
    fun extractFirstName_EmptyString_ReturnEmptyString() {
        Assert.assertEquals(FirstNameExtractor().extractFirstName(""), "")
    }

    @Test
    fun extractFirstName_FullName_ReturnCorrect() {
        Assert.assertEquals(FirstNameExtractor().extractFirstName("Nico Galves"), NICO)
    }

    @Test
    fun extractFirstName_FullNameWithSpace_ReturnCorrect() {
        Assert.assertEquals(FirstNameExtractor().extractFirstName(" Nico Galves"), NICO)
        Assert.assertEquals(FirstNameExtractor().extractFirstName("Nico  Galves"), NICO)
        Assert.assertEquals(FirstNameExtractor().extractFirstName("Nico Galves "), NICO)
    }

    @Test
    fun extractFirstName_FirstName_ReturnCorrect() {
        Assert.assertEquals(FirstNameExtractor().extractFirstName("Nico"), NICO)
    }

    @Test
    fun extractFirstName_FirstNameWithSpace_ReturnCorrect() {
        Assert.assertEquals(FirstNameExtractor().extractFirstName("  Nico"), NICO)
        Assert.assertEquals(FirstNameExtractor().extractFirstName(" Nico"), NICO)
        Assert.assertEquals(FirstNameExtractor().extractFirstName("Nico "), NICO)
    }
}