## Shop Project
### 프로젝트 제목
ZomBo Shopping mall

### 프로젝트 정보
+ 위 프로젝트는 백엔드 1명, 프론트엔드 1명 총 2명의 인원으로 진행했다.
+ 간단한 쇼핑몰 프로젝트를 기획 및 설계해서 진행했다.
+ 개발기간 : 2022.12.18 ~ 2023.01.07 (약 3주)
+ https://github.com/perillaleaves/shop-backend , ZOMBO
+ 
### 배포 주소
http://mullae.com
http://api.mullae.com

### 기술 스택
`IntelliJ` `GIT` `GITHUB`  
`JAVA` `SPRING` `spring mvc` `JPA`  
`SLACK` `NOTION`

### 다음 프로젝트 챌린지
Spring mvc error handling  
map  
DB study  
Spring profiles env

## server
### Daemon
1. systemctl 설정
> /etc/systemd/system/spring.service
```
[Unit]
Description=A Spring Boot application
After=syslog.target

[Service]
User=myapplication
ExecStart=/opt/my-application.jar
SuccessExitStatus=143
Restart=always
RestartSec=5

[Install]
WantedBy=multi-user.target
```
2. daemon 등록
systemctl enable spring.service

3. systemctl 사용법
```bash
systemctl status spring.service
systemctl start spring.service
systemctl restart spring.service
systemctl stop spring.service
journalctl -f
```

ref
  - https://www.digitalocean.com/community/tutorials/how-to-use-systemctl-to-manage-systemd-services-and-units
