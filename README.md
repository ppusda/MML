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

![MML_ERD](https://github.com/user-attachments/assets/f2d2c64d-5ae6-4fe6-b64d-8cf9717f2566)

## ⚙ API 설계
### User
- `GET /user` - 회원 조회
- `POST /user` - 회원가입
- `DELETE /user/n` - n번 회원탈퇴

### Music
- `GET /music` - 음악 조회
- `POST /music` - 음악 등록
- `PATCH /music/n` - n번 음악 정보 수정
- `DELETE /music/n` - n번 음악 삭제

### Playlist
- `GET /playlist` - 플레이리스트 조회
- `POST /playlist` - 플레이리스트 생성
- `PATCH /playlist/n` - n번 플레이리스트 정보 수정
- `DELETE /playlist/n` - n번 플레이리스트 삭제

### Music-Playlist
- `POST /playlist/n/music` - n번 플레이리스트에 음악 추가
- `DELETE /playlist/n/music` - n번 플레이리스트에 음악 삭제

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
