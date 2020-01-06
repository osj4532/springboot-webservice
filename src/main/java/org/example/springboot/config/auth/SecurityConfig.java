package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console 화면을 사용하기 위해 해당 옵션들을 disable한다.
                .csrf().disable().headers().frameOptions().disable()
                .and()
                    //url별 권한 관리를 설정하는 옵션의 시작점
                    .authorizeRequests()
                    //antMatchers에 해당하는 url은 permitAll로 권한 상관없이 모두 허용
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    // /api/v1/** 주소를 가진 API는 User 권한을 가진 사람만 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    //위 설정된 값들을 제외한 나머지 URL은 인증된 사용자들만 허용
                    .anyRequest().authenticated()
                .and()
                    //logout 기능에 대한 여러 설정의 진입점
                    .logout()
                        //logout 성공시 /로 이동
                        .logoutSuccessUrl("/")
                .and()
                    //oauth2 로그인 기능에 대한 설정의 진입점
                    .oauth2Login()
                        //oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                        .userInfoEndpoint()
                            //로그인 성공 시 후속 조치를 진행할 user service인터페이스의 구현체를 등록
                            .userService(customOAuth2UserService);


    }
}
