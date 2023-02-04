package com.study.employeelayoffs.admin

import com.study.employeelayoffs.admin.AuthController.Companion.BASE_VERSION_URL
import com.study.employeelayoffs.admin.dto.LoginDto
import com.study.employeelayoffs.admin.dto.Message
import com.study.employeelayoffs.admin.dto.AdminService
import com.study.employeelayoffs.common.exception.NotFoundException
import com.study.employeelayoffs.common.exception.WrongPasswordException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = [BASE_VERSION_URL])
class AuthController(
    private val adminService: AdminService
) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDto): Admin {
        val admin = Admin()
        admin.name = body.name
        admin.email = body.email
        admin.password = body.password

        return this.adminService.save(admin)
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
        val admin = this.adminService.findByEmail(body.email)
            ?: throw NotFoundException(String.format("Admin with email %s not found", body.email))

        if (!admin.comparePassword(body.password)) {
            throw WrongPasswordException("Wrong password, please try again")
        }

        val issuer = admin.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Success!"))
    }

    @GetMapping("admin")
    fun admin(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("Not logged in"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.adminService.getById(body.issuer.toInt()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("Not logged in"))
        }
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Success"))
    }

    companion object {
        const val BASE_VERSION_URL: String = "api/v1"
    }
}