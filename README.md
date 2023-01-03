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
