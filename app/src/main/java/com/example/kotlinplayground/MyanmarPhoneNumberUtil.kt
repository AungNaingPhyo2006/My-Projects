package com.example.kotlinplayground

// MyanmarPhoneNumberUtil.kt

class MyanmarPhoneNumberUtil {

    private val myanmarNumbers = mapOf(
        "၀" to 0, "၁" to 1, "၂" to 2, "၃" to 3, "၄" to 4,
        "၅" to 5, "၆" to 6, "၇" to 7, "၈" to 8, "၉" to 9
    )

    private val operatorRegex = mapOf(
        "ooredoo" to Regex("^(09|\\+?959)9([4-9])\\d{7}\$"),
        "telenor" to Regex("^(09|\\+?959)7([4-9])\\d{7}\$"),
        "mytel" to Regex("^(09|\\+?959)6([5-9])\\d{7}\$"),
        "mpt" to Regex("^(09|\\+?959)(5\\d{6}|4\\d{7,8}|2\\d{6,8}|6\\d{6}|8\\d{6}|7\\d{7}|9(0|1|9)\\d{5,6}|2[0-4]\\d{5}|5[0-6]\\d{5}|8[13-7]\\d{5}|4[1379]\\d{6}|73\\d{6}|91\\d{6}|25\\d{7}|26[0-5]\\d{6}|40[0-4]\\d{6}|42\\d{7}|45\\d{7}|89[6789]\\d{6}|)\$"),
        "mec" to Regex("^(09|\\+?959)(3\\d{7,8}|3[0-369]\\d{6}|34\\d{7})")
    )

    private val operators = mapOf(
        "OOREDOO" to "Ooredoo",
        "TELENOR" to "Telenor",
        "MPT" to "MPT",
        "MEC" to "MEC",
        "MYTEL" to "MyTel",
        "UNKNOWN" to "Unknown"
    )

    private val networkTypes = mapOf(
        "GSM" to "GSM",
        "WCDMA" to "WCDMA",
        "CDMA_450" to "CDMA 450 MHz",
        "CDMA_800" to "CDMA 800 MHz",
        "UNKNOWN" to "Unknown"
    )

    fun sanitizeInput(phoneNumber: String?): String {
        if (phoneNumber.isNullOrBlank()) {
            throw IllegalArgumentException("Please include phoneNumber parameter.")
        }

        var sanitizedNumber = phoneNumber.trim().replace(Regex("[\\- )(]"), "")

        val countryCodeRe = Regex("^\\+?950?9\\d+\$")
        if (countryCodeRe.matches(sanitizedNumber)) {
            val doubleCountryCodeRe = Regex("^\\+?95950?9\\d{7,9}\$")
            if (doubleCountryCodeRe.matches(sanitizedNumber)) {
                sanitizedNumber = sanitizedNumber.replace("9595", "95")
            }
            val zeroBeforeAreaCodeRe = Regex("^\\+?9509\\d{7,9}\$")
            if (zeroBeforeAreaCodeRe.matches(sanitizedNumber)) {
                sanitizedNumber = sanitizedNumber.replace("9509", "959")
            }
        }
        return sanitizedNumber
    }

    fun normalizeInput(phoneNumber: String?): String {
        val sanitizedNumber = sanitizeInput(phoneNumber)
        val possibleCases = Regex("^((09-)|(\\+959)|(09\\s)|(959)|(09\\.))")

        if (possibleCases.matches(sanitizedNumber)) {
            return sanitizedNumber.replace(possibleCases, "09")
        }

        if (sanitizedNumber.any { it in '၀'..'၉' }) {
            return sanitizedNumber
                .map { myanmarNumbers[it.toString()]?.toString() ?: it.toString() }
                .joinToString("")
                .replace(possibleCases, "09")
        }

        return sanitizedNumber
    }

    fun isValidMMPhoneNumber(phoneNumber: String?): Boolean {
        val normalizedNumber = normalizeInput(phoneNumber)
        val myanmarPhoneRe = Regex("^(09|\\+?950?9|\\+?95950?9)\\d{7,9}\$")
        return myanmarPhoneRe.matches(normalizedNumber)
    }

    fun getTelecomName(phoneNumber: String?): String {
        if (phoneNumber != null && isValidMMPhoneNumber(phoneNumber)) {
            val normalizedNumber = normalizeInput(phoneNumber)
            return when {
                operatorRegex["ooredoo"]!!.matches(normalizedNumber) -> operators["OOREDOO"] ?: "Unknown"
                operatorRegex["telenor"]!!.matches(normalizedNumber) -> operators["TELENOR"] ?: "Unknown"
                operatorRegex["mpt"]!!.matches(normalizedNumber) -> operators["MPT"] ?: "Unknown"
                operatorRegex["mec"]!!.matches(normalizedNumber) -> operators["MEC"] ?: "Unknown"
                operatorRegex["mytel"]!!.matches(normalizedNumber) -> operators["MYTEL"] ?: "Unknown"
                else -> operators["UNKNOWN"] ?: "Unknown"
            }
        }
        return operators["UNKNOWN"] ?: "Unknown"
    }

    fun getPhoneNetworkType(phoneNumber: String?): String {
        val networkType = if (phoneNumber != null && isValidMMPhoneNumber(phoneNumber)) {
            val normalizedNumber = normalizeInput(phoneNumber)
            when {
                operatorRegex["ooredoo"]!!.matches(normalizedNumber) ||
                        operatorRegex["telenor"]!!.matches(normalizedNumber) ||
                        operatorRegex["mytel"]!!.matches(normalizedNumber) -> networkTypes["GSM"]
                operatorRegex["mpt"]!!.matches(normalizedNumber) ||
                        operatorRegex["mec"]!!.matches(normalizedNumber) -> {
                    val wcdmaRe = Regex("^(09|\\+?959)(55\\d{5}|25[2-4]\\d{6}|26\\d{7}|4(4|5|6)\\d{7})\$")
                    val cdma450Re = Regex("^(09|\\+?959)(8\\d{6}|6\\d{6}|49\\d{6})\$")
                    val cdma800Re = Regex("^(09|\\+?959)(3\\d{7}|73\\d{6}|91\\d{6})\$")

                    when {
                        wcdmaRe.matches(normalizedNumber) -> networkTypes["WCDMA"]
                        cdma450Re.matches(normalizedNumber) -> networkTypes["CDMA_450"]
                        cdma800Re.matches(normalizedNumber) -> networkTypes["CDMA_800"]
                        else -> networkTypes["GSM"]
                    }
                }
                else -> networkTypes["GSM"]
            }
        } else {
            networkTypes["UNKNOWN"]
        }

        return networkType ?: "Unknown"
    }
}