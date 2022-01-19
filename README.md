# Simpan Pinjam App

Aplikasi ini terbagi menjadi dua bagian:
- Back-end
- Front-end

Beberapa tool yang digunakan antara lain:
- Java JDK 1.8 (atau versi lebih tinggi).
- Spring Boot.
- NodeJS LTS
- Maven  
- ReactJS
- Parcel

## Back-end

Bagian back-end dibangun menggunakan framework Spring Boot dan Maven sebagai _build automation tool_.

Database yang digunakan adalah PostgreSQL dan MongoDB.

Aplikasi ini menggunakan Kafka sebagai _message queueing_ dan _event streaming_.

### Menjalankan Service PostgreSQL

Untuk kemudahan, saya menyiapkan script docker-compose untuk menjalankan database PostgreSQL. Script ini terletak di folder ```src/main/docker/postgresql.yml```. Ketik perintah berikut pada ```command prompt``` untuk menjalankan service PostgreSQL pada container:

```
docker-compose -f src/main/docker/postgresql.yml up -d
```

### Menjalankan Service MongoDB

Script docker-compose untuk MongoDB juga tersedia di ```src/main/docker/mongodb.yml```. Ketik perintah berikut pada ```command prompt``` untuk menjalankan service MongoDB pada container:

```
docker-compose -f src/main/docker/mongodb.yml up -d
```

### Menjalankan Service Kafka

Script docker-compose untuk Kafka juga tersedia di ```src/main/docker/kafka.yml```. Ketik perintah berikut pada ```command prompt``` untuk menjalankan service Kafka pada container:

```
docker-compose -f src/main/docker/mongodb.yml up -d
```

### Menjalankan Aplikasi Back-end

Jalankan perintah berikut pada ```command prompt``` untuk menjalankan aplikasi back-end:

```
mvn clean compile
mvn
```

Atau dengan menambahkan opsi JVM:

```
mvn -Dspring-boot.run.jvmArguments="-Xms128m -Xmx256m"
```

Kita dapat menggunakan JDK versi 1.8 atau lebih tinggi.

## Front-end

Bagian ini dibangun dengan library ReactJS sebagai toolkit UI dan Parcel sebagai _build tool_.

Penulisan code menggunakan Typescript yang sudah terintegrasi dengan ReactJS.

Untuk menjalankan development server front-end, ketik _command_ berikut di terminal:

```
npm install
npm run start
```

Parcel akan mengkompilasi source code, menyalakan server pada port 1234, dan membuat proxy yang menghubungkan dengan aplikasi back-end yang berjalan pada port 8080.

## Postman Collection

Postman collection data ditemukan di dalam root folder dengan nama ```SimpanPinjam App.postman_collection.json```. Di dalamnya terdapat beberapa HTTP request yang telah disiapkan untuk melakukan test pada beberapa endpoint RESTful.
