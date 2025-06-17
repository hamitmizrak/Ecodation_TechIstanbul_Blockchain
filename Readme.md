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
