# Blockchain 
[GitHub Address](https://github.com/hamitmizrak/Ecodation_TechIstanbul_Blockchain.git )
---

# JavaFX Desktop
[GitHub Address](https://github.com/hamitmizrak/Ecodation_JavaFX_1)
---

## Git
```sh 
git init
git add .
git commit -m "initalize javafx"
git remote add origin URL
git push -u origin master
```

## Git Clone 
```sh 
git clone https://github.com/hamitmizrak/Ecodation_TechIstanbul_Blockchain.git
```

## JDK Dikkat
```sh 
JDK JavaFx bizlere önerdiği JDK sürümü 17'dir.
```

## Eğer JDK ile alakalı hatalar alırsak nereleri JDK 17 yapmalıyız ?
```sh 
Settings => Build, Execution => Compiler => Build Compiler (JDK 17 seçelim)
Projects Structure => Project (JDK 17 seçelim)
Projects Structure => Modules => Module,Source,Dependency (JDK 17 seçelim)
Projects Structure => SDK =>  (JDK 17 seçelim)

Build => Rebuild Project
```

## Eğer durduk yere veya JDK değiştirdikten sonra sistem çalışmazsa;
```sh 
Build => Rebuild Project
```

## Maven Codes
```sh 
mvn clean
mvn clean install
mvn clean package
mvn clean package -DskipTests
```

## JavaFX Permission   src/main/module-info.java 
```sh 
module-info.java  bu dosya içinde izinler vermemiz gerekiyor.
```

## Projenin Amaçı
```sh 

```
Bu **JavaFX destekli Java projesi** olan **“JavaFX Smart Contract Blockchain Simulator”**, temel bir *blockchain (blok zinciri)* sisteminin arka plan mantığını simüle eder. Aşağıda projenin **ne işe yaradığını**, **bileşenlerini** ve **senaryo akışını** çok detaylı bir şekilde açıklıyorum:

---

## 🔍 Projenin Amacı ve İşlevi

Bu proje, birden fazla kullanıcının cüzdan (wallet) bakiyelerini yönettiği, bu kullanıcılar arasında transferlerin yapılabildiği, her üç işlemde bir blok oluşturarak bu işlemleri zincire kalıcı olarak kaydeden **basit ama işlevsel bir blockchain simülasyonudur**.

Proje, aşağıdaki kavramları öğretmek veya uygulamak için geliştirilmiştir:

* Blockchain yapısı
* SHA-256 hash algoritması
* Genesis Block (ilk blok)
* İşlem zinciri (Transaction pool)
* Akıllı kontrat simülasyonu
* Konsensüs ve zincir geçerliliği
* Java'da nesne yönelimli programlama uygulamaları
* JavaFX üzerinden grafiksel arayüze genişletilebilirlik

---

## 🧱 Modül Bileşenleri

### 1. `_01_Transaction.java`

Bir transfer işlemini temsil eder:

* Gönderen (`from`)
* Alıcı (`to`)
* Miktar (`amount`)

### 2. `_02_Utils.java`

SHA-256 hash fonksiyonunu uygular. Blockchain’de verilerin bütünlüğünü sağlamak için kullanılır.

### 3. `_03_Block.java`

Bir blok nesnesini temsil eder:

* `index`: Blok sırası
* `timestamp`: Zaman damgası
* `transactions`: İşlem listesi
* `previousHash`: Önceki bloğun hash değeri
* `hash`: Bu bloğun hash değeri

### 4. `_04_Blockchain.java`

Blok zincirini yönetir:

* Genesis bloğunu oluşturur.
* Yeni blokları zincire ekler.
* Zincirin geçerliliğini kontrol eder.
* Zinciri yazdırır.

### 5. `_05_Wallet.java`

Her kullanıcıya bir cüzdan oluşturur ve bakiyesini takip eder. Transfer sırasında bakiyeyi günceller.

### 6. `_06_SmartContract.java`

İşlem havuzu yönetimini yapar ve 3 işlem biriktiğinde yeni blok oluşturur. Ayrıca transfer işlemlerinin başarıyla gerçekleşmesini denetler.

### 7. `_07_Main.java`

Komut satırından çalışan test senaryosudur. Kullanıcıdan giriş alarak menü üzerinden:

* Transfer yapılmasını
* Blockchain’in yazdırılmasını
* Cüzdan bakiyelerinin görüntülenmesini
* Zincirin geçerliliğinin kontrolünü sağlar.

---

## 🧪 Uygulama Akışı (Senaryo)

1. **Blockchain Başlatılır.** (Genesis Block oluşur)
2. **3 Kullanıcı Cüzdanı**: "Hamit", "Habibe", "Hulusi"
3. **Kullanıcı Transferi**: Kullanıcılar birbirine para transfer eder.
4. **Transfer Havuzu**: Her transfer havuza atılır. 3 transfer birikince blok oluşur.
5. **Zincir Büyür**: Yeni bloklar önceki bloğun hash değerine bağlı olarak eklenir.
6. **Kullanıcı Bakiyeleri Güncellenir.**
7. **Konsol Menü** üzerinden kullanıcı;

    * Transfer yapabilir.
    * Blockchain’i yazdırabilir.
    * Tüm cüzdanları görebilir.
    * Zincirin geçerli olup olmadığını görebilir.

---

## 🛡️ Güvenlik ve Kontroller

* ✅ Kullanıcı adı geçerli değilse tekrar sorar.
* ✅ Negatif miktar girişi engellenir.
* ✅ Bakiye yetersizse işlem yapılmaz.
* ✅ Zincir geçerliliği her blokta hash ve önceki hash eşleşmesiyle kontrol edilir.

---

## 🧩 Genişletilebilirlik (JavaFX ve UI)

Bu temel yapı istenirse şu şekilde genişletilebilir:

* JavaFX ile kullanıcı arayüzü: TextField, ComboBox, Button’larla kontrol
* Dosya sistemine export/import
* Ağ üzerinden diğer node’larla senkronizasyon
* Gelişmiş smart contract kuralları (şartlı işlemler)

---

## 📌 Sonuç

Bu proje, bir **blockchain zincirinin teknik mimarisini, güvenliğini ve işlem kontrolünü** sade ve anlaşılır bir biçimde modelleyen bir örnektir. Eğitim amaçlı olarak çok etkilidir. Gerçek dünyada aşağıdaki alanlara temel oluşturabilir:

* Kripto para cüzdanları
* Dijital sözleşmeler
* Dağıtık sistem altyapıları
* Güvenli veri transferi uygulamaları


## Projenin Eklenebilir ÖZellikler
```sh 

```

Yukarıdaki blockchain simülasyonu projesine aşağıdaki **gelişmiş özellikleri** ekleyerek sistemi daha işlevsel, öğretici ve gerçek dünyaya daha yakın hâle getirebilirsiniz:

---

## 🚀 Geliştirme ve Ek Özellik Önerileri

### 1. 🔐 **Dijital İmza ve Anahtar Yönetimi**

* Her kullanıcı için `public` ve `private key` üretimi yapılabilir.
* İşlemler `private key` ile imzalanabilir, doğrulama `public key` ile yapılabilir.
* Bu, gerçek blockchain güvenliğini öğretir.

---

### 2. 🧠 **Akıllı Kontrat Genişletmesi**

* Koşullu işlemler:

  ```java
  if (amount > 1000 && userType == "student") return "Limit aşımı!";
  ```
* Tarih bazlı sözleşmeler (gelecekte otomatik tetiklenme)
* DAO benzeri yönetim kuralları

---

### 3. 📊 **İstatistiksel Gösterim**

* Her kullanıcı kaç işlem yapmış?
* Toplam transfer edilen miktar nedir?
* Hangi blokta kaç işlem var?

---

### 4. 🗃️ **Veri Kalıcılığı**

* `blockchain.json` gibi bir dosyaya zincirin kaydedilmesi
* Program başlarken bu dosyadan blockchain’in geri yüklenmesi

---

### 5. 📦 **İşlem Havuzu Görüntüleme**

* Şu anda blok oluşmayı bekleyen işlemleri ayrı listele:

  ```java
  contract.getPendingTransactions().forEach(System.out::println);
  ```

---

### 6. 🧪 **Manipülasyon ve Hack Simülasyonu**

* Bloklardan birinin içeriğini bilinçli bozup `isChainValid()` sonucunu test etme
* Kullanıcılara zincir saldırısı örnekleri gösterilebilir

---

### 7. 🧬 **Çatal (Fork) Oluşturma**

* Aynı anda iki farklı yol izleyen zincir örnekleri (fork durumu)
* Konsensüs mekanizması: uzun zinciri kabul etme

---

### 8. 🪙 **Token Tanımlama**
* Cüzdanlara sabit bir token türü atama (örnek: `EduCoin`, `SmartToken`)
* İleride farklı token işlemleri yapılmasını sağlayabilir
---

### 9. 🖼️ **JavaFX GUI**

* Arayüz tasarımıyla kullanıcı:

    * Liste kutusundan kullanıcıları seçebilir
    * Transfer formu doldurabilir
    * Cüzdan bakiyelerini grafikle görebilir
    * Blockchain yapısını tablolu veya grafiksel görebilir
---

### 10. 🔄 **Node Simülasyonu (İleri Seviye)**
* Birden fazla bağımsız blockchain node'u
* Her node’un kendi zinciri
* Broadcast ile zincir paylaşımı, konsensüs sağlama

---

### 11. 🧪 **Birim Testleri (JUnit)**
* Her bir sınıfa özel JUnit testleri yazılabilir:

    * `TransactionTest`
    * `BlockTest`
    * `WalletTest`
    * `BlockchainValidationTest`
---


