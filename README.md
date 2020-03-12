# Backend - SpringBoot

## Prerequisite

- [install terraform](https://www.terraform.io/downloads.html)
- [install git](https://git-scm.com/downloads)

## Download source

- Bitbucket URL : [Frontend Repository](https://wire.lgcns.com/bitbucket/projects/LGSTATION/repos/frontend/browse)
- git clone
  git clone 명령을 사용하여 로컬에 다운로드

    ```bash
    git clone https://wire.lgcns.com/bitbucket/scm/lgstation/frontend.git
    ```

- [git clone 사용방법](https://www.atlassian.com/git/tutorials/setting-up-a-repository/git-clone)

## Install NPM package

다운로드한 Source 폴더내에서 아래 명령 실행

```bash
npm install
```

## Start application

Runs the app in the react-scripts development mode.

```bash
npm run start
```

## Access WebPage

Open http://localhost:3000 to view it in the browser.

## Lint

린트(lint) 또는 린터(linter)는 소스 코드를 분석하여 프로그램 오류, 버그, 스타일 오류, 의심스러운 구조체에 표시(flag)를 달아놓기 위한 도구들을 가리킨다.

```bash
npm run lint
```

## Unit Test

단위 테스트를 진행한다.

```bash
npm run test:unit:coverage
```

## Build

React/typescript로 작성한 source file을 build (build file 경로 : /build)

```bash
npm run Build
```

## Learn More

The app was created by [Create React App](https://facebook.github.io/create-react-app/docs/getting-started).

To learn more about ReactJS, check out the [React documentation](https://reactjs.org/).
