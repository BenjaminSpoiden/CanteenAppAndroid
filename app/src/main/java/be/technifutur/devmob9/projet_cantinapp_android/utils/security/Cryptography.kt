package be.technifutur.devmob9.projet_cantinapp_android.utils.security

import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

fun String.MD5Hashing(): String{
    val MD5 = MessageDigest.getInstance("MD5")
    return BigInteger(1, MD5.digest(toByteArray())).toString(16).padStart(32, '0').toUpperCase(Locale.ROOT)
}