Stable language features

1. JVM 레코드 지원

```kotlin
@JvmRecord
data class User(val name: String, val age: Int)
```
Java기능인 레코드 클래스와의 상호운용성을 유지하기 위해 - java 14부터 지원

레코드 클래스란?

해당 record 클래스는 final 클래스이라 상속할 수 없다.

각 필드는 private final 필드로 정의된다.

모든 필드를 초기화하는 RequiredAllArgument 생성자가 생성된다.

각 필드의 getter는 getXXX()가 아닌, 필드명을 딴 getter가 생성된다.(name(), age(), address())

```java
public class SampleRecord {
   private final String name;
   private final Integer age;
   private final Address address;
 
   public SampleRecord(String name, Integer age, Address address) {
      this.name = name;
      this.age = age;
      this.address = address;
   }
 
   public String getName() {
      return name;
   }
 
   public Integer getAge() {
      return age;
   }
 
   public Address getAddress() {
      return address;
   }
}


public record SampleRecord(
   String name,
   Integer age,
   Address address
) {}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-enable-preview")
        jvmTarget = "11"
    }
}
```
Enabling JVM records﻿

JVM records require the 16 target version or higher of the generated JVM bytecode.

2. Sealed modifier가 인터페이스에서도 적용 가능

Sealed class는 Super class를 상속받는 Child 클래스의 종류 제한하는 특성을 갖고 있는 클래스입니다. 어떤 클래스를 상속받는 하위 클래스는 여러 파일에 존재할 수 있기 때문에 컴파일러는 얼마나 많은 하위 클래스들이 있는지 알지 못합니다. 하지만 Sealed class는 동일 파일에 정의된 하위 클래스 외에 다른 하위 클래스는 존재하지 않는다는 것을 컴파일러에게 알려주는 것과 같습니다.

```kotlin
sealed interface Polygon

fun draw(polygon: Polygon) = when ( polygon ) {
  is Rectangle -> //
  is Triangle => //
  // else is not needed - all possible implementations are covered
}
```
sealed 클래스의 테스트 버전 지원은 Java 15 및 JVM에 도입되었습니다. 앞으로 사용자가 Kotlin 코드를 최신 JVM으로 컴파일하는 경우(이 기능이 안정화되면 JVM 17 이상일 가능성이 높음)

→ JVM 지원은 아직 제공되지 않음

→ 함수형 프로그래밍과 패턴매칭을 사용할 것이 아니면 쓸일은 없다.

switch에서 state와 expression의 차이

3. Inline classes → value class

inline 클래스는 기본 유형 (primitive type)이나 참조 유형(String)의 래퍼가 될 수 있음!

value class 자세한 사용법

https://github.com/Kotlin/KEEP/blob/master/proposals/inline-classes.md#description 
```kotlin
@JvmInline //required for the JVM backend
value class Password(val s: String)
```

```kotlin
@JvmInline
value class Cents(val amount: Int)

fun payMoney(price: Cents)
payMoney(500) //500 what??
payMoney(Cents(500))
payMoney(Cents.fromEuros(5))
```
inline → object를 할당할때 시간이 걸리는 작업, class를 만드는 것처럼 코드를 풀어서 불 필요한 새로운 오브젝트 생성과정이 줄어듬 - 라이브러리성 작업을 하는 사람은 알아볼 필요 있음

Standard and test library improvements

부호 없는 정수 유형 및 그 범위, 진행, 함수를 지원하는 새로운 안정적인 API. 부호 없는 정수

최신 논블로킹(non-blocking) Java IO를 Kotlin-idiomatic 스타일로 사용할 수 있도록 지원하는 java.nio.file.Path용 확장 함수

문자열과 문자의 대소문자 변경이 가능한 새로운 로케일에 구애받지 않는 API, 문자와 정수 코드 및 숫자 값 간의 변환이 가능한 새 함수 세트, 더 많은 char 함수에 대한 멀티 플랫폼 지원 등이 포함된 String 및 Char API 개선 사항

내부 표현에 Long 값을 사용, 기간을 Long 값으로 검색할 수 있도록 새 프로퍼티를 제공하는 등의 Duration API 변경 사항

1. 부호 없는 정수 유형

Uint, ULong, UByte, UShort

Unsigned integers﻿

In addition to integer types, Kotlin provides the following types for unsigned integer numbers:

UByte: an unsigned 8-bit integer, ranges from 0 to 255

UShort: an unsigned 16-bit integer, ranges from 0 to 65535

UInt: an unsigned 32-bit integer, ranges from 0 to 2^32 - 1

ULong: an unsigned 64-bit integer, ranges from 0 to 2^64 - 1

index % size //Negative Results -3 -2 
index.mod(size) //Only Positive 1 2 

→ db와 값도 맞춰야하고 하므로 특정한 목적이 없으면 X

2. java.nio.file.Path 

 
```kotlin
val basePath = Path("inputs")
val subdirectory = basPath/"mySourceCode"
val kotlinFiles = subdirectory.listDirectoryEntries("*.kt")
```
→ temp file,

3. Duration 

Internal value representation now uses Long instead of Double to provide better precision.

There is a new API for conversion to a particular time unit in Long. It comes to replace the old API, which operates with Double values and is now deprecated. For example, Duration.inWholeMinutes returns the value of the duration expressed as Long and replaces Duration.inMinutes.
```kotlin
val duration = Duration.milliseconds(120000)
println("There are ${duration.inWholeSeconds} seconds in ${duration.inWholeMinutes} minutes")

There are 120 seconds in 2 minutes
```
4. assert - kotlin test

```kotlin
open class Service
class RealService : Service()
class MockService : Service()

fun createService(): Service = RealService()

assertIs<RealService>(createService())
assertIstNot<MockService>(createService())
assertContentEquals(expectedArray, actualArray)
```
In Kotlin 1.5.0, we are deprecating @JvmDefault and the old Xjvm-default modes: -Xjvm-default=enable and -Xjvm-default=compatibility.

→ interface 자바코드 jvmdefault  biopassport에서는 default = all 사용했음

→ default all로 바뀜

5.Improvements to handling nullability annotations + char

-Xtype-enhancement-improvements-strict-mode
```kotlin
val data = listOf("Kotlin", "1.5")
println(data.firstNotNullOf(String::toDoubleOrNull))
println(data.firstNotNullOfOrNull(String::toIntOrNull))
```
char api
```kotlin
val chars = listOf('a', '1', '+')
val (letterOrDigitList, notLetterOrDigitList) = chars.partition { it.isLetterOrDigit() }
println(letterOrDigitList) // [a, 1]
println(notLetterOrDigitList) // [+]
```
isDigit()
isLetter()도 있음


##### 변경 

Standard library﻿

The standard library has received a range of changes and improvements, from stabilizing experimental parts to adding new features:

Stable unsigned integer types

Stable locale-agnostic API for uppercase/lowercase text

Stable Char-to-integer conversion API

Stable Path API

Floored division and the mod operator

Duration API changes

New API for getting a char category now available in multiplatform code

New collections function firstNotNullOf()

Strict version of String?.toBoolean()

--> kotlin 1.5 아쉬운 기능 + , compile jvm 개선, 성능 개성


 
