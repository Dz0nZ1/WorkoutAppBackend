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

import static rs.ac.singidunum.workout.enums.PermissionEnum.*;
import static rs.ac.singidunum.workout.enums.RoleEnum.Admin;
import static rs.ac.singidunum.workout.enums.RoleEnum.Employee;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;


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
                .requestMatchers("/api/v1/auth/register", "/api/v1/auth/login", "/products/all", "/products/create")
                .permitAll()
                .requestMatchers("/api/v1/admin/**").hasRole(Admin.name())
                .requestMatchers("/api/v1/employee/**").hasAnyRole(Admin.name(), Employee.name())
                .requestMatchers(GET, "/api/v1/admin/all").hasAuthority(AdminRead.name())
                .requestMatchers(POST, "/api/v1/admin/create").hasAuthority(AdminCreate.name())
                .requestMatchers(PUT, "/api/v1/admin/update/**").hasAuthority(AdminUpdate.name())
                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(AdminDelete.name())
                .requestMatchers(GET, "/api/v1/employee/**").hasAnyAuthority(AdminRead.name(), EmployeeRead.name())
                .requestMatchers(POST, "/api/v1/employee/**").hasAnyAuthority(AdminCreate.name(), EmployeeCreate.name())
                .requestMatchers(PUT, "/api/v1/employee/**").hasAnyAuthority(AdminUpdate.name(), EmployeeUpdate.name())
                .requestMatchers(DELETE, "/api/v1/employee/**").hasAnyAuthority(AdminDelete.name(), EmployeeDelete.name())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());


        return http.build();
    }
}
