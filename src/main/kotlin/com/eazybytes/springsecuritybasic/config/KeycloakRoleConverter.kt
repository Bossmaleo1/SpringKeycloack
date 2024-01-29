package com.eazybytes.springsecuritybasic.config

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import java.util.stream.Collectors


class KeycloakRoleConverter :
    Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmAccess = jwt.claims["realm_access"] as Map<*, *>?

        if (realmAccess == null || realmAccess.isEmpty()) {
            return ArrayList()
        }

        val returnValue = (realmAccess["roles"] as kotlin.collections.List<*>)
            .stream()
            .map { roleName -> "ROLE_$roleName" }
            .map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            .collect(Collectors.toList())

        return returnValue
    }
}