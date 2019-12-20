package org.example.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 어노테이션이 생성될 수 있는 위치를 지정
// PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할수 있다.
@Target(ElementType.PARAMETER)
// 어노테이션이 얼마나 오랫동안 유지되는지 설정
@Retention(RetentionPolicy.RUNTIME)
// @interface 이파일을 어노테이션 클래스로 지정(LoginUser 어노테이션 생성)
public @interface LoginUser{

}