# Kotlin In Action



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

