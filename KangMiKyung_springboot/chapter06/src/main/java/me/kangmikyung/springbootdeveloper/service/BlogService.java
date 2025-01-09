package me.kangmikyung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kangmikyung.springbootdeveloper.domain.Article;
import me.kangmikyung.springbootdeveloper.dto.AddArticleRequest;
import me.kangmikyung.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service                    // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 매서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
    /*
        @Service : 해당 클래스를 반으로 서블릿 컨테이너에 등록해줍니다.
        save() : JpaRepository에서 지원하는 저장 메서드 save()로,
            AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장합니다.

        -> 글을 생성하는 서비스 계층에서의 코드 작성이 완료

        -> 컨트롤러 메서드 코드 작성하기
            URL에 매핑하기 위한 컨드롤러 메서드를 추가할겁니다. -> 컨트롤러 메서드 구현 자체는 한 적있는데,
            컨트롤러 메서드에는 URL 매핑 애너테이션인
            @PostMapping
            @GetMapping
            @PutMapping
            @DeleteMapping 등을 사용할 수 있습니다.
            이름에서 유추할 수 있듯 각 애너테이션들은 HTTP 메서드와 대응합니다.

            /api/articles에 POST 요청이 들어오면 @PostMapping을 이용해 요청을 매핑한 뒤,
            블로그 글을 생성하는 BlogService의 save() 메서드를 호출
                -> 생성된 블로그 글을 반환하는 작업을 하는 addArticle() 메서드를 작성할 예정.

            지시사항
            service와 동일한 라인에 controller 패키지 생성한 뒤에, BlogApiController.java 생성
     */


    public List<Article> findAll() {
        return blogRepository.findAll();
    }
    /*
        JPA 지원 메서드인 findAll()을 호출해 article 테이블에 저장되어 있는 모든 데이트를 조회 -> Test에서 이미 해봤습니다.

        응답을 위한 DTO 생성 -> dto package에 -> ArticleResponse.java


     */

    // id로 특정 글 조회
    public Article findById(long id) { // 이 경우 결과값은 하나밖에 없기 때문에 리턴 타입이 Article이어야만 합니다. -> id가 PK라서
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id)); // 람다식
    }

    // BlogApiController.java로 이동

    // 삭제 메서드 정의
    public void delete(long id) {
        blogRepository.deleteById(id);
    }
    // 컨트롤러로 가면 됩니다. /api/articles/{id}


}











