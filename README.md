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

![MML_ERD](https://github.com/user-attachments/assets/205c1e35-8288-4556-bd0a-2640f935394b)


## ⚙ API
### User
<details>
  
  <summary><code>GET /user</code> - 회원 조회</summary>

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
  
  <summary><code>POST /user</code> - 회원 가입</summary>

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
  
  <summary><code>DELETE /user/{n}</code> - n번 회원 탈퇴</summary>

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
  
  <summary><code>POST /user/sign</code> - 회원 로그인</summary>

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
  
  <summary><code>DELETE /user/sign</code> - 회원 로그아웃</summary>

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
  
  <summary><code>GET /music</code> - 음악 조회</summary>

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
  
  <summary><code>POST /music</code> - 음악 등록</summary>

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
  
  <summary><code>PATCH /music/{n}</code> - n번 음악 정보 수정</summary>

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
  
  <summary><code>DELETE /music/{n}</code> - n번 음악 삭제</summary>

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
  
  <summary><code>GET /playlist</code> - 플레이리스트 조회</summary>

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
  
  <summary><code>POST /playlist</code> - 플레이리스트 생성</summary>

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
  
  <summary><code>PATCH /playlist/{n}</code> - n번 플레이리스트 정보 수정</summary>

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
  
  <summary><code>DELETE /playlist/{n}</code> - n번 플레이리스트 삭제</summary>

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
  
  <summary><code>GET /playlist/{n}/music</code> - n번 플레이리스트 조회</summary>

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
  
  <summary><code>POST /playlist/{n}/music</code> - n번 플레이리스트에 음악 추가</summary>

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
  
  <summary><code>DELETE /playlist/{n}/music</code> - n번 플레이리스트에 음악 삭제</summary>

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
  
  - [] Restful 하도록 API 수정하기
  - [] 읽기 좋은 코드로 수정하기 (주석, 메서드 네이밍 수정)
  - [] 테스트 코드 추가하기 (Coverage를 최대한 채워보기, 단위 테스트 작성)
  - [] UI 추가하기
  - [] 더미 데이터 추가하기

</details>
