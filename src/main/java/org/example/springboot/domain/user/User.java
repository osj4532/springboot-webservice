package org.example.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.posts.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    //JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정, 기본 int
    //int면 DB에서 무슨 코드인지 알수 없기 때문에 String으로 저장될수 있게한다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public User guestToUser(){
        this.role = Role.USER;
        return this;
    }

    public String getRoleKey(){
        return role.getKey();
    }
}
