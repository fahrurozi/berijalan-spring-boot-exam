package com.techno.technicaltestauthservice.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import java.text.DateFormat
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class JWTGenerator {
    companion object {
        private const val SECRET_KEY = "SUPER_SECRETE"
        private val instance: JWTGenerator = JWTGenerator()
    }

    val log = LoggerFactory.getLogger(this::class.java)
    fun createJWT(id: Int, subject: String): List<String> {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()
        val now = Date(nowMills)

        val apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)
        val expMills = nowMills + 10000L

        val builder: JwtBuilder = Jwts.builder().setId(id.toString())
            .setIssuedAt(now)
            .setSubject(subject)
            .signWith(signatureAlgorithm, signingKey)
            .setExpiration(Date(expMills))

        val token = builder.compact()
        return listOf(token, (expMills-nowMills).toString())
    }

    fun decodeJWT(jwt: String): Claims{
       try {
           val claims: Claims = Jwts.parser()
               .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
               .parseClaimsJws(jwt).body

           log.info("ID : ${claims.id}")
           log.info("Issuer : ${claims.issuer}")
           log.info("Subject : ${claims.subject}")
           return claims
       }catch (e: Exception) {
           throw e
       }
    }

    fun isExpired(jwt: String): Boolean{
        try {
            val claims: Claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).body
            val exp = claims.expiration
            val now = Date(System.currentTimeMillis())
            log.info("Exp : ${claims.expiration}")
            log.info("Exp : ${claims.expiration.time}")
//            return exp.before(now).toString()
            return false
        }catch (e: Exception){
            return true
        }
    }
}