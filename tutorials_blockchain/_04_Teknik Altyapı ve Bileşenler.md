# Blockchain Konu başlıkları

---

## 🔹 2.1. Blok Yapısı: Header, Hash, Nonce, Data
Her **blok**, belirli bileşenlerden oluşur ve bu bileşenler blokların güvenli şekilde birbirine bağlanmasını sağlar.

### 🧱 Blok Yapısının Ana Bileşenleri:

| Alan       | Açıklama                                                                          |
| ---------- | --------------------------------------------------------------------------------- |
| **Header** | Blok üst bilgileri içerir: zaman damgası, önceki blok hash’i, nonce               |
| **Hash**   | Blok içeriğinden türetilen sabit uzunlukta dijital özet                           |
| **Nonce**  | Rastgele bir sayı; hash algoritmasında istenen zorluğu sağlamak için değiştirilir |
| **Data**   | Bu blokta yer alan işlemler veya bilgiler (örn. transferler, kontratlar)          |

### Örnek (Blok Java Sınıfı gibi düşünebiliriz):

```json
{
  "index": 5,
  "timestamp": "2025-06-19T12:30:00",
  "transactions": [...],
  "previousHash": "00123abef",
  "nonce": 28473,
  "hash": "0000f82abc234f..."
}
```

---

## 🔹 2.2. SHA-256 ve Diğer Hash Algoritmaları

### ✅ SHA-256 Nedir?

* **Secure Hash Algorithm 256-bit** anlamına gelir.
* Herhangi bir uzunluktaki girdiden 256 bitlik (64 karakterlik) sabit uzunlukta bir çıktı üretir.
* Tek yönlüdür: Hash'ten orijinal veri geri elde edilemez.

### Özellikleri:

* Deterministik
* Çakışmaya karşı dirençli (collision resistant)
* Hızlı çalışır

### Örnek:

```plaintext
Girdi:  blockchain
SHA-256: 7a44c2d3f20cc5e6d5717b4cd103d814e3fd6702d6e2bc9eea16bb1d5b1dcd03
```

### Diğer Hash Algoritmaları:

| Algoritma | Açıklama                                |
| --------- | --------------------------------------- |
| SHA-1     | Artık güvenli kabul edilmez             |
| SHA-512   | Daha uzun çıktı üretir                  |
| Keccak    | Ethereum'da kullanılır                  |
| Blake2    | Yeni nesil, hızlı algoritmalar arasında |

---

## 🔹 2.3. Merkle Tree (Merkle Ağacı)

Merkle Ağacı, blok içinde yer alan işlemleri **özetleyen** ve veri bütünlüğünü **hiyerarşik olarak doğrulayan** yapıdır.

### Yapısı:

* Her yaprak → bir işlemin hash'i
* Yapraklar çiftler hâlinde gruplanır → üst hash'ler hesaplanır
* En üstte **Merkle Root** oluşur → blok header içinde yer alır

### Amaç:

* Veri değişikliği tespitini hızlıca sağlar
* Bellek ve işlem gücü tasarrufu
* SPV (Simplified Payment Verification) gibi hafif doğrulama sağlar

### Görsel:

```
        Merkle Root
         /      \
   Hash(1+2)   Hash(3+4)
   /    \       /    \
 H1    H2     H3     H4
```

---

## 🔹 2.4. Genesis Block Nedir?

**Genesis Block**, zincirdeki **ilk bloktur**. Her blockchain’in bir adet Genesis Block’u vardır ve özel anlam taşır.

* Önceki blok hash’i **boştur** (`"000..."`)
* Sabit bir noktadır; zincirin köküdür
* Kod içinde manuel olarak tanımlanır (hardcoded)

### Örnek (Bitcoin Genesis Block):

> Timestamp mesajı: *“The Times 03/Jan/2009 Chancellor on brink of second bailout for banks”*

Bu, aynı zamanda Satoshi Nakamoto'nun sisteme olan eleştirisini de yansıtır.

---

## 🔹 2.5. Node (Düğüm) Çeşitleri

### 1. **Full Node**

* Tüm blok zincirini indirir ve saklar
* Tüm işlemleri doğrular
* Ağın omurgasını oluşturur

### 2. **Light Node (SPV)**

* Sadece başlıkları indirir (blok verisi indirimez)
* Daha az işlem gücü ve depolama ister
* Kullanıcı cüzdanları bu türdür

### 3. **Miner Node**

* Blok üretir (madencilik yapar)
* Hash problemi çözer, yeni blok ekler
* İşlem karşılığı ödül alır (örneğin BTC)

### 4. **Archive Node (Bazı sistemlerde)**

* Tüm geçmiş durumları (state) saklar
* Geliştiriciler ve araştırmacılar için faydalıdır

---

## 🔹 2.6. Blockchain Mimarisi ve Veri Yapısı

### Mimarideki Katmanlar:

| Katman                         | Açıklama                                     |
| ------------------------------ | -------------------------------------------- |
| **Ağ Katmanı (Network Layer)** | P2P protokolü, veri yayılımı                 |
| **Veri Katmanı (Data Layer)**  | Blok yapısı, zincirleme, Merkle ağacı        |
| **Konsensüs Katmanı**          | Blokların doğrulanması                       |
| **Uygulama Katmanı**           | Akıllı kontratlar, dApp’ler, token işlemleri |

### Veri Yapısı Özeti:

* **Linked List** mantığında zincir
* Bloklar arası bağlantı → kriptografik hash ile
* Her blok işlemleri içerir → işlemler Merkle ağacı ile özetlenir

---

## 🔹 2.7. Konsensüs Algoritmaları

Konsensüs, dağıtık sistemin **tek bir karara varmasını** sağlar. Aksi takdirde herkes farklı zincir versiyonuna sahip olabilir.

### 🔸 1. **Proof of Work (PoW)**

* İşlem: Matematiksel problemi çözerek blok eklenir
* Zorluk ayarlanabilir
* Yüksek enerji tüketir
* Örnek: **Bitcoin**

### 🔸 2. **Proof of Stake (PoS)**

* Blok üreticileri, ellerindeki token oranına göre seçilir
* Enerji tasarrufludur
* Daha hızlı blok üretimi sağlar
* Örnek: **Ethereum 2.0, Cardano**

### 🔸 3. **Delegated PoS (DPoS)**

* Token sahipleri temsilciler (delegates) seçer
* Hızlı ve demokratiktir
* Temsilci sayısı sabittir (örnek: 21)
* Örnek: **EOS, TRON**

### 🔸 4. **PBFT (Practical Byzantine Fault Tolerance)**

* 1/3 kötü niyetli düğüme kadar sistem dayanabilir
* Blokların çoğunluk ile kabul edilmesi
* Daha çok özel blockchain’lerde kullanılır
* Örnek: **Hyperledger, Tendermint (Cosmos)**

### 🔸 5. **Hybrid Modeller**

* PoW + PoS birlikte çalışabilir (örneğin Ethereum’un geçmiş yapısı)
* Hem güvenlik hem verimlilik hedeflenir
* Bazı sistemlerde zamanlama ya da türlere göre ayrılır

---

## 🧾 Özet Tablo:

| Bileşen       | Açıklama                                                |
| ------------- | ------------------------------------------------------- |
| Blok          | İşlemleri ve önceki bloğun hash’ini içerir              |
| Hash          | Verilerin kriptografik özeti                            |
| SHA-256       | Blockchain’de yaygın kullanılan hash fonksiyonu         |
| Merkle Ağacı  | İşlemlerin doğrulanmasını kolaylaştırır                 |
| Genesis Block | Zincirin ilk ve sabit bloğu                             |
| Node          | Ağa katılan bilgisayar; işlevine göre sınıflanır        |
| Mimariler     | Layered yapı; P2P, veri, konsensüs, uygulama katmanları |
| Konsensüs     | Ağın aynı zincir üzerinde anlaşmasını sağlar            |

---