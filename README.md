# 🎶 MML (My Music List)

자신만의 음악 플레이리스트를 만들고 공유하기 위한 서비스


## 📝 기능 소개
1. 자신만의 음악 플레이리스트를 생성하여 음악을 추가, 삭제 가능
2. 회원 별로 음악 플레이리스트를 저장하여 공유 가능

## 🛠 기술 스택
- Spring Boot 3.3.4 / Kotlin
- zulu JDK 17
- Spring Data JPA
- MySQL
- Kotlin JDSL

## 🔗 ERD

![MML_ERD](https://github.com/user-attachments/assets/d1abddd5-577f-4441-aa4a-9daa935fb816)


## ⚙ API
### User
<details>
  
  <summary><code>GET /members</code> - 회원 조회</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {
    "userReponse" : [
      {
        "id" : 1,
        "email" : "ppusda@naver.com"
      },
      {
        "id" : 2,
        "email" : "ppusda1234@gmail.com"
      },
    ]
  }
  ```

</details>

<details>
  
  <summary><code>POST /members</code> - 회원 가입</summary>

  **Request**
  ```json
  {
    "email" : "ppusda@naver.com",
    "password" : "1234",
    "passwordCheck" : "1234"
  }
  ```

  **Response**e
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>DELETE /members/{n}</code> - n번 회원 탈퇴</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {}
  ```

</details>

### User-Sign

<details>
  
  <summary><code>POST /members/signs</code> - 회원 로그인</summary>

  **Request**
  ```json
  {
    "email" : "ppusda@naver.com",
    "password" : "1234"
  }
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>DELETE /members/signs</code> - 회원 로그아웃</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {}
  ```

</details>

### Music
<details>
  
  <summary><code>GET /musics</code> - 음악 조회</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {
    "musicResponse" : [
      {
        "id" : 1,
        "title" : "Gang Gang Schiele",
        "artist" : "혁오",
        "url" : "https://www.youtube.com/watch?v=WB4547-tSJA",
      },
      {
        "id" : 2,
        "title" : "멋진헛간 Remix",
        "artist" : "혁오",
        "url" : "https://www.youtube.com/watch?v=3DpL4UcCdWk"
      }
    ]
  }
  ```

</details>

<details>
  
  <summary><code>POST /musics</code> - 음악 등록</summary>

  **Request**
  ```json
  {
    "title" : "Gang Gang Schiele",
    "artist" : "혁오",
    "url" : "https://www.youtube.com/watch?v=WB4547-tSJA",
  }
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>PATCH /musics/{n}</code> - n번 음악 정보 수정</summary>

  **Request**
  ```json
  {
    "title" : "멋진헛간 Remix",
    "artist" : "혁오",
    "url" : "https://www.youtube.com/watch?v=3DpL4UcCdWk" 
  }
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>DELETE /musics/{n}</code> - n번 음악 삭제</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {}
  ```

</details>

### Playlist

<details>
  
  <summary><code>GET /playlists</code> - 플레이리스트 조회</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {
    "playlistResponse" : [
      {
        "id" : 1,
        "ownerEmail" : "ppusda@naver.com",
        "name" : "내가 자주 듣는 혁오 노래 모음집"
      },
      {
        "id" : 2,
        "ownerEmail" : "ppusda1234@gmail.com",
        "name" : "혁오"
      }
    ]
  }
  ```

</details>

<details>
  
  <summary><code>POST /playlists</code> - 플레이리스트 생성</summary>

  **Request**
  ```json
  {
    "name" : "내가 자주 듣는 혁오 노래 모음집"
  }
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>PATCH /playlists/{n}</code> - n번 플레이리스트 정보 수정</summary>

  **Request**
  ```json
  {
    "name" : "혁오"
  }
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>DELETE /playlists/{n}</code> - n번 플레이리스트 삭제</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {}
  ```

</details>

### Playlist-Music

<details>
  
  <summary><code>GET /playlists/{n}/musics</code> - n번 플레이리스트 조회</summary>

  **Request**
  ```json
  {
    "playlistMusicResponse" : {
      "id" : 1,
      "ownerEmail" : "ppusda@naver.com",
      "musics" : [
        {
          "id" : 1,
          "title" : "Gang Gang Schiele",
          "artist" : "혁오",
          "url" : "https://www.youtube.com/watch?v=WB4547-tSJA",
        },
        {
          "id" : 2,
          "title" : "멋진헛간 Remix",
          "artist" : "혁오",
          "url" : "https://www.youtube.com/watch?v=3DpL4UcCdWk"
        }
      ]
    }
  }
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>POST /playlists/{n}/musics</code> - n번 플레이리스트에 음악 추가</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {}
  ```

</details>

<details>
  
  <summary><code>DELETE /playlists/{n}/musics</code> - n번 플레이리스트에 음악 삭제</summary>

  **Request**
  ```json
  {}
  ```

  **Response**
  ```json
  {}
  ```

</details>

## 🏆 미션 결과물

<details>
  
  <summary>[미션 1] 테이블 설계하기 제출 스레드</summary>

  커밋 내용 => [Update README.md](https://github.com/ppusda/MML/commit/9964f8345a719f880f1355529373801b27f0d425)

</details>

<details>
  
  <summary>[미션 2] 깃허브 리포지토리에 프로젝트 올리기</summary>

  커밋 내용 => [init: initialize project](https://github.com/ppusda/MML/commit/8aeb1b11555e157b6f17439522feed22a96f8b0c)

</details>

<details>
  
  <summary>[미션 3] REST API 설계하기</summary>

  커밋 내용 => [docs: API 설계 - 요청/응답 전체 추가](https://github.com/ppusda/MML/commit/8b23bae06ec013b6e26c076f5f7697b4c32c8a13)

</details>

<details>
  
  <summary>[미션 4] 조회 REST API 만들기</summary>

  커밋 내용 => [feat: 조회 API 테스트 코드 작성](https://github.com/ppusda/MML/commit/497518e55bac5a840560496dd8f50ea39d87c940)
  
  <table>
    <tr>
      <th>음악 전체 조회</th>
      <td><img src="https://github.com/user-attachments/assets/65edf250-0345-4062-9baf-6d2d475827aa"/></td>
      <td>
        <pre>
          <code>
[
  {
    "title": "Gang Gang Schiele",
    "artist": "혁오",
    "url": "https://www.youtube.com/watch?v=Xjk3w7NcZAU"
  },
  {
    "title": "멋진헛간 Remix",
    "artist": "혁오",
    "url": "https://www.youtube.com/watch?v=3DpL4UcCdWk"
  },
  {
    "title": "Happy",
    "artist": "Day6",
    "url": "https://www.youtube.com/watch?v=VXp2dCXYrvQ"
  }
]
          </code>
        </pre>
      </td>
    </tr>
    <tr>
      <th>플레이리스트 전체 조회</th>
      <td><img src="https://github.com/user-attachments/assets/6a1b02b9-a5d2-4738-9cf6-38f43126a9cf"/></td>
      <td>
        <pre>
          <code>
[
  {
    "name": "혁오 노래 모음",
    "musics": [
      {
        "title": "Gang Gang Schiele",
        "artist": "혁오",
        "url": "https://www.youtube.com/watch?v=Xjk3w7NcZAU"
      },
      {
        "title": "멋진헛간 Remix",
        "artist": "혁오",
        "url": "https://www.youtube.com/watch?v=3DpL4UcCdWk"
      }
    ]
  },
  {
    "name": "내가 자주 듣는 노래",
    "musics": [
      {
        "title": "Gang Gang Schiele",
        "artist": "혁오",
        "url": "https://www.youtube.com/watch?v=Xjk3w7NcZAU"
      },
      {
        "title": "멋진헛간 Remix",
        "artist": "혁오",
        "url": "https://www.youtube.com/watch?v=3DpL4UcCdWk"
      },
      {
        "title": "Happy",
        "artist": "Day6",
        "url": "https://www.youtube.com/watch?v=VXp2dCXYrvQ"
      }
    ]
  }
]
          </code>
        </pre>
      </td>
    </tr>
    <tr>
      <th>N번 플레이리스트 조회</th>
      <td><img src="https://github.com/user-attachments/assets/acc33ae6-e7ad-4d28-84c4-75622253dda4"/></td>
      <td>
        <pre>
          <code>
{
  "name": "혁오 노래 모음",
  "musics": [
    {
      "title": "Gang Gang Schiele",
      "artist": "혁오",
      "url": "https://www.youtube.com/watch?v=Xjk3w7NcZAU"
    },
    {
      "title": "멋진헛간 Remix",
      "artist": "혁오",
      "url": "https://www.youtube.com/watch?v=3DpL4UcCdWk"
    }
  ]
}
          </code>
        </pre>
      </td>
    </tr>
  </table>

</details>

<details>
  
  <summary>[미션 5] 삽입, 수정, 삭제 REST API 만들기</summary>

  커밋 내용 => [feat: [미션5] 삽입, 수정, 삭제 REST API 만들기](https://github.com/ppusda/MML/commit/39f5ab22c7c6e1ff6f3c93b9826aef17d35cae7c)
  
  <table>
    <tr>
      <th>테스트 결과</th>
      <td><img src="https://github.com/user-attachments/assets/f21a8528-34b0-4c03-90bb-0712c17b6681"/></td>
    </tr> 
  </table>

</details>

## 🧩 고도화 결과물

<details>
  
  <summary>Restful 하도록 API 수정하기</summary>

  커밋 내용 => [refactor: Api 수정 (자원 복수형, 버저닝)](https://github.com/ppusda/MML/commit/105e29759e1489490ff1b93dae9f60efc202d893), [refactor: Api 수정 (PUT, PATCH)](https://github.com/ppusda/MML/commit/58559a6fe2e49ce72a2e3ac2005d506d4c1355d8)

  ### PUT, PATCH에 대하여

  위 내용은 이전부터 고민하고 사용했다고 생각했지만 코치님께 관련 피드백을 받게 되었다.
  그렇게 자료를 좀 더 찾아보게 되었고 아래와 같은 고민과 생각을 마주치게 되었다.
  
  [수정이라는 작업을 할 때 PUT을 더 사용하는 이유가 있을까요?](https://www.inflearn.com/community/questions/1175426/%EC%88%98%EC%A0%95%EC%9D%B4%EB%9D%BC%EB%8A%94-%EC%9E%91%EC%97%85%EC%9D%84-%ED%95%A0-%EB%95%8C-put%EC%9D%84-%EB%8D%94-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94-%EC%9D%B4%EC%9C%A0%EA%B0%80-%EC%9E%88%EC%9D%84%EA%B9%8C%EC%9A%94?srsltid=AfmBOoqsSWzuKAMi-dHVhdjLSY8lzRR2-ZaaE-n0DDki3FHIlgOE-JKT) <br>
  [그래서 PUT 이랑 PATCH 는 뭐가 다른건가요](https://cindycho.tistory.com/79)
  
  부끄럽게도 나는 단순히 "PUT은 자원의 전체 교체", "PATCH는 자원의 일부 교체" 정도의 개념만 알고 있었기에 문제였던 것을 알게되었다. <br>
  아래와 같은 내용을 알아두려고 한다.

  - 언급되는 자원은 Entity의 컬럼들과 동일 시 되지 않는다. / DTO에 명시 한 목록이 자원이 되며, 이 자원들을 모두 교체 하느냐 마느냐가 PUT, PATCH로 갈리게 된다.
  - PUT은 자원의 전체 교체, 입력 받은 **모든 데이터가 그대로 반영**되어야 한다.
  - PATCH는 자원의 일부 교체, 입력 받은 데이터 중 **null이 있더라도 이전 데이터를 유지하며, 입력된 데이터만 반영**한다.
  - REST한 API를 항상 지키기는 힘들다. 하지만 차이를 알고 정해놓은 컨벤션에 맞춰서만 작성한다면 크게 문제되지는 않는다.

  위와 같은 내용들을 생각하고 코드를 수정했으며, Music의 경우는 PUT으로 변경, Playlist의 경우는 PATCH로 동작 할 수 있도록 코드를 수정하였다.
  
</details>

<details>
  
  <summary>읽기 좋은 코드로 수정하기 (주석, 메서드 네이밍 수정, 퍼사드 패턴 적용)</summary>

  커밋 내용 => 

  <table>
    <tr>
      <th>10/30 목</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/ac20b1d1e7df747848bf61330bd2d72328d45b86">refactor: 읽기 좋은 코드로 수정하기 - Member (+ List -> Page로 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/0fed734a91b46ed3d2b8d81ffe9ace7d253cd193">refactor: 읽기 좋은 코드로 수정하기 - Member (누락 및 오타 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/42009d2c11584fa8bfee6fde2805d5a184f67977">refactor: 읽기 좋은 코드로 수정하기 - Member (오타 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/f3e6a83bc10d53817a24dc646c9877aa49448657">refactor: 읽기 좋은 코드로 수정하기 - Member (주석 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/14dd3eb228719213d508755dc6e9b6269bfb0249">refactor: 읽기 좋은 코드로 수정하기 - Music (+ List -> Page로 수정, 테스트 코드 일부 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/2c30baa89100c3d488b5f4e0d2314406e2a4044f">refactor: 읽기 좋은 코드로 수정하기 - Playlist (+ List -> Page로 수정)</a><br>
      </td>
    </tr> 
    <tr>
      <th>11/01 금</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/98a0400baa93a7ea17841dceafb58d95554f8384">refactor: 읽기 좋은 코드로 수정하기 - PlaylistMusic (+ List -> Page로 수정, 테스트 코드 일부 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/b8896382739d16dc9cced465c060eb81f89b7142">refactor: 읽기 좋은 코드로 수정하기 - PlaylistMusic (Operation 추가)</a><br>
        <a href="https://github.com/ppusda/MML/commit/526c026ed9914218ff5ca5594f0924b9c7e93c41">refactor: 읽기 좋은 코드로 수정하기 - 퍼사드 패턴 적용하기 (MusicListRepository)</a><br>
        <a href="https://github.com/ppusda/MML/commit/ed67c314e6431978797c159d36e0c98d871d9953">refactor: 읽기 좋은 코드로 수정하기 - 오타 수정 및 불필요한 import 제거</a><br>
      </td>
    </tr> 
  </table>

  ### 후기

  이번 자체미션에서는 한 번에 이해되지 않는 메서드 네이밍을 수정하거나 구현했던 기능들에 대해 모두 주석을 달아보게 되었다.
  처음 작성할 때는 이상한 점을 느끼지 못했는데 다시 보니 이상하다고 느껴지는 메서드 네임들도 있었고, 주석을 달다 보니 누구나 이런 부분에서 벗어나더라도 더 쉽게 이해할 수 있을 것 같다고 생각하게 되었다.
  
  물론 어려운 작업은 아니었지만 생각을 요하는 작업이었던 것 같다.
  
  추가로 퍼사드 패턴을 적용해서 Music, Playlist 간에 발생하는 간단한 DB 로직들을 합쳐서 관리할 수 있게 하였다.
  혼자서 직접 적용해보는 건 처음이었지만 어렵지는 않았고, 수정한 코드를 보니 확실히 더 깔끔해졌다고 느꼈다.
  
  복잡한 구조라면 이러한 패턴을 적용해보는 것을 고민해봐야겠다고 생각되었고, 다른 디자인 패턴들도 적용해볼 수 있는지 좀 더 알아봐야겠다.
  
</details>

<details>
  
  <summary>테스트 커버리지 100% 달성하기(단위 테스트 위주로 작성)</summary>

  커밋 내용 => 

  <table>
    <tr>
      <th>11/02 토</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/1cd83d06e115975e5a6da5c505ef362c32df5d1e">feat: JaCoCo 설정 추가 (+ 각 요소 설명)</a><br>
        <a href="https://github.com/ppusda/MML/commit/5f46369f5ee9570516ca9df0c2737663cf64c1c2">refactor: 테스트 커버리지 100% 달성하기 - 기존 테스트 변경사항 수정</a><br>
      </td>
    </tr> 
    <tr>
      <th>11/03 일</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/373c6084814e106612c369225a2888a2f4262dd9">feat: 테스트 커버리지 100% 달성하기 - MemberService 테스트 작성</a><br>
      </td>
    </tr>
    <tr>
      <th>11/04 월</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/31353d16df692bdde8c97615a98e8970144351e7">feat: 테스트 커버리지 100% 달성하기 - MemberService 예외 상황 테스트 작성 (+ Mockito-Kotlin 추가)</a><br>
      </td>
    </tr>
    <tr>
      <th>11/05 화</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/a9ce047adf05c02bb057008821dc37b578c40f97">fix: 테스트 커버리지 100% 달성하기 - MemberService / 상수로 수정, mockito-kotlin 라이브러리로 수정</a><br>
        <a href="https://github.com/ppusda/MML/commit/f0a1414f07940695499b6227b669b53d97b63ca8">feat: 테스트 커버리지 100% 달성하기 - MusicService 테스트 작성</a><br>
        <a href="https://github.com/ppusda/MML/commit/877c67aeba47b6f6a32e55d42d660ba0e8b3f61c">feat: 테스트 커버리지 100% 달성하기 - PlaylistService 테스트 작성</a><br>
        <a href="https://github.com/ppusda/MML/commit/e30b24d9d8ff761fe02b0d2c9cf79d8d77370cd5">feat: 테스트 커버리지 100% 달성하기 - PlaylistMusicService 테스트 작성</a><br>
        <a href="https://github.com/ppusda/MML/commit/a35a7a90327c82757ff767bb6dcfb661557b78a9">fix: 테스트 커버리지 100% 달성하기 - 불필요한 import 제거 및 디렉토리 정리</a><br>
      </td>
    </tr>
    <tr>
      <th>11/06 수</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/92788561266007a87076c962d5a9a368050d815c">refactor: 테스트 커버리지 100% 달성하기 - BaseServiceTest 기본 설정 클래스 추가</a><br>
        <a href="https://github.com/ppusda/MML/commit/180c71b5e50d18213a905dbed0796bf9f9c13c87">refactor: 테스트 커버리지 100% 달성하기 - BaseControllerTest 기본 설정 클래스 추가 (WebMvcTest)</a><br>
        <a href="https://github.com/ppusda/MML/commit/fe41a1304758edb71cfa365ce06eb93e8d8eb804">fix: 테스트 커버리지 100% 달성하기 - BaseServiceTest 접근제어자 및 명명규칙 준수</a><br>
      </td>
    </tr>
    <tr>
      <th>11/07 목</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/d70df0f630503433bc389eb73ad7cce19dfc41c7">fix: 테스트 커버리지 100% 달성하기 - 기존 테스트에 변경사항 적용</a><br>
      </td>
    </tr>
    <tr>
      <th>11/08 금</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/34943fc9320bf86181fe9c3f7ea5d5f858b943aa">feat: 테스트 커버리지 100% 달성하기 - BaseRepositoryTest 기본 설정 클래스 추가</a><br>
      </td>
    </tr>
    <tr>
      <th>11/09 토</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/08c7162eb1d0c5510dd27d85cd002a551514d9b7">feat: 테스트 커버리지 100% 달성하기 - PlaylistMusicRepository / Custom 메서드 테스트</a><br>
        <a href="https://github.com/ppusda/MML/commit/fa228241d65ab7942e6304a8f13954ff9148f33e">feat: 테스트 커버리지 100% 달성하기 - 주석 정리</a><br>
      </td>
    </tr>
    <tr>
      <th>11/10 일</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/73cc3f2c6f0ddd846fb4c1c550dd11ba4cf4367d">feat: 테스트 커버리지 100% 달성하기 - PlaylistMusicRepository 테스트 작성</a><br>
        <a href="https://github.com/ppusda/MML/commit/85f77232b6faba52caecbd5efbd5fe355c71df10">fix: 테스트 커버리지 100% 달성하기 - Entity 미사용 Setter 제거</a><br>
        <a href="https://github.com/ppusda/MML/commit/3a40d028f5d70ffdc9b23b00fe120b9405dcec15">fix: 테스트 커버리지 100% 달성하기 - 테스트 공통 사항 적용 및 Excpetion 검증</a><br>
        <a href="https://github.com/ppusda/MML/commit/896d0ba606d75cbf947c2868c6a1ae844c6dcc0b">fix: 테스트 커버리지 100% 달성하기 - 업데이트 로직 수정</a><br>
      </td>
    </tr>
    <tr>
      <th>11/11 월</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/2b5aa1bda3ef92780e5cd420a74ee96b9c0aa535">feat: 테스트 커버리지 100% 달성하기 - ApiResponse, Custom Exception 테스트 작성</a><br>
        <a href="https://github.com/ppusda/MML/commit/45ee651bd9765a886f0cbc54c1220a1836752dd9">feat: 테스트 커버리지 100% 달성하기 - DTO 테스트 작성 (+ Member DTO 경로 수정)</a><br>
        <a href="https://github.com/ppusda/MML/commit/1ea4ea631fec800ecfdda5e8a4fbe8807e1364db">feat: 테스트 커버리지 100% 달성하기 - MmlApplication main @generated로 제외</a><br>
        <a href="https://github.com/ppusda/MML/commit/0d265a8f4a67a763bb9bfb93939d9551b7277bd1">feat: 테스트 커버리지 100% 달성하기 - Entity 내 수정 메서드 테스트 작성</a><br>
      </td>
    </tr> 
  </table>

  ### 테스트 커버리지 - 초기
  ![테스트 커버리지 - 초기](https://github.com/user-attachments/assets/faa7f62b-2e4a-4bbf-b5db-5de2c0a70992)

  ### 테스트 커버리지 - 기존 테스트 변경사항 적용 후
  ![테스트 커버리지 - 기존 테스트 변경사항 적용 후](https://github.com/user-attachments/assets/18df3306-3e98-435f-9e2d-d0cd380a7de8)

  ### 테스트 커버리지 - 멤버 추가 후
  ![테스트 커버리지 - 멤버 추가 후](https://github.com/user-attachments/assets/ee94586d-2ca0-4f77-b56c-f17d2bf8d3e3)

  ### 테스트 커버리지 - 서비스 추가 후
  ![테스트 커버리지 - 서비스 추가 후](https://github.com/user-attachments/assets/f57c968f-cd41-429b-aa82-0a5c42435858)

  ### 테스트 커버리지 - 컨트롤러 변경사항 적용 후
  ![테스트 커버리지 - 컨트롤러 변경 후](https://github.com/user-attachments/assets/0ecb68d6-cee0-49da-9bba-b34a1312e9a4)

  ### 테스트 커버리지 - 리포지토리 추가 후
  ![테스트 커버리지 - 리포지토리 추가 후](https://github.com/user-attachments/assets/aadd245e-d5f2-42c1-937c-c094952e3b61)

  ### 테스트 커버리지 - 미사용 Setter 제거 및 Exception 검증 로직 등 변경 후
  ![테스트 커버리지 - 미사용 Setter 제거 및 Exception 검증 등 변경사항 적용 후](https://github.com/user-attachments/assets/af9a9f6f-7a98-447a-9cf3-0bd7768fe15a)

  ### 테스트 커버리지 - Controller Advice, Custom Excpetion 테스트 추가 후
  ![테스트 커버리지 - Controller Advice, Custom Excpetion 테스트 추가 후](https://github.com/user-attachments/assets/a5b87d2a-7593-4a1e-91b2-278c8a65ffb8)

  ### 테스트 커버리지 - DTO 테스트 추가 후
  ![테스트 커버리지 - DTO 테스트 작성](https://github.com/user-attachments/assets/5a6d8a76-f348-4ade-88e1-0924a21ace91)

  ### 테스트 커버리지 - Entity 내 메서드 테스트 추가 및 메인 메서드 제외 후
  ![테스트 커버리지 - Entity 메서드 테스트와 Main 메서드 제외](https://github.com/user-attachments/assets/a859e185-8451-4aec-93b4-6f43f863da47)

  ### 후기
  꽤 오랜시간을 투자해서 JaCoCo 테스트 커버리지 100%를 달성하게 되었다. <br>
  **총 84개의 테스트와 24개의 파일**을 만들었으며 코치님께서 말씀하셨던 커버리지 100%를 달성해보면 배우는 것이 있을 것이라는 말에 공감하게 되었다.
  
  **먼저 테스트 코드가 필요한 Controller, Service, Repository에 대해 테스트 작성 흐름을 알게 되었다.** <br>
  이 부분이 솔직히 정말 의미있다고 생각하며, 앞으로 어렵게만 느껴졌던 테스트 코드 작성에 대한 부담감을 덜 수 있을것 같다.

  **두번째로는 여러 분기에 대한 처리의 중요성을 알게 되었다.** <br>
  테스트 커버리지 100%를 목표로 채워나가다 보니 사용한 적 없는 조건문이나 고려해야하는 예외 상황들을 많이 보게 되었다. <br>
  이런 부분에 대한 작성을 실제로 하다 보니 예상치 못한 부분에서 발생할 수 있는 상황을 좀 더 생각해 볼 수 있었고, 불필요한 코드들을 처리할 수 있었던 것 같다.

  **마지막으로 테스트 코드의 중요성을 다시 한 번 깨닫게 되었다.** <br>
  테스트 코드를 작성해보면서 실제로 잘못 구현한 부분도 발견했으며, 위에서 언급했던 것 처럼 불필요한 부분이나 어색한 부분들을 고칠 수 있었다. <br>
  이러한 자잘한 실수들이 실무에서는 큰 영향을 미칠 수 있으니 테스트 코드가 얼마나 중요한 지 알 수 있었던 것 같다.

  ### 마치며
  일주일이 넘는 시간동안 테스트 코드를 작성해보면서 여러 생각들이 들었다. <br>
  작은 프로젝트임에도 불구하고 작성해야 할 테스트는 많았고 관련 지식도 부족했기에 생각보다 힘들었고 시간이 꽤나 소요되었다. <br>
  그래도 매일 새벽까지 테스트를 작성하며 테스트 커버리지를 올려나가는 순간들은 재밌었고 무엇보다 의미있는 작업이라는 것을 많이 느끼게 되었다.

  [코치님께서 추천해주셨던 영상](https://www.youtube.com/watch?v=jdlBu2vFv58)에서도 나왔던 것 처럼 테스트 커버리지 100%를 달성한 순간에 "아 이렇게 해도 모든 상황을 검증할 수는 없겠구나" 라는 생각이 들었고
  그렇기에 테스트 커버리지 100% 달성은 좋은 경험이었고 앞으로도 최대한 버그가 없도록 좋은 테스트 코드를 열심히 작성하며 개발을 해나가야겠다고 다짐하게되었다.

  ![테스트 결과](https://github.com/user-attachments/assets/8db3bcc0-0e26-40d7-b0e7-b49478a546ff)

</details>

<details>
  
  <summary>간단한 UI 추가하기</summary>

  커밋 내용 => 

  <table>
    <tr>
      <th>11/12 화</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/b8c0b615a93c1bfc751acf56e1f26ecb9d228617">feat: 간단한 UI 추가하기 - 부트스트랩 템플릿</a><br>
      </td>
    </tr> 
    <tr>
      <th>11/13 수</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/ee55a10389e055cfc7d644254618db5b144c79c8">feat: 간단한 UI 추가하기 - Fragment 분리, 기본 구조 설정</a><br>
        <a href="https://github.com/ppusda/MML/commit/1211213eaee2fb26ad1d938c4fff8b49b3a1a96c">test: 간단한 UI 추가하기 - MusicListViewController 테스트 작성</a><br>
      </td>
    </tr>
    <tr>
      <th>11/14 목</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/5743afde27188946aaef850abe49fad661a9b99d">feat: 간단한 UI 추가하기 - 템플릿 수정 (MusicList)</a><br>
      </td>
    </tr>
    <tr>
      <th>11/15 금</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/9e5c8d4985a36d002477dcc0a0853ce8541e0eec">feat: 간단한 UI 추가하기 - 로그인 페이지 추가</a><br>
        <a href="https://github.com/ppusda/MML/commit/39ee42769646933344c5c4036206b781e729f70b">fix: 간단한 UI 추가하기 - card 내 아이콘 svg로 수정</a><br>
      </td>
    </tr> 
    <tr>
      <th>11/16 토</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/71b0ebe6784ccced0db36775dabde875fee4ee4f">feat: 간단한 UI 추가하기 - 음악/플레이리스트 페이지 추가</a><br>
      </td>
    </tr>
    <tr>
      <th>11/17 일</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/7e944abf88e9f35e7ba621bd8027cc0d259276d1">test: 간단한 UI 추가하기 - MusicListViewController, MemberViewControllerTest</a><br>
      </td>
    </tr> 
  </table>

  ### 메인화면
  ![Main](https://github.com/user-attachments/assets/f9176912-7204-4b27-8a57-59d5928e1104)

  ### 로그인화면
  ![Login](https://github.com/user-attachments/assets/330f052a-480f-41a0-b80c-33e71921a8cb)

  ### 재생목록 생성 화면
  ![Playlist](https://github.com/user-attachments/assets/1a1baa73-2d3f-45b4-ab67-3d0ddb8a132c)

  ### 후기
  크게 어려운 작업은 아니었지만 기존 기능에 대한 화면만 구현할지 새 기능들을 추가해서 구현할지에 대해서 고민이 조금 되었다.<br>
  결과적으로는 구현하기로 마음을 먹었으며 UI 자체는 더 추가할 부분이 없을 것 같아 일단락하려고 한다.<br>
  <br>
  향후 추가할 기능은 아래와 같다.<br>
    - 음악 검색 (Kotlin JDSL)<br>
    - Github 로그인<br>
    - 재생목록 템플릿 제공<br>
  <br>
  새로운 프로젝트를 진행하게 되어서 당장은 힘들겠지만 차근차근 해나가보려고한다.
  
</details>

<details>
  
  <summary>더미 데이터 추가하기</summary>

  커밋 내용 => 

  <table>
    <tr>
      <th>11/30 토</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/6752ae3537d4e8b7be3eb0f1767288c50b2e4cc0">feat: 더미 데이터 추가하기</a><br>
      </td>
    </tr> 
  </table>
  
</details>

<details>
  
  <summary>Swagger 적용해보기</summary>

  커밋 내용 => 

  <table>
    <tr>
      <th>11/30 토</th>
      <td>
        <a href="https://github.com/ppusda/MML/commit/4af77796d6d0b9c6c2d4181de9924b29b3654940">feat: Swagger 추가 및 설정</a><br>
      </td>
    </tr> 
  </table>

  ### Swagger UI
  /swagger-ui/index.html

  ![SwaggerUI](https://github.com/user-attachments/assets/62ffb88b-d690-44d3-b3f4-03a22c7d4809)

  
</details>

## 📑 참고
본 프로젝트는 [인프런 워밍업 클럽 스터디 2기 - 백엔드 프로젝트 (Kotlin, Spring Boot)](https://www.inflearn.com/course/offline/warmup-club-2-be-bk) 의 서브 미션을 위한 프로젝트입니다.

<details>
  
  <summary>🎯 미션 수행 사항</summary>
  
  - [x] [미션 1] 테이블 설계하기(~10/4 금)
  - [x] [미션 2] 깃허브 리포지토리에 프로젝트 올리기(~10/4 금)
  - [x] [미션 3] REST API 설계하기(~10/8 화)
  - [x] [미션 4] 조회 REST API 만들기(~10/15 화)
  - [x] [미션 5] 삽입, 수정, 삭제 REST API 만들기(~10/21 월)
  - [ ] [자체 미션] 이후 고도화 (진행 중)

</details>

<details>
  
  <summary>📑 고도화 계획</summary>

  코드 리뷰 => [코드 리뷰](https://github.com/ppusda/MML/pull/1)
  코드 리뷰 해주신 내용을 고려하여 코드를 수정하고, 고도화를 진행해보려고 합니다.
  
  - [X] Restful 하도록 API 수정하기 (10/30 수)
  - [x] 읽기 좋은 코드로 수정하기 (주석, 메서드 네이밍 수정, 퍼사드 패턴 적용) (10/31 목 ~ 11/01 금)
  - [x] 테스트 커버리지 100% 달성하기 (단위 테스트 위주로 작성) (11/02 토 ~ 11/11 월)
  - [x] 간단한 UI 추가하기 (Thymeleaf) (11/12 화 ~ 11/17 일)
  - [x] 더미 데이터 추가하기 (11/30 토)
  - [x] Swagger 적용 (11/30 토)

  추가 기능 구현 계획
  - [x] 음악 검색 (Kotlin JDSL) (11/30 토)
  - [ ] Github 로그인
  - [ ] 재생목록 템플릿 제공

</details>
