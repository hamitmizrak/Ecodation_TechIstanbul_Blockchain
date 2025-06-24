# Blockchain Konu başlıkları

---


## 🔹 1.1. Blockchain Nedir?

**Blockchain**, verilerin bloklar halinde ve kronolojik sırayla birbirine bağlandığı, merkezi olmayan ve değiştirilemez bir dijital kayıt sistemidir. Temel amacı, herhangi bir otoriteye güven duymadan, sistemdeki tüm katılımcıların aynı veriye güvenle ulaşabilmesini sağlamaktır.

Her blok, belirli sayıda işlemi, bir zaman damgasını, kendisine ait hash’i ve bir önceki bloğun hash’ini içerir. Bu yapı sayesinde:

* Veriler **manipüle edilemez**,
* Kayıtlar **şeffaf ve izlenebilir** olur,
* Sistem **dağıtık (P2P) yapıda** çalışır.

Kısaca:

> Blockchain = Kriptografi + Zaman damgası + Dağıtık defter + Konsensüs algoritması

---

## 🔹 1.2. Merkeziyetsizlik (Decentralization) Nedir?

**Merkeziyetsizlik**, bir sistemin tek bir kontrol noktasına değil, çok sayıda katılımcıya (node/düğüm) dayalı çalışmasıdır.

### Merkezi Sistem:

* Tek bir otorite (banka, şirket, devlet) sistemi yönetir.
* Örnek: Facebook, geleneksel bankacılık

### Merkeziyetsiz Sistem:

* Her katılımcı kendi verisinin sahibidir.
* Ağa katılan her düğüm aynı verilere sahiptir.
* Örnek: Bitcoin ağı, IPFS

### Avantajları:

* Tek bir hata noktasına (single point of failure) dayanmaz.
* Sansüre ve manipülasyona karşı dirençlidir.
* Güven, merkezi aktöre değil, kriptografik kurallara dayanır.

---

## 🔹 1.3. Dağıtık Sistemler (Distributed Systems) ve Blockchain İlişkisi

**Dağıtık sistem**, tek bir fiziksel konum yerine çok sayıda bilgisayarın iş birliğiyle çalışan sistemlerdir. Blockchain bu mimari üzerine kuruludur.

### Dağıtık sistemlerde:

* Tüm düğümler (node) kendi yerel kopyalarını tutar.
* Bir düğüm arızalansa bile sistem çalışmaya devam eder.
* Verilerin bütünlüğü, konsensüs algoritmalarıyla korunur.

### Blockchain ve Dağıtık Sistem:

* Blockchain bir dağıtık defter teknolojisidir (**Distributed Ledger Technology, DLT**).
* Her blok zinciri ağı bir dağıtık sistemdir ama her dağıtık sistem bir blockchain değildir.
* Ağdaki düğümler senkronize çalışarak işlem doğrulama ve zincir güncellemesi yapar.

---

## 🔹 1.4. Kriptografi Temelleri

Blockchain’in güvenliğini sağlayan en kritik unsur **kriptografi**dir. Temel unsurlar şunlardır:

### 1.4.1. Hash Fonksiyonu

* Girdi olarak aldığı veriden sabit uzunlukta **özet (hash)** üretir.
* Aynı veri → aynı hash
* Farklı veri → çok farklı hash (Avalanche effect)
* Geri döndürülemez (tek yönlü fonksiyon)
* Örnek: SHA-256 → “Hello” = `185f8db32271fe25f561a6fc938b2e264306ec304eda518007d1764826381969`

### 1.4.2. Dijital İmza

* Özel anahtarla oluşturulan ve veriye özgü bir şifredir.
* Verinin **sahipliğini ve bütünlüğünü** garanti eder.
* İmzayı herkes doğrulayabilir ama sadece sahibi oluşturabilir.
* Kullanılan algoritmalar: ECDSA, RSA

### 1.4.3. Merkle Ağacı (Merkle Tree)

* İşlem verilerini özetleyerek ağaç yapısında depolar.
* Her dal: Altındaki verilerin hash’idir.
* Merkle Root: Tüm işlemlerin kriptografik özeti
* Amaç: Hızlı ve güvenli doğrulama (veri bütünlüğü)

---

## 🔹 1.5. Blok, Zincir ve Zaman Damgası Yapısı

### 🔸 Blok (Block)

* Blockchain’deki temel veri yapısıdır.
* Her blok şunları içerir:

    * Zaman damgası
    * İşlem listesi
    * Önceki bloğun hash’i
    * Blok hash’i
    * Nonce (bazı sistemlerde)

### 🔸 Zincir (Chain)

* Her blok bir öncekinin hash’ini içerir → Zincir oluşur.
* Bu bağlantı sayesinde:

    * Veriler geçmişe dönük **değiştirilemez** hale gelir.
    * Bir blokta yapılacak küçük değişiklik bile tüm zinciri etkiler.

### 🔸 Zaman Damgası (Timestamp)

* Blokların oluşturulma zamanı kayıt altına alınır.
* Zincirin sırasal ve kronolojik ilerlemesini sağlar.

---

## 🔹 1.6. Public Key ve Private Key Nedir?

### Açık Anahtar (Public Key)

* Herkese açık bir kimlik gibidir.
* Size ait cüzdan adresini oluşturur.
* Başkaları size coin/varlık göndermek için bunu kullanır.

### Özel Anahtar (Private Key)

* Sadece sizin bilmeniz gereken şifredir.
* Cüzdanınıza ve işlemlerinize erişim sağlar.
* Dijital imza bu anahtarla üretilir.
* Kaybedilirse: Cüzdan sonsuza kadar kilitlenir.

### Açık – Özel Anahtar İlişkisi:

* Asimetrik kriptografi (örneğin ECDSA) kullanılır.
* Şifreleme ve doğrulama ters anahtarlarla yapılır.

```plaintext
Özel Anahtar → Açık Anahtar → Cüzdan Adresi
```

---

## 📌 Özet Tablo

| Kavram             | Açıklama                                                         |
| ------------------ | ---------------------------------------------------------------- |
| Blockchain         | Dağıtık, değiştirilemez, zincir yapılı kayıt sistemi             |
| Merkeziyetsizlik   | Kontrolün tek bir elde değil, ağın tamamında olması              |
| Dağıtık Sistem     | Verilerin birden fazla düğümde paylaşılması                      |
| Hash               | Sabit uzunluklu, tek yönlü veri özeti                            |
| Dijital İmza       | Verinin bütünlüğünü ve sahipliğini doğrular                      |
| Merkle Ağacı       | İşlem verilerini hiyerarşik hash yapısıyla özetler               |
| Blok               | Verilerin kaydedildiği temel yapı birimi                         |
| Zincir             | Blokların önceki hash’lerle bağlandığı yapı                      |
| Zaman Damgası      | Blokların kronolojik sırasını belirler                           |
| Public/Private Key | Kimlik ve güvenlik altyapısını oluşturan kriptografik anahtarlar |

---