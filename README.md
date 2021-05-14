# Kotlin In Action

## 목차

[1. 코틀린이란 무엇이며, 왜 필요한가?](#1-코틀린이란-무엇이며-왜-필요한가?)

[2. 코틀린 기초](#2-코틀린-기초)

[3. 함수 정의와 호출](#3-함수-정의와-호출)

[4. 클래스, 객체, 인터페이스](#4-클래스-객체-인터페이스)

[5. 람다로 프로그래밍](#5-람다로-프로그래밍)

​	

### 1. 코틀린이란 무엇이며, 왜 필요한가?

#### 1.1코틀릿 맛보기

```kotlin
//데이터 클래스
data class Person(
    var name: String,
    val age: Int? = null //널이 될 수 있음
)

//최상위 함수
fun main(args: Array<String>) {
    val persons = listOf(
        Person("영희"),
        Person("철수", age = 29) //이름 붙인 파라미터
    )

    val oldest = persons.maxBy { it.age ?: 0 } //람다식 엘비스 연산자
    println("나이가 가장 많은 사람: $oldest") //문자열 템플릿
}
//toSting 자동생성
//나이가 가장 많은 사람: Person(name=철수, age=29)
```

엘비스 연산자 ?: null인 경우 0을 반환 그렇지 않은 경우 age 반환



#### 1.2코틀린의 주요 특성

1. 자바를 사용하는 백엔드, 안드로이드 애플리케이션, js 등 많은 곳에서 사용 가능

2. 정적 타입 지정 언어 (statically typed)

   > 구성 요소의 타입을 컴파일 시점에 알 수 있고 프로그램 안에서 객체의 필드나 메소드를 사용할 때마다 컴파일러가 타입을 검증해준다는 것    : Groovy JRuby가 동적 타입 지정 언어

- 자바와 달리 코틀린은 type inference가 가능

  > 정적 타입의 장점
  >
  > - 성능
  >   - 실행 시점에 어떤 메소드를 호출할지 알아내는 과정이 필요 없으므로 메소드 호출이 빠름
  > - 신뢰성
  >   - 컴파일러가 프로그램의 정확성을 검증하기 때문에 오류로 중단될 가능성이 적어짐
  > - 유지 보수성
  >   - 객체가 어떤 타입에 속하는지 알 수 있기 때문에 처음 보는 코드를 다룰 때도 쉬움
  > - 도구 지원
  >   - 정적 타입 지정을 활용하면 안전한 리팩토링을 할 수 있다 IDE 다른 지원 기능도 더 잘 만들어진다.

+ nullable type을 지원하므로 컴파일 시점에 NPE를 더 잘 잡을 수 있음
+ function type에 대한 지원



##### 함수형 프로그래밍이란과 객체지향 프로그래밍

- 일급 시민인 함수 : 함수를 일반 값처럼 다룰 수 있다.
- 불변성 : 함수형 프로그래밍에서는 일단 만들어지고 나면 내부 상태가 절대로 바뀌지 않는 불변 객체를 사용
- side effect 없음 : 입력이 같으면 항상 같은 출력을 내놓고 다른 객체의 상태를 변경하지 않음 (pure function)

첫 번째 장점 :

내부만 조금 다른 중복 코드가 있다.

lamda expression을 이라 불리는 anonymous function 구문을 이용하면 그런 함수를 간단히 표현가능

```kotlin
fun findAlice() = findPerson { it.name == "Alice" }
fun findBob() = findPerson { it.name == "Bob" }
```

두 번째 장점 : safe multithreading 

마지막 장점 : 함수는 준비 코드 없이 독립적으로 테스트 할 수 있음

[kotlin opensource](https://github.com/jetbrains/kotlin)



#### 1.3 코틀린 응용

- HTML 생성 라이브러리

```kotlin
fun renderPersonList(Persons: Collection<Person>) =
	createHTML().table {
        for (person in persons){
            tr{
                td{ +person.name }
                td{ +person.age }
            }    
        }
	}
```

- DLS 기능을 활용 (추후 학습)
- 안드로이드 프로그래밍



#### 1.4 코틀린의 철학

- 실용성, 간결성, 안전성, 상호운용성

코틀린은 getter, setter, 생성자 파라미터를 묵시적으로 제공

안전성과 생산성 사이에는 trade off관계가 성립하는데 JVM에 이점을 취하는 동시에 좀 더 간결하게 하여 생산성 증가

> ? 하나로 nullable을 파악 가능


#### 1.5 코틀린 도구 사용

**코틀린 빌드 과정**

![kotlin build](https://workingdev.net/images/kotlin-compilation-process.png)

- maven, gradle과 호환
- 대화형 셀 REPL(read-eval-print loot) 코틀린 한 줄 입력하면 즉시 그 코드를 실행한 결과 확인 가능





### 2. 코틀린 기초

- Hello, World!

```kotlin
fun main(args: Array<String>) {
	println("Hello, world!")
}
```

#### 2.1 변수

- 함수

```kotlin
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

//식이 본문인 함수
fun max(a: Int, b: Int): Int = if (a > b) a else b
//반환타입 생략도 가능 - 컴파일러가 식의 결과 타입을 함수 반환 타입으로 정해주기 때문에
fun max(a: Int, b: Int) = if (a > b) a else b
```

> kotlin은 루프를 제외한 대부분의 제어 구조가 식(expression)

- 변수

```kotlin
val question = "삶, 우주, 그리고 모든 것에 대한 궁극적인 질문"
val answer = 42
val answer: Int = 42
val yearsToCompute = 7.5e6

//초기화 하지 않고 변수를 선언하려면 변수 타입을 명시
val answer: Int
answer = 42
```

> val 은 변경 불가능한 (final) 
> var은 변경 가능하다.
>
> 기본적으로 val을 사용하면 객체를 side effect가 없는 함수와 조합이 가능하다.

```kotlin
//다음과 같은 코드는 정상 참조는 불변이라도 내부 값은 변경될 수 있음
val message: String
if (canPerformOperation()) {
	message = "Success"
} else {
	message = "Failed"
}

val languages = arrayListOf("Java")
languages.add("Kotlin")
```

- 문자열 템플릿

```kotlin
// $를 쓰고 싶으면 \$ escape시켜야 한다.
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
	println("Hello, $name!")
}


// 이것도 가능
fun main(args: Array<String>) {
    if (args.size > 0) {
        println("Hello, ${args[0]}")
    }
}

//한글은 $name이 작동 안 하므로 ${name} 습관을 들이는 것이 좋다.
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
	println("${name}님 반가워요")
}

fun main(args: Array<String>) {
	println("Hello, ${if (args.size > 0) args[0] else "Kotlin"}!")
}
```

#### 2.2 클래스와 프로퍼티

##### 2.2.1 클래스와 프로퍼티

```kotlin
//클래스
class Person(val name: String)

//프로퍼티 - val은 읽기 전용 var은 변경 가능
class Person(
	val name: String //getter만 제공
    var isMarried: Boolean //getter setter 제공
)

fun main(args: Array<String>) {
    val person = Person("Bob", true)
    
    println(person.name)
    person.isMarried = false
    println(person.isMarried)
}
```

##### 2.2.2 커스텀 접근자

```kotlin

class Rectangle(val height:Int, val width: Int) {
    val isSquare: Boolean
    	get() {
            return height == width
        }
	    //get() = height == width도 가능
}

fun main(args: Array<String>) {
    val rectangle = Rectangle(3, 3)
    println(rectangle.isSquare)
}
//true
```

#### 코틀린 소스코드 구조: 디렉토리와 패키지

```kotlin
package geometry.shapes

import java.util.Random

class Rectangle(val height: Int, val width: Int) {
	val isSquare: Boolean
	    get() = height == width도 가능
}

fun createRandomRectangle() : Rectangele {
	val random = Random()
	return Rectangle(random.nextInt(), random.nextInt())
}

//다른 곳에서 import할때도 같음
```



#### 2.3 선택 표현과 처리: enum과 when

##### enum

```kotlin
//1
enum class Color {
	RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
//2
enum class Color (
	val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255), ORANGE(255, 165, 0); 
    //유일한 세미콜론
    fun rgb() = (r * 256 + g) *256 + b
}

fun main(args: Array<String>) {
    println(Color.BLUE.rgb())
}
//255
```

##### when - 분기 끝에 break;필요 없음

```kotlin
fun getMnemonic(color: Color) =
	when (color) {
		Color.RED, Color.ORANGE -> "Richard" //,분기도 가능
		Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
    }

//enum 상수 값을 import하면 Color.* Color도 필요 없음

//섞는 것도 가능
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        else -> throw Exception("Dirty color")
    }

//인자 없는 when도 사용가능
when {
    (c1 == RED && c2 == YELLOW) ||
    (c1 == YELLOW && c2 == RED) ->
    ORANGE
}
```


##### 스마트 캐스트 : 타입 검사와 타입 캐스트를 조합

```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num
        return n.value
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left) // 자동 변환 되었음
    }
    throw IllegalArgumentException("Unknown expression")
}
//리팩토링
fun eval(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval(e.right) + eval(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }
//when 사용한 리팩토링
fun eval(e: Expr): Int =
    when (e) {
        is Num ->
            e.value
        is Sum ->
            eval(e.right) + eval(e.left)
        else ->
            throw IllegalArgumentException("Unknown expression")
    }

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("Sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown Expression")
    }
//블록의 마지막 식이 결과라는 규칙은 블록이 값을 만들어내야 하는 경우 항상 성립
```

코틀린은 is 를 사용해 변수 타입을 검사

e is Num

e is Sum

> 스마트캐스트
> is로 검사하고 나면 굳이 변수를 원하는 타입으로 캐스팅하지 않아도 마치 처음부터 그 변수가 원하는 타입으로 선언된 것처럼 사용할 수 있다.



#### 2.4 대상을 이터레이션: while과 for 루프

while은 자바와 동일, for은 for-each만 존재 for <아이템> in <원소들>

##### 2.4.2 수에 대한 이터레이션: 범위와 수열

```kotlin
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 4 == 0 -> "Buzz "
    else -> "$i "
}

fun main(args: Array<String>) {
    for (i in 1..100) {
        print(fizzBuzz(i))
    }

    for  (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
}

//..은 끝 값을 포함 until size를 사용하면 size-1까지 가능
```



##### 2.4.3 맵에 대한 이터레이션

```kotlin
import java.util.*

fun main(args: Array<String>) {
    val binaryReps = TreeMap<Char, String>() //키에대해 정렬
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("${index+1}: $element")
    }

    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know"
}
```

```kotlin
println("Kotlin" in "Java".."Scala") // true
println("Kotlin" in setOf("Java", "Scala")) // false
```



#### 2.5 코틀린의 예외 처리

```kotlin
import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {
    val percentage = 10
    if (percentage !in 0..100) {
        throw IllegalArgumentException(
            "A percentage value must be between 0 and 100: $percentage"
        )
    }

    val number = 10
    val percentage2 =
        if (number in 0..100)
            number
        else
            throw IllegalArgumentException(
                "A percentage value must be between 0 and 100: $number"
            )

    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))

    val reader2 = BufferedReader(StringReader("not a number"))
    readNumber(reader2)
}

/* 예외 발생시 값을 반환하지 않음 + 다음 println을 실행하지 않음
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }
    println(number)
}
*/

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    } finally {
        reader.close()
    }
}

```

> 코틀린은 unchecked Exception과 checked Exception을 구별하지 않음
>
> try-with-resource는 문법 제공하지 않지만 라이브러리 함수로 같은 기능을 구현함



### 3. 함수 정의와 호출

#### 3.1 코틀린에서 컬렉션 만들기

```kotlin
//set
val set = hashSetof(1, 7, 53)
//list
val list = arrayListOf(1, 7, 53)
//map - to는 일바 함수이다.
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
```

> getClass를 확인해보면 표준 java class인 것을 확인할 수 있음

코틀린은 자바 컬렉션과 똑같은 클래스지만 더 많은 기능을 쓸 수 있음

```kotlin
val strings = listOf("first", "second", "third")
println(strings.last())

val numbers = listOf(1, 14, 2)
println(numbers.max())
```



#### 3.2 함수를 호출하기 쉽게 만들기

toString을 (1; 2; 3) 같이 만들고 싶을 때 예제

```kotlin
import java.lang.StringBuilder

fun <T> joinToString(
    collection: Collection<T>,
    seperator: String = ", ", //코틀린은 디폴트 값을 지정할 수 있음
    prefix: String = "",
    postfix: String = ""
    ) : String {
    val result = StringBuilder(prefix)

    for((index, element) in collection.withIndex()) {
        if(index > 0) result.append(seperator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)
    println(joinToString(list, "; ", "(", ")"))
    
    //코틀린에서는 인자 중 일부의 이름을 명시할 수 있는데 명시하면 그 뒤에 인자는 모두 이름을 명시
    println(joinToString(list, seperator = " ", prefix = "(", postfix = ")"))
}
```

> 자바에서 이런 기능을 쓰려면 @JvmOverloads 어노테이션을 추가하면 자동으로 맨 마지막 파라미터부터 하나씩 생략한 오버로딩한 자바 메소드를 추가해줌



##### 3.2.3 정적인 유틸리티 클래스 없애기: 최상위 함수와 프로퍼티

자바에서는 특별한 상태나 인스턴스를 메소드는 없는 클래스가 생격난다. Util 클래스 같은

```kotlin
@file:JvmName("StringFunctions")
package strings
fun joinToString(...) : String {...}
```

최상위 프로퍼티도 접근자 메소드를 통해 자바 코드에 노출 -> const 사용

```kotlin
const val UNIX_LINE_SEPERATOR ="\n"
=> public static final String UNIX_LINE_SEPERATOR ="\n"
```



#### 3.3 메소드를 다른 클래스에 추가 : 확장 함수와 확장 프로퍼티

확장 함수는 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있찌만 그 클래스의 밖에 선언된 함수이다.

```kotlin
package strings 
fun String.lastChar() : Char = this.get(this.length - 1)
//   ^ receiver type            ^ reveiver object

char c = StringUtilKt.lastChar("Java")
//로 쓸 수 있음
```

##### 3.3.1 임포트와 확장 함수

다음과 같은 방법으로 import해서 사용할 수 있음

```kotlin
import strings.lastChar
import strings.*
import strings.lastChar as last
```

##### 3.3.3. 확장 함수로 유틸리티 함수 정의

```kotlin
import java.lang.StringBuilder

fun <T> Collection<T>.joinToString(
    seperator: String = ", ",
    prefix: String = "(",
    postfix: String = ")"
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(seperator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
//list Collection이 아니라 불가능
```

##### 3.3.4 확장 함수는 오버라이드 할 수 없다.

> 실행 시점에 객체 타입에 따라 동적을 호출될 대상 메소드를 결정하는 방식을 동적 디스패치(dynamic dispatch)라 한다.
>
> 정적은 컴파일 시점, 동적은 실행 시점

하지만 확장 함수는 클래스의 일부가 아님!

- 수신 객체로 지정한 변수의 정적 타입에 의해 어떤 확장 함수가 호출될지 결정되지, 그 변수에 저장된 객체의 동적인 타입에 의해 확장 함수가 결정되지 않는다.

```kotlin
fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a view!")

val view: View = Button()
view.showOff()

//I'm a view
```



##### 3.3.5 확장 프로퍼티

```kotlin
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main(args: Array<String>) {
    val sb = StringBuilder("Kotlin?")
    println(sb.lastChar)
}
```



#### 3.4 컬렉션 처리 : 가변 길이 인자, 중위 함수 호출, 라이브러리 지원

- varage

  : 호출 시 인자 개수가 달라질 수 있는 함수를 정의할 수 있다.

- infix

  : 인자가 하나뿐인 메소드를 간편하게 호출

- destruction declaration

  : 복합적인 값을 분해해서 여러 변수에 나눠 담을 수 있다.



##### 3.4.2 가변 인자 함수: 인자의 개수가 달라질 수 있는 함수 정의

> 자바에서는 배열 구문이 들어오면 그냥 넘기지만 kotlin에서는 spread연산을 함

```kotlin
val list = listOf("args: ", *args)
println(list)
```

##### 3.4.3 값의 쌍 다루기: 중위 호출과 구조 분해 선언

```kotlin
//맵을 만들었을때
val map = mapOf(1 to "one", 2 to "two")

infix fun Any.to(other: Any) = Pair(this, other)
val (number, name) = 1 to "one" //이런 기능을 구조 분해 선언이라함

for((index, element) in Collection.withIndex()) {
    println("${index}: ${element}")
} //구조분해함수 였음
```



#### 3.5 문자열과 정규식 다루기

##### 3.5.1 문자열 나누기

```kotlin
println("12.345-6.A".split("\\.|-".toRegex()))
println("12.345-6.A".split(".", "-"))
```

##### 3.5.2 정규식과 3중 따옴표로 묶은 문자열

경로 파싱

```kotlin
fun parsePath(path: String) {
	val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

	val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    
    println("Dir: $directory, name: $fileName, ext: $extension")
}
```

> kotlin에서 3중 따움표 문자열을 사용하면 어떤 문자도 이스케이프할 필요가 없다.

```kotlin
fun parsePathRegx(path: String) {
	val regex = """(.+)/(.+)\.(.+)""".toRegex()
 
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured //구조분해    
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}
// 따로 지정하지 않으면 정규식 엔진은 가장 긴 부분 문자열과 매치하려고 시도 - lazy greedy
```

> 3중 문자열을 사용하면 escape 없이 그대로 들어간다.
>
> 하지만 3중 문자열에 $를 넣어야한다면 '$' 로 사용해야한다. 소스를 더 보기 좋게하려면 trimMargin("")



#### 3.6 코드 다듬기: 로컬 함수와 확장

> 많은 개발자들이 좋은 코드의 중요한 특징 중 하나가 중복이 없는 것이라 믿는다.
> DRY (Don't Repeat Yourself) 하지만 메소드 추출로 너무 많이 나누면 메소드가 많아지고 관계가 파악하기 힘들어진다.

- 코틀린은 함수에서 추출한 함수를 원 함수에 내부에 중첩시킬 수 있다.!!!

```kotlin
class User(val id: Int, val name: String, val address: String)

//1. 코드 중복이 있는 예제
fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }
    // user를 데이터베이스에 저장한다.
}

//2. 로컬 함수를 사용 코드 중복 줄이기
fun saveUser(user: User) {
    fun validate(
        user: User,
        value: String,
        fieldName: String
    ) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty ${fieldName}"
            )
        }
    }

    validate(user, user.name, "Name") 
    validate(user, user.address, "Address")
    // user를 데이터베이스에 저장한다.
}

//3. user.name이 아니고 바깥 함수에 파라미터를 변수로 사용 가능
fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty ${fieldName}"
            )
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
}

//4. 확장 함수로 추출
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${id}: empty ${fieldName}")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
    // user를 데이터베이스에 저장한다.
}

fun saveUser(user: User) {
    user.validateBeforeSave()
    // user를 데이터베이스에 저장한다.
}


fun main() {
    saveUser(User(1, "", ""))
}
```

>! 중첩된 함수의 깊이가 깊어지면 코드를 읽기가 어려워지므로 한 단계만 중첨시키는 것을 권장



### 4. 클래스, 객체, 인터페이스

코틀린 클래스와 인터페이스는 기본적으로 final이며 public

> 싱글턴 클래스, 동반 객체(companion object), 객체 식(object expression)을 표현할 때 object 키워드를 쓴다.

#### 4.1 클래스 계층 정의

sealed는 클래스 상속을 제한

##### 4.1.1 코틀린 인터페이스

```kotlin
interface Clickable {
	fun click()
    fun showOff() = println("I'm clickable!") //default 구현
}

class Button : Clickable { //kotlin에서는 이것만으로 상속이 됨
    //override 변경자 필수!
    override fun click() = println("I was clicked")
}
```

```kotlin
package com.example.kotlin

class Button : Clickable, Focusable{
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()//부모상속
        super<Focusable>.showOff()
    }
}
```


##### 4.1.2 open, final, abstract 변경자 : 기본적으로 final

상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지하라!

코틀린의 클래스와 메소드는 기본적으로 final 상속을 허용하려면 open 변경자를 붙여야 한다.

- 오버라이드 금지 final을 붙임

```kotlin
final override fun click() {}
```

interface는 자동 abstract

>final : 오버라이드할 수 없음 : 클래스 멤버의 기본 변경자
>
>open : 오버라이드할 수 있음 : 반드시 open을 명시해야 오버라이드할 수 있음
>
>abstract : 반드시 오버라이드 : 추상 클래스의 멤버에만 이 변경자를 붙일 수 있다
>
>override : 상위 클래스나 상위 인스턴스의 멤버를 오버라이드 하는 중 : 오버라이드하는 멤버는 기본적으로 열려있음 하위 클래스의 오버라이드를 금지하려면 final을 명시

>변경자 : 클래스 멤버 : 최상위 선언
>
>public 기본 가시성 : 모든 곳 : 모든 곳에서 볼 수 있음
>
>internal : 같은 모듈 : 같은 모듈
>
>protected : 하위 클래스 안에서만 : (최상위 선언에 적용할 수 없음)
>
>private : 같은 클래스 안에서만 볼 수 있음 : 같은 파일 안에서만 볼 수 있음

##### 4.1.4 내부 클래스와 중첨된 클래스

nested class는 명시적으로 요청하지 않는 한 바깥쪽 클래스 인스턴스에 대한 접근 권한이 없음

자바에서는 static을 붙여야 바깥쪽 클래스 참조가 사라지지만 
kotlin은 반대다 inner을 붙여야 바깥쪽 클래스를 참조한다.

```kotlin
class Outer {
	inner class Inner {
		fun getOuterReference(): Outer = this@Outer
	}
}
```

##### 4.1.5 봉인된 클래스

코틀린 컴파일러는 when을 사용해 Expr 타입의 검사할 때 꼭 디폴트 분기인 else분기를 추가
하지만 이렇게 되면 디폴트 분기가 선택되기 때문에 심각한 버그가 발생할 수 있음

! sealed class를 이용한다면 default 분기가 필요 없음 -> sealed class는 자동으로 open임



#### 4.2 뻔하지 않은 생성자와 프로퍼티를 갖는 클래스 선언

주생성자 부생성자가 있으며 초기화 블록이 있다.

##### 4.2.1 클래스 초기화: 주 생성자와 초기화

 ```kotlin
class User(_nickname: String) {
    val nickname = _nickname
}

class User(val nickName: String, val isSubscribed: Boolean = true)
val chan = User("찬훈")
println(chan.isSubscribed) // true
 ```

```kotlin
open class Button

class RadioButton : Button() 
//클래스의 생성자를 호출해야함
//interface는 없어도 됨
```

##### 4.2.2 부 생성자

부 생성자는 constructor 키워드로 시작

```kotlin
class MyButton : View {
	constructor(ctx: Context) : this(ctx, MY_STYLE) {
		//다른 생성자에게 생성을 위임
	}
}
```

##### 4.2.3 인터페이스에 선언된 프로퍼티 구현

```kotlin
interface User {
	val nickname: String
}

class PrivateUser(override val nickname: String) : User
class SubscribingUser(val email: String) : User {
    override val nickname: String
    	get() = email.substringBefore('@')
}
class FackbookUser(val accountId: Int) {
    override val nickname = getFacebookName(accountId)
}
```

##### 4.2.4 게터와 세터에서 뒷받침하는 필드에 접근

getter에서는 field값을 읽을 수만 있고 세터에서는 field 값을 읽거나 쓸 수 있음

```kotlin
class User(val name:String ){
	var address: String = "unspecified"
		set(value: String) {
			println("""
				Address was changed for $name:
				"$field" -> "$value".""".trimIndent())
            field = value
		}
}
```

##### 4.2.5 접근자의 가시성 변경

counter을 내부에서만 바뀌도록 한 것이다.

```kotlin
class LengthCounter {
	var counter: Int = 0
		private set
    fun addWord(word: String) {
    	coutner += word.length
    }
}
```

#### 4.3 컴파일러가 생성한 메소드 : 데이터 클래스와 클래스 위임

toString(), equals(), hashCode()

```kotlin
class Client(val name: String, val postalCode: Int) {
	override fun toString() = "Cliend(name=$name, postalCode=$postalCode)"
    
    override fun equals(other: Any?) : Boolean {
        if (other == null || other !is Client)
    		return false
    	return name == other.name &&
    		postalCode == other.postalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}
```

- 코틀린에서는 ==가 내부적으로 equals를 호출해서 객체를 비교 참조 비교는 ===

-> 복잡하다 코틀린 컴파일러는 이 모든 메소드를 자동으로 생성해준다.



##### 4.3.2 데이터 클래스 : 모든 클래스가 정의해야 하는 메소드 자동 생성

data class로 만들면 전부 해결

copy()메소드도 제공해준다.

```kotlin
data class Client(val name: String, val postalCode: Int)
```

##### 4.3.3 클래스 위임: by 키워드 사용

데코레이터 패턴: 상속을 허용하지 않는 클래스에 새로운 동작을 추가할때 사용

collection만 구현하려해도 많은 코드가 필요 이런 위임을 **일급 시민 기능**으로 지원

> 일급 객체? 코틀린 함수는 1급 시만
>
> - 파라미터로 전달할 수 있다.
>
> - 반환값으로 사용할 수 있다.
> - 변수나 데이터 구조 안에 담을 수 있따.
> - 할당에 사용된 이름과 관계없이 고유한 구별이 가능하다.

```kotlin
class DelegatingCollection<T>(
	innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList()
```

```kotlin
class CountingSet<T>(
	val innerSet : MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innserSer(
	var objectsAdded = 0
	override fun add(element: T): Boolean {
		objectsAdded++
		return innerSet.add(element)
	}
	override fun addAll(c: Collection<T>): Boolean {
		ovjectsAdded += c.size
		return innerSet.addAll(c)
	}
)
```



#### 4.4 object 키워드 : 클래스 선언과 인스턴스 생성

- object declaration은 싱글턴을 정의하는 방법 중 하나

- 동반 객체는 인스턴스 메소드는 아니지만 어떤 클래스와 관련 있는 메소드와 팩토리 메소드를 담을 때 쓰임
- 객체 식은 자바의 무명 내부 클래스 대신 쓰인다.

##### 4.4.1 객체 선언: 싱글턴을 쉽게 만들기

> 정적인 필드에 그 클래스의 유일한 객체를 저장하는 싱글턴 패턴

일반 클래스 인스턴스와 달리 **싱글턴 객체는 객체 선언문이 있는 위치에서 생성자 호출 없이 즉시 만들어지므로** 생성자 정의가 필요 없다.

- 중첩 객체를 사용한 Comparator

```kotlin
data class Person(val name: String) {
	object NameComparator : Comparator<Person> {
		override fun compare(p1: Person, p2: Person): Int = 
			p1.name.compareTo(p2.name)
	}
}
```



##### 4.4.2 동반 객체 : 팩토리 메소드와 정적 멤버가 들어갈 장소

**클래스의 인스턴스와 관계없이 호출해야 하지만 클래스 내부 정보에 접근해야 하는 함수가 필요할 때는 클래스에 중첩된 객체 선언의 멤버 함수로 정의해야 함**

```kotlin
//JSON 직렬화
class Person(val name: String) {
	companion object Loader {
		fun fromJSON(jsonText: String): Person = ...
	}
}
//동반 객체에서 인터페이스 구현
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {
    companion object : JSONFactory<Person> {
        override fun fromJSON(jsonText: String) : Person...
    }
}
```

```kotlin
class Person(val firstName: String, val lastName: String) {
	companion object {
	
	}
}
//확장 함수 선언
fun Person.Companion.fromJSON(json: String): Person {

}
```

##### 4.4.5 객체 식: 무명 내부 클래스를 다른 방식으로 작성



### 5. 람다로 프로그래밍

> lamda expression 또는 람다는 기본적으로 다른 함수에 넘길 수 있는 작은 코드 조각을 뜻함

##### 5.1.1 람다 소개: 코드 블록을 함수 인자로 넘기기

함수형 프로그래밍에서는 함수를 값처럼 다루는 접근 방법을 사용
```kotlin
button.setOnClickListener { }
```

##### 5.1.2 람다와 컬렉션

```kotlin
people.maxBy(Person::age)
```

##### 5.1.3 람다 식의 문법

- 코틀린에는 함수 호출 시 맨 뒤에 있는 인자가 람다 식이라면 그 람다를 괄호 밖으로 빼낼 수 있다.
- 람다가 어떤 함수의 유일한 인자이고 괄호 뒤에 람다를 썼다면 호출 시 빈 괄호를 없애도 된다.

```kotlin
people.maxBy() { p: Person -> p.age }
people.maxBy { p: Person -> p.age }
```
- 람다의 파라미터가 하나뿐이고 그 타입을 컴파일러가 추론할 수 있는 경우 it을 바로 쓸 수 있다.

##### 5.1.4 현재 영역에 있는 변수에 접근

람다 안에서는 파이널 변수가 아닌 변수에 접근할 수 있다. 또한 람다 안에서 바깥의 변수(포획한:capture 변수)를 변경해도 된다.
-> 래퍼에 대한 참조를 람다 코드와 저장함

##### 5.1.5 멤버 참조
::를 사용하는 식을 멤버 참조라고 부른다
최상위에 선언된 함수나 프로퍼티를 참조할 수 있음

#### 5.2 컬렉션 함수형 API
함수형 프로그래밍 스타일을 사용하면 컬렉션을 다룰 때 편리

##### 5.2.1 필수적인 함수: filter와 map

filter 함수는 컬렉션에서 원치 않은 변수를 제거하지만 변환 할 수 없다.
```kotlin
val list = listOf(1, 2, 3, 4)
println(list.filter { it % 2 == 0 }
```

map 함수는 주어진 람다를 컬렉션의 각 원소에 적용한 결과를 모아 새 컬렉션을 만든다.
```kotlin
val list = listOf(1, 2, 3, 4)
println(list.map { it * it })
```

가장 나이 많은 사람 출력하기
```kotlin
//계속 최댓값 연산을 함
people.filter { it.age == people.maxBy(Person::age)!!.age }

//필요없는 계산을 반복하지 말자
val maxAge = people.maxBy(Person::age)!!.age
people.filter { it.age == maxAge }
```
- map의 경우는 mapKeys, filterKeys를 이용해 키를 다루고 mapValues 와 filterValues를 이용해 value를 다룬다.

##### 5.2.2 all, any, count, find: 컬렉션에 술어 적용

all 모두 만족하는지 any 만족하는 것이 있는지
count는 만족하는 개수 find(firstOrNull) 함수는 첫 번째 원소 (없으면 null)
```kotlin
val canBeInClub27 = { p: Person -> p.age <=27 }

val people = listOf(Person("Alice", 27), Person("Bob", 31))
println(people.all(canBeInClub27)) //false
println(people.any(canBeInClub27)) //true
println(people.count(canBeInClub27)) //1
```
-> filter을 사용할 수 있지만 중간 컬렉션이 생김

##### 5.2.3 groupBy:리스트를 여러 그룹으로 이뤄진 맵으로 변경
```kotlin
people.groupBy { it.age }
```

##### 5.2.3 flatMap과 flatten: 중첩된 컬렉션 안의 원소 처리
```kotlin
val strings = listOf("abc", "def")
println(strings.faltMap { it.toList() })
[a, b, c, d, e, f]
```
> 특별히 변환할 내용이 없으면 flatten() 사용

#### 5.3 지연 계산(lazy) 컬렉션 연산

```kotlin
people.map(Person::name).filter { it.startsWith("A") } //계산 중간 결과를 계속 컬렉션에 담게 된다.

people.asSequence() //원본 컬렉션을 시퀀스로 만든다.
	.map(Person::name) 
	.filter { it.startsWith("A") }
	.toList() //iterator로 읽는 경우는 좋지만 인덱스로 접근하는 것이 빠른 경우는 다시 리스트로
```

##### 5.3.1 시퀀스 연산 실행 : 중간 연산과 최종 연산
위에서 toList()로 결과가 필요할 떄 연산이 진행된다. lazy 연산

##### 5.3.2 시퀀스 만들기
```kotlin
val naturealNumbers = generateSequence(0) { it + 1 }
vla numbesTo100 = naturalNumbers.takeWhile { it <= 100 }
println(numberTo100.sum())
```

#### 5.4 자바 함수형 인터페이스 활용

```kotlin
button.setOnClickListener { view -> ... }
```
- OnclickListener에 추상 메소드가 단 하나만 있기 때문에 가능
- 함수형인터페이스 or SAM(Single abstract method) 인터페이스라고 한다.


##### 5.4.1 자바 메소드에 람다를 인자로 전달
Runnable 인스턴스 
대부분 자동으로 컴파일러에서 변환

##### 5.4.2 SAM 생성자 : 람다를 함수형 인터페이스로 명시적으로 변경
오버로드한 메소드 중에서 어떤 타입의 메소드를 선택해 람다를 변환해 넘겨줘야 할지 모호할 때 명시적으로 SAM 생성자를 적용


#### 5.5 수신 객체 지정 람다: with와 apply

##### 5.5.1 with함수

```kotlin
fun alphabet(): String {
	val stringBuilder = StringBuilder()
	return with(stringBuilder) {
		for(letter in 'A'..'Z') {
			this.append(letter)
		}
		append("\nNow I know the alphabet!")
		this.toString()
	}
}

fun alphabet(): with(StringBuilder()) {
	for(letter in 'A'..'Z') {
		append(letter)
	}
	append("\nNow I know the alphabet!")
	toString()
}
// OuterClass의 toString()을 쓰고 싶다면 this@OuterClass.toString()
```
결과값은 마지막 줄이지만 수신 객체가 필요한 경우 apply함수를 사용

##### 5.5.2 apply 함수
apply함수는 항상 자신에게 전달된 객체를 반환한다
```kotlin

fun alphabet(): StringBuilder().apply {
	for(letter in 'A'..'Z') {
		append(letter)
	}
	append("\nNow I know the alphabet!")
}.toString()
```
- buildString은 StringBuilder객체를 만드는 일과 toString을 호출해주는 일을 알아서 해준다. 
