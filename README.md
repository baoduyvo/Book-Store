# 📦 Book Store - Spring Boot Deployment with Docker Compose

Book Store là một hệ thống quản lý sách được xây dựng với Spring Boot 3.4.5, sử dụng JDK 17. Ứng dụng kết nối trực tiếp
đến MySQL trên máy host, đồng thời tích hợp các dịch vụ hạ tầng như Elasticsearch, Redis, Kafka, MinIO thông qua Docker
Compose để phục vụ các chức năng tìm kiếm, lưu trữ, cache và giao tiếp bất đồng bộ.

---
⚙️ Yêu cầu hệ thống

| 🧩 Dịch vụ           | ⚙️ Cấu hình                                                                                                | 🔗 URL truy cập                                |
|----------------------|------------------------------------------------------------------------------------------------------------|------------------------------------------------|
| **MySQL (host)**     | `IP: 172.16.6.118`, `Port: 3306`  <br>`Database: book_store`<br>`User: root`, `Password: 1234`             | *(chạy nội bộ, không qua browser)*             |
| **Elasticsearch**    | `Host: localhost`, `Port: 9200`                                                                            | [http://localhost:9200](http://localhost:9200) |
| **Kibana**           | `Host: localhost`, `Port: 5601`                                                                            | [http://localhost:5601](http://localhost:5601) |
| **Redis**            | `Host: localhost`, `Port: 6379`                                                                            | *(nội bộ - không có giao diện mặc định)*       |
| **MinIO**            | `Host: localhost`, `Port: 9000`<br>`Bucket: bookstore-bucket`<br>`Username: admin`, `Password: admin@2024` | [http://localhost:9000](http://localhost:9000) |
| **Zookeeper**        | `Host: localhost`, `Port: 2181`                                                                            | *(nội bộ - dùng cho Kafka)*                    |
| **Kafka Broker 1**   | `Port: 9092:29092`                                                                                         | *(nội bộ - kết nối qua client/producer)*       |
| **Kafka Broker 2**   | `Port: 9093:29093`                                                                                         | *(nội bộ - kết nối qua client/producer)*       |
| **Kafka Broker 3**   | `Port: 9094:29094`                                                                                         | *(nội bộ - kết nối qua client/producer)*       |
| **Spring Boot MVC**  | `Port: 8888` *(hoặc cấu hình khác trong application.yml/properties)*                                       | [http://localhost:8080](http://localhost:8080) |
| **Spring Boot Flux** | `Port: 9093` *(hoặc cấu hình khác trong application.yml/properties)*                                       | [http://localhost:9093](http://localhost:9093) |
---
🔧 Bước 1: Clone dự án từ GitHub

    git clone https://github.com/baoduyvo/Book-Store.git
    cd book

🐳 Bước 2: Khởi động các dịch vụ bằng Docker Compose

    open ./book/Book-Store-NetWork/docker-composer.yml
    docker-compose up -d

☕ Bước 3: Biên dịch và chạy ứng dụng Spring Boot

    ./mvnw clean install
    ./mvnw spring-boot:run

☕ Bước 4: http://localhost:8888/bookstore/api/swagger-ui/index.html#/

✅ 📂 Cấu trúc thư mục:
📦 book-store-root/
├── 📁 book-store-tools/        # Module dùng chung: Entity, DTO, Enum, Exception
│    ├── pom.xml
│    └── src/main/java/org/voduybao/tools/
│          ├── entities/
│          │     ├── MediaGallery.java
│          ├── dto/
│          │     ├── Result.java
│          ├── enums/
│          ├── exceptions/
│          └── util/
│
├── 📁 book-store-webflux/      # Service chạy Reactive: Spring WebFlux + R2DBC
│    ├── pom.xml
│    ├── src/main/java/org/voduybao/webflux/
│    │     ├── controllers/
│    │     │     └── MediaGalleryController.java
│    │     ├── repositories/
│    │     │     └── MediaGalleryRepository.java
│    │     ├── services/
│    │     ├── Application.java
│    ├── src/main/resources/application.yml
│
├── 📁 book-store-jpa/          # Service chạy Blocking: Spring Data JPA + JDBC
│    ├── pom.xml
│    ├── src/main/java/org/voduybao/jpa/
│    │     ├── controllers/
│    │     ├── repositories/
│    │     ├── services/
│    │     ├── Application.java
│    ├── src/main/resources/application.yml
│
├── 📄 pom.xml                  # Root pom.xml (parent)
└── 📄 settings.gradle (nếu dùng Gradle)
