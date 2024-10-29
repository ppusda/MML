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

## 📑 참고
본 프로젝트는 [인프런 워밍업 클럽 스터디 2기 - 백엔드 프로젝트 (Kotlin, Spring Boot)](https://www.inflearn.com/course/offline/warmup-club-2-be-bk) 의 서브 미션을 위한 프로젝트입니다.

<details>
  
  <summary>🎯 미션 수행 사항</summary>
  
  - [x] [미션 1] 테이블 설계하기(~10/4 금)
  - [x] [미션 2] 깃허브 리포지토리에 프로젝트 올리기(~10/4 금)
  - [x] [미션 3] REST API 설계하기(~10/8 화)
  - [x] [미션 4] 조회 REST API 만들기(~10/15 화)
  - [x] [미션 5] 삽입, 수정, 삭제 REST API 만들기(~10/21 월)
  - [ ] [자체 미션] 이후 고도화

</details>

<details>
  
  <summary>📑 고도화 계획</summary>

  코드 리뷰 => [코드 리뷰](https://github.com/ppusda/MML/pull/1)
  코드 리뷰 해주신 내용을 고려하여 코드를 수정하고, 고도화를 진행해보려고 합니다.
  
  - [X] Restful 하도록 API 수정하기 (10/30 수)
  - [ ] 읽기 좋은 코드로 수정하기 (주석, 메서드 네이밍 수정)
  - [ ] 테스트 코드 추가하기 (Coverage를 최대한 채워보기, 단위 테스트 작성)
  - [ ] UI 추가하기
  - [ ] 더미 데이터 추가하기

</details>
