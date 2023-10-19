# A-Yetenek 
## Aselsan Back End
###### Producer:Ahmet Can Erol

*Bu proje Aselsan'da yapmış olduğum staj boyunca geliştirdiğim, A Yetenek Web Sayfasının backend kodlarını içermektedir.*

Genel olarak projemizin amacı, A Yetenek adaylarının başvurularının alınabileceği bir web sayfası yaratmak ve adaylardan aldığımız bilgileri İnsan Kaynaklarına sergileyebileceğimiz bir arayüz oluşturmaktır.

*Projemizin BackEnd kısmında;*
* Salted Sha256 Hashing; Bu hash mekanizması ile kullanıcıdan aldığımız şifreleri sisteme direkt kaydetmektense yine kullanıcıdan aldığımız başka bir bilgi ile birleştirip hashleyerek veritabanımıza kaydetmekteyiz.*
* Tc Kimlik No Doğrulama: Bu doğrulama sayesinde kullanıcıdan aldığımız Tc Kimlik numaralarının sistematik olarak doğruluğunu kontrol ederek kullanıcıların doğru bilgi girip girmediğine bakmaktayız.*
* Authorization: Burada kendisi random olarak üretilen rakamlar ve sayılardan oluşan bir doğrulama kodunu kullanıcıya sunarak işlem yapmasını istemekteyiz. Bu da kullanıcının gerçek bir insan olup olmadığını anlamamızda
 yani başvuranın bir insan olduğuna kanaat getirmemizde yardımcı olacaktır.
* Postgre Sql: Veritabanı olarak kullandığımız PostgreSql'i bir docker image olarak yaratarak hem verilerin güvenilir bir şekilde depolanmasına yardımcı olduk. Hem de bilgisayarımıza herhangi bir sistem yüklemeden veritabanımızı kullanabildik.*
* APIs: Genel olarak tüm projemizin amacı olan apiler, ön yüz kısmında yaptığımız işlemlere göre çağrılıp işlemi yapıp geri değer dönmektedir. (Rest)
* Jpa Hibernate: Jpa kullanılarak veritabanı oluşturmada büyük bir kolaylık yakalanmıştır.

  
 Proje geliştirilirken Docker, PostgreSql, Spring Boot, Jpa Hibernate, Spring Security,Java, Rest API's dan yararlanılmıştır.

## ENGLISH

*This project includes the backend code of the A-Yetenek web page that I developed during my internship at Aselsan.*

*The main objective of our project is to create a web page where A-Yetenek candidates can submit their applications and to build an interface where we can showcase the information we receive from candidates to the Human Resources department.*

In the Backend part of our project:
- Salted SHA-256 Hashing: With this hashing mechanism, we hash the passwords we receive from the user by combining them with another piece of information we receive from the user before saving them in the system.
- Turkish Identity Number Verification: With this verification, we systematically check the accuracy of the Turkish identity numbers received from users to ensure that they enter correct information.
- Authorization: Here, we provide the user with a randomly generated verification code consisting of numbers and digits and request them to perform an action. This helps us determine whether the applicant is a real person.
- PostgreSQL: We created PostgreSQL as a Docker image to ensure reliable data storage and to use our database without installing any system on our computer.
- APIs: In general, the APIs, which are the main purpose of our project, are called based on the operations we perform on the front end, execute the operation, and return the result (REST).
- JPA Hibernate: Using JPA has greatly facilitated database creation.

*When developing the project, Docker, PostgreSQL, Spring Boot, JPA Hibernate, Spring Security, Java, and Rest APIs were utilized.*
