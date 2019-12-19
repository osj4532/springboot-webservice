package org.example.springboot.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
    /*
        - Mybatis 등에서 Dao라고 불리는 DB Layer 접근자

        - Jpa에서 Repository라고 부르며 인터페이스로 생성한다.

        - 단순한 인터페이스 생성 후 JapRepository<Entity 클래스, PK 타입>를 상속하면
          기본적인 CRUD 메소드가 자동으로 생성됩니다.

        - @Repository를 추가할 필요가 없다.

        - 주의점 : Entity클래스와 기본 Entity Repository는 함께 위치해야 한다.(도메인 페키지에서 함께 관리한다.)
     */
}
