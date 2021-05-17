
Kotiln_CODELAB_코틀린 특징
- Functions - definition in package or in class
- Immutable/mutable variables
- No "New" keyword
- Type inference
- Null Safety
- Property
- No cecked exceptions
- No primitive types
- No static members
- No set/get Method
- No Constructor
- Concise Class
- Implicit Return Value

Immutability
> Built-in support for mutable and immutable variables, properties and fields
> Keywords var and val

Type Inference
> Primitive Type value uses suffix (L,F)
> Assignee follow assigner type

Null Safety
> Kotlin is designed in a way that aims to eliminate NPE form our code
> types added ? suffix:
> String: no nullable     String?: nullable
> Handle manually or use Safe Call operator ?. Or use the !! operator to allow/trigger a NPE

지연 초기화 - lateinit var, lazy
초기 값을 나중에 할당하는 방식
가능하면 Optional(Nullable Type)을 피하는 편이 낫다
> Lateinit var
> lazy init of not primitive, Not checking initialized
> By lazy
> Called when initail access, With code block, ONly immutable

Property
> Variable with getter or setter
> No need to defined in class
> Keyword "field" is internal storage

Code Block & Return
- {} is used to function body, if-else body
	- Function can place "return" anywhere
	- Code block like if-else, when, try-catch, Lambda expr, has implicit block result

형변환(Type Cast), Type Alias
- as : 명시적 형변환
- is : 형의 비교 (instance of)
- when code block after is will be cast type >> smart casting 됨

Array
Array<Int>(6, {0}) //0,0,0,0,0,0

Collection
mutable, immutable

Condition Flow Control
- Code block can return a block result
- In case of different block result, it return Any type
- Can't assign variable in condition : if ((num = num + 1)>0)

Loop Control
- Not conditional loop
- Use range or collective type for iteration
- Can't assign variable in condition
- Can use destructing in loop variable

Loop(1) - for, forEach, repeat
list.indices
1..10 1 ~ 10
1 until 10 1 ~ 9

step 2  downTo
```kotlin
loop1@ for
	loop2@ for
		if(i=='B')
			break@loop1
```

Currying and Named Parameter
```kotlin
fun setTextWith(tv: TextView, text: String,textSize:Float = 10f,color:Int = 0x000, 
bgColor:Int = 0xfff) {
	tv.text = text
	tv.setTextColor(color)
	tv.setBackgroundColor(bgColor)
	tv.textSize = textSize
}
```
java는 다 만들어줬어야 했음

Lambda Expression
- lamda instantiate when it called

Single Abstract Method (SAM)
Interface or Only 1 not Implemented function
java's SAM can switched with lambda expression, but kotlin's not switched

High Order Function
- Function has function type in parameters
- A Lambda expression can be described out of function


Kotlin Object
- class
- data class - value copy도 가능
- object - singleton 모두 static으로 생각하면 됨
- sealed class - class를 enum했다고 생각하자
- enum

kotlin class feature
- java와는 달리 final이 내재 선언
- 상속 가능을 위해서 open 키워드를 붙여 주어야 함, interface는 open이 기본
- java와 달리 constructor가 내재 선언이 되므로 초기화를 위한 init()이 제공

inheritance :찍고 쓰면 됨
```kotlin
oepn class PersonBase(age: Int)
class Contact(age: Int): PersonBase(age)
```

Data class
데이터 간결하게 담기 위한 class
제약사항
- 반드시 primary constructor가 필요
- 1개 이상의 파라미터 존재/ val, var로 정의
- abstrac open sealed or inner 클래스가 아니어야 함
- interface 를 확장하는 것 외에 다른 클래스를 확장 불가

장점
- equals()/hashCode()쌍으로 생성
- toString()생성
- 선언된 프로퍼티에 상응하는 componentN()함수를 생성
- copy() 생성

Sealed Class
- 여러 개의 자식 객체를 묶어 놓은 집합 클래스

```kotlin
sealed class NetworkResult(val token: Stirng) {
	data class Success(val resultCode: Int): NetworkResult("Success")
	data class Failure(val resultCode: Int, val message: String): NetworkResult("Failure")
}
```
Scipe 함수
모든 객체에 공통적으로 정의되어 있으며 인스턴스를 받아서 별도의 작업을 추가하고 결과를 되돌려 주는 함수
let, applymrun, also, with
Let
Sender 값을 받아서 코드 블록에 전달하고, 블록의 결과를 리턴함, Sender는 it으로 할당 - nullable에 대한 처리를 할 때 사용

Apply
Sender를 Receiver가 받아서 프로퍼티나 함수를 it없이 바로 접근하고, Sender를 블록 결과고 리턴함
객체 생성 후 프로퍼티 설정시 사용

takrIf
- 조건이 만족하면 this, 아니면 null 리턴

run 
- 독립적으로 사용될 때에는 코드 블록을 실행하고 블록결과 값을 돌려줌

