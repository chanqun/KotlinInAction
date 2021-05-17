
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
