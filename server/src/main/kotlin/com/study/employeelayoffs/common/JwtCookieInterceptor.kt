package com.study.employeelayoffs.common

import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtCookieInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val methodAnnotation = handler.method.getAnnotation(RequiresCookie::class.java)
            val classAnnotation = handler.beanType.getAnnotation(RequiresCookie::class.java)
            if (methodAnnotation == null && classAnnotation == null) {
                return true // Skip cookie check for methods and classes without the @RequiresCookie annotation
            }
            val cookie = request.cookies?.find { it.name == "jwt" }
            if (cookie == null) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Not logged in")
                response.writer.write("Not logged in")
                return false
            }
        }
        return true
    }
}