
// package com.bank.Config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;

// @Configuration
// @EnableWebSecurity
// public class MyConfig extends WebSecurityConfigurerAdapter {


//     @Autowired
//     private UserDetailsService userDetailsService;

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .authorizeRequests()
//                 .antMatchers(HttpMethod.POST,"/bank/**").permitAll()
//                 .antMatchers("/branches").hasRole("USER")
//                 .anyRequest().authenticated()
//                 .and()
//             .httpBasic();
//     }

// //     @Override
// //     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// //         auth.userDetailsService(userDetailsService)
// //             .passwordEncoder(passwordEncoder());
// //     }

// //     @Autowired
// //     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// //         auth
// //             .inMemoryAuthentication()
// //                 .withUser("user").password("{noop}password").roles("USER")
// //                 .and()
// //                 .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
// //     }
// //     @Bean
// //     public PasswordEncoder passwordEncoder() {
// //         return new BCryptPasswordEncoder();
// //     }
// }


// // @Configuration
// // @EnableWebSecurity
// // public class MyConfig extends WebSecurityConfigurerAdapter {
    
// //     @Override
// //     protected void configure(HttpSecurity httpSecurity) throws Exception{
// //         httpSecurity.authorizeRequests()
// //                     // .antMatchers("/bank").hasRole("ADMIN")
// //                     .anyRequest()
// //                     .authenticated()
// //                     .and()
// //                     .httpBasic();
// //     } 
// //     // @Autowired
// //     // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// //     //     auth
// //     //         .inMemoryAuthentication()
// //     //             .withUser("user").password("{noop}password").roles("USER")
// //     //             .and()
// //     //             .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
// //     // }
// // }
