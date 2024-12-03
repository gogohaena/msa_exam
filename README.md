# 서비스 구조
![image](https://github.com/user-attachments/assets/ec32a09f-2d6f-4ee7-b186-87f2460b92a1)

| 서비스 | 패키지명  | 포트 |
| --- | --- | --- |
| 유레카서버-**eureka server** | `com.sparta.msa_exam` | `19090` |
| 게이트웨이-**gateway**  | `com.sparta.msa_exam.gateway` | `19091` |
| 상품-**product** | `com.sparta.msa_exam.product` | `19093` , `19094` |
| 주문-**order** | `com.sparta.msa_exam.order` | `19092` |
| 인증-**auth** | `com.sparta.msa_exam.auth` | `19095` |

# API Documentation
[https://documenter.getpostman.com/view/31651869/2sAYBa99QB](https://documenter.getpostman.com/view/31651869/2sAYBa99QB)