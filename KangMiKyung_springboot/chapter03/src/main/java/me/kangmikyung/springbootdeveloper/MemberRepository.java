package me.kangmikyung.springbootdeveloper;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
