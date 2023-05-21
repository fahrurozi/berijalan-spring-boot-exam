package com.techno.technicaltestauthservice.util

import com.techno.technicaltestauthservice.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional
import java.text.DateFormat
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter

class JWTGenerator(
    val userRepository: UserRepository
) {

    companion object {
        private const val SECRET_KEY = "SUPER_SECRETE"
//        private val instance: JWTGenerator = JWTGenerator()
    }

    val log = LoggerFactory.getLogger(this::class.java)

    open fun createJWT(id: Int, subject: String): List<String?> {
        val user = userRepository.findUsername(subject)
        log.info("===============")
        log.info(user.toString())
        log.info(user?.token.toString())
        if(user?.token != null){
            log.info("condition : If 1")
            val isExpired = isExpired(user?.token!!)
            if(!isExpired && user.token!=null){
                log.info("condition : If 2")
                val claims: Claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .parseClaimsJws(user?.token!!).body
                return listOf(user?.token, (claims.expiration.time-System.currentTimeMillis()).toString())
            }
        }
        log.info("condition : Outside")
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
        user?.token = token
        userRepository.save(user!!)
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
        if(jwt==null) return true
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