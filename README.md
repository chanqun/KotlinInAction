# Kotlin In Action

## 목차

[1. 코틀린이란 무엇이며, 왜 필요한가?](#1-코틀린이란-무엇이며-왜-필요한가?)
[2. 코틀린 기초](#2-코틀린-기초)
[3. 함수 정의와 호출](#3-함수-정의와-호출)
[4. 클래스, 객체, 인터페이스](#4-클래스-객체-인터페이스)

### 

#### 

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

lamda expression을 이라 불리는 anonymous function 구문을 이요하면 그런 함수를 간단히 표현가능

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