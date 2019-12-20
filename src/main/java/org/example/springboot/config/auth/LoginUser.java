package org.example.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션이 생성될 수 있는 위치를 지정
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//이 파일을 어노테이션 클래스로 지정
public @interface LoginUser {
}
