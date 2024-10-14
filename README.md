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

## 📑 참고
본 프로젝트는 [인프런 워밍업 클럽 스터디 2기 - 백엔드 프로젝트 (Kotlin, Spring Boot)](https://www.inflearn.com/course/offline/warmup-club-2-be-bk) 의 서브 미션을 위한 프로젝트입니다.

<details>
  
  <summary>🎯 미션 수행 사항</summary>
  
  - [x] [미션 1] 테이블 설계하기(~10/4 금)
  - [x] [미션 2] 깃허브 리포지토리에 프로젝트 올리기(~10/4 금)
  - [x] [미션 3] REST API 설계하기(~10/8 화)
  - [ ] [미션 4] 조회 REST API 만들기(~10/15 화)
  - [ ] [미션 5] 삽입, 수정, 삭제 REST API 만들기(~10/21 월)
  - [ ] [자체 미션] 이후 고도화

</details>
