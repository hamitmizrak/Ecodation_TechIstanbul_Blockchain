// com.hamitmizrak.ecodation_techistanbul_blockchain

module com.hamitmizrak {

    // JavaFX'in temel bileşenlerini kullanmak için gerekli modüller
    // JavaFX kontrol bileşenlerini (Button, Label, TextField vb.) kullanabilmek için gereklidir.
    requires javafx.controls;

    // JavaFX FXML dosyalarını (FXML UI tasarımları) yükleyebilmek için gereklidir.
    requires javafx.fxml;

    // #######################################################################################
    // UI geliştirme için kullanılan harici kütüphaneler
    // ControlsFX, gelişmiş UI bileşenlerini (örn: Notifikasyonlar, Doğrulama Alanları) sağlar.
    requires org.controlsfx.controls;
    // FormsFX, formlar için gelişmiş bileşenler sunan bir kütüphanedir.
    requires com.dlsc.formsfx;
    // ValidatorFX, form doğrulama işlemleri için kullanılır.
    requires net.synedra.validatorfx;
    // İkon kütüphanesi, UI'de çeşitli ikonları kullanmaya olanak tanır.
    requires org.kordamp.ikonli.javafx;
    // BootstrapFX, Bootstrap benzeri CSS stillerini JavaFX'e entegre eder.
    requires org.kordamp.bootstrapfx.core;

    // #######################################################################################
    // Lombok kütüphanesi, Java'da getter, setter, constructor gibi metotları otomatik oluşturur.
    // Lombok, derleme zamanı (compile-time) kullanıldığı için "static" olarak eklenmiştir.
    requires static lombok;

    // JDBC ile veritabanı bağlantısı kurabilmek için gerekli modül
    // Java'daki SQL işlemlerini (Connection, Statement, ResultSet vb.) gerçekleştirebilmek için gereklidir.
    requires java.sql;
    requires org.apache.commons.codec;

    // #######################################################################################
    // Paket Erişimlerine İzin vermek
    // `opens` ifadesi, bir paketin runtime'da (çalışma zamanında) refleksiyon (reflection) kullanılarak erişilebilir olmasını sağlar.
    // Ana paket (Root package) açılıyor, böylece FXML dosyalarından erişilebilir.
    opens com.hamitmizrak to javafx.fxml;


    // Controller sınıfları FXML tarafından kullanılacağı için açılması gerekiyor.
    opens com.hamitmizrak.controller to javafx.fxml;

    // DTO (Data Transfer Object) paketinin içeriği, JavaFX bileşenleri ve Lombok tarafından erişilebilir olmalıdır.
    opens com.hamitmizrak.dto to javafx.base, lombok;

    // DAO (Data Access Object) sınıfları, SQL bağlantısı kullandığı için açılıyor.
    opens com.hamitmizrak.dao to java.sql;

    // Veritabanı bağlantısı sağlayan sınıfların da SQL modülüne açık olması gerekiyor.
    opens com.hamitmizrak.database to java.sql;

    // #######################################################################################
    // Paket dışa aktarmak
    // `exports` ifadesi, paketin diğer modüller tarafından erişilebilir olmasını sağlar.

    // blockchain paketini dışa açarak controller gibi sınıflardan erişimi mümkün kılıyor.
    exports com.hamitmizrak.blockchain; // Blockchain paketini dışa aç

    // Ana paketi dış dünyaya açıyoruz. Diğer modüller bu paketin içeriğini kullanabilir.
    exports com.hamitmizrak;

    // DAO sınıflarını dışarıya açıyoruz. Böylece başka modüller veritabanı işlemlerini çağırabilir.
    exports com.hamitmizrak.dao;

    // // Veritabanı bağlantı paketini dış dünyaya açıyoruz. Diğer modüller DB bağlantısını kullanabilir.
    exports com.hamitmizrak.database;

}

