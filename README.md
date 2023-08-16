# Wanted BE internship 사전과제


---
### Skills
- java
- spring boot
- spring data JPA
- spring security 2.6.7
- mysql


---
### 구조
![image](https://github.com/jungmyeong96/wanted-pre-onboarding-backend/assets/55140432/ced80caf-c94d-403e-96ba-33828da3bf0e)

![image](https://github.com/jungmyeong96/wanted-pre-onboarding-backend/assets/55140432/a3513c1b-9a1e-4e1e-bb6a-f20a7fc8b40c)

---
### API 명세

`http://localhost:8081/swagger-ui/index.html`

<img width="1073" alt="image" src="https://github.com/jungmyeong96/wanted-pre-onboarding-backend/assets/55140432/c741e7ae-1c3f-4b81-b839-48e2672f0db9">


---


게시판을 관리하는 RESTful API를 개발해 주세요. 이때, 다음의 기능을 구현해야 합니다. 데이터베이스의 테이블 설계는 개발자님의 판단에 맡겨져 있습니다. 요구사항을 충족시키는 데 필요하다고 생각되는 구조로 자유롭게 설계해 주세요.

- [x] 1. 사용자 회원가입 엔드포인트
    - 이메일과 비밀번호로 회원가입할 수 있는 엔드포인트를 구현해 주세요.
    - 이메일과 비밀번호에 대한 유효성 검사를 구현해 주세요.
        - 이메일 조건: **@** 포함
        - 비밀번호 조건: 8자 이상
        - 비밀번호는 반드시 암호화하여 저장해 주세요.
        - 이메일과 비밀번호의 유효성 검사는 위의 조건만으로 진행해 주세요. 추가적인 유효성 검사 조건은 포함하지 마세요.
- [ ] 2. 사용자 로그인 엔드포인트
    - 사용자가 올바른 이메일과 비밀번호를 제공하면, 사용자 인증을 거친 후에 JWT(JSON Web Token)를 생성하여 사용자에게 반환하도록 해주세요.
    -  1과 마찬가지로 회원가입 엔드포인트에 이메일과 비밀번호의 유효성 검사기능을 구현해주세요.
- [x] 3. 새로운 게시글을 생성하는 엔드포인트
- [x] 4. 게시글 목록을 조회하는 엔드포인트
    - 반드시 Pagination 기능을 구현해 주세요.
- [x] 5. 특정 게시글을 조회하는 엔드포인트
    - 게시글의 ID를 받아 해당 게시글을 조회하는 엔드포인트를 구현해 주세요.
- [ ] 6. 특정 게시글을 수정하는 엔드포인트
    - 게시글의 ID와 수정 내용을 받아 해당 게시글을 수정하는 엔드포인트를 구현해 주세요.
    - 게시글을 수정할 수 있는 사용자는 게시글 작성자만이어야 합니다.
- [ ] 7. 특정 게시글을 삭제하는 엔드포인트
    - 게시글의 ID를 받아 해당 게시글을 삭제하는 엔드포인트를 구현해 주세요.
    - 게시글을 삭제할 수 있는 사용자는 게시글 작성자만이어야 합니다.
