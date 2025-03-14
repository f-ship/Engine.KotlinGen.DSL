Work on Refactoring effort
MONDAY 10 - SUNDAY 16
1) Create when branch DONE
2) Write when definition DONE
3) Create TypedValue sealed class DONE
4) Create TypedValue state ext DONE
5) Add abstract method for addChild
6) Convert remaining Class implementations into delegates
7) Improve WriterCore implementation to use better string literals
8) Remove RandomString
9) Refactor Save to use execute
10) Refactor toCode to automatically handle executing blocks
11) Complete Class visibility, sealed class, data class
----------------------------------------------------------------------------------------------------
Work on WriterCore
MONDAY 17 - SUNDAY 23
1) MONDAY 17 Write Instance definition
2) TUESDAY 18 Write Object definition
3) WEDNESDAY 19 Write Fun definition
4) THURSDAY 20 Write Enum definition
5) FRIDAY 21 Write Lambda definition
6) SATURDAY 22 Write List definition
7) SUNDAY 23 Write Annotation definition
----------------------------------------------------------------------------------------------------
Work on Engine migration to KSP
MONDAY 24 - SUNDAY 30
1) Attempt to make code for Screen, SubPubs and Engine using KSP
----------------------------------------------------------------------------------------------------
Improve KotlinGen to handle more general cases
ALPHA
1) Include project builds for the project
2) Handle Overrides, Suspend
3) Stricter Class and Fun definitions
4) Typed lists for args, typeArgs and Calls
----------------------------------------------------------------------------------------------------
Create a system for automatic imports using declared types POSTPONE for V1
BETA
1) Use qualified name of types for imports (works for simple types without type args) => Tick
2) Use KType for imports (Doesn't work at all) will need a second arg which will contain a list of imports to include
3) Use qualified name of types for imports (does not work for type args)
4) Use KType for imports (Doesn't work for type args)
5) Use references from generated types to use on automatic imports (Doesn't work)
6) Use references to validate whether other code should be shown should use Bundle to blend references and t<T> in args
----------------------------------------------------------------------------------------------------
