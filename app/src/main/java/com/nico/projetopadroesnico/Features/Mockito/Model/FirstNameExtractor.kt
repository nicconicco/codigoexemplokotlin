package com.nico.projetopadroesnico.Features.Mockito.Model

open class FirstNameExtractor {
    open fun extractFirstName(fullName: String?) : String? {

        if (fullName == null || fullName.isEmpty()) {
            return ""
        }

        val arrayOf = fullName.split(" ")

        for(i in arrayOf) {
            if(!i.isEmpty()) {
                return i
            }
        }

        return null
    }
}