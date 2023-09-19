package rs.ac.singidunum.workout.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static rs.ac.singidunum.workout.enums.PermissionEnum.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static rs.ac.singidunum.workout.enums.RoleEnum.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf().disable()
                .authorizeHttpRequests()

                /* Permit pass */
                .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login", "/products/all", "/products/create", "/users/create", "/api/v1/auth/refresh-token", "/v3/api-docs",
                        "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources", "/swagger-resources/**", "/v2/api-docs")
                .permitAll()

                /* ROLES */
                .requestMatchers("/api/v1/admin/**").hasRole(Admin.name())
                .requestMatchers("/api/v1/employee/**").hasAnyRole(Admin.name(), Employee.name())
                .requestMatchers("/users/**").hasAnyRole(Admin.name(),User.name())
                .requestMatchers("/api/v1/exercise/**").hasRole(Admin.name())
                .requestMatchers("/api/v1/plan/**").hasAnyRole(Admin.name(), User.name())

                /* Authorities */

                //plans
                .requestMatchers(GET, "/api/v1/plan/all").hasAuthority(UserRead.name())
                .requestMatchers(GET, "/api/v1/plan/get/**").hasAuthority(UserRead.name())
                .requestMatchers(GET, "/api/v1/plan/user/**").hasAuthority(UserRead.name())
                .requestMatchers(POST, "/api/v1/plan/create").hasAuthority(UserCreate.name())
                .requestMatchers(DELETE, "/api/v1/plan/delete/**").hasAuthority(UserDelete.name())


                //exercises
                .requestMatchers(GET, "/exercise/all").hasAuthority(AdminRead.name())
                .requestMatchers(GET, "/exercise/get/**").hasAuthority(AdminRead.name())
                .requestMatchers(POST, "/exercise/create").hasAuthority(AdminCreate.name())
                .requestMatchers(DELETE, "/exercise/delete/**").hasAuthority(AdminDelete.name())

                //users
                .requestMatchers(GET,"/users/all").hasAuthority(UserRead.name())
                .requestMatchers(GET,"/users/all").hasAuthority(AdminRead.name())

                //admin
                .requestMatchers(GET, "/api/v1/admin/all").hasAuthority(AdminRead.name())
                .requestMatchers(POST, "/api/v1/admin/create").hasAuthority(AdminCreate.name())
                .requestMatchers(PUT, "/api/v1/admin/update/**").hasAuthority(AdminUpdate.name())
                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(AdminDelete.name())


                //employe
                .requestMatchers(GET, "/api/v1/employee/**").hasAnyAuthority(AdminRead.name(), EmployeeRead.name())
                .requestMatchers(POST, "/api/v1/employee/**").hasAnyAuthority(AdminCreate.name(), EmployeeCreate.name())
                .requestMatchers(PUT, "/api/v1/employee/**").hasAnyAuthority(AdminUpdate.name(), EmployeeUpdate.name())
                .requestMatchers(DELETE, "/api/v1/employee/**").hasAnyAuthority(AdminDelete.name(), EmployeeDelete.name())
                .anyRequest()
                .authenticated()

                /* Other settings */
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .and()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList(
                "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since",
                "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
