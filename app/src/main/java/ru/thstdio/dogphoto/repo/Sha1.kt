package ru.thstdio.dogphoto.repo

import java.math.BigInteger
import java.security.MessageDigest

 val String.sha1: String
    get() {
        val m = MessageDigest.getInstance("SHA1")
        m.update(this.toByteArray(), 0, this.length)
        return BigInteger(1, m.digest()).toString(16)
    }