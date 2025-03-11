Refactor effort

1) Clean up the Writer file to include a proper example of each thing I am trying to demonstrate DONE
2) Clean up the arguments for each of the types DONE
3) Experiment with delegation to see if we can reuse methods CANCEL
4) Find a better solution for dealing with replace children and addChildren DONE
5) Look into differentiating between using a typed string as a type and a typed string as a value, maybe use v for the value DONE
6) actually implement the difference between add and define
7) Make vals addable even if they are not assigned DONE
8) Break up the Kotlin File into definable things DONE
9) Factor Bundle out by replacing things with T CANCEL (Don't think I can remove bundle)
10) Improve KType to string/type CANCEL (Don't think I can reliably rely on reflection)
11) Create a system for automatic imports using declared types POSTPONE
12) Create a base lib that includes a lot of common imports POSTPONE

----------------------------------------------------------------------------------------------------

1) Break up the Kotlin File into definable things DONE
2) Actually implement the difference between add and define DONE

----------------------------------------------------------------------------------------------------
Complete WriterCore for all currently defined methods 
1) MONDAY Get intent working DONE
2) TUESDAY Create Typed strategy for typed block and values a sealed class DONE
3) WEDNESDAY Complete If IfElse Else, Create a better id naming system DONE
        ID a) create a random id function, b) add to all classes as default, c) share id between code DONE
4) THURSDAY Complete For DONE
5) FRIDAY Complete While DONE
6) SATURDAY Complete DoWhile DONE
7) SUNDAY Complete Class block + definition
        Definition 
                Visibility
                Sealed/Data
        Block Methods POSTPONE TO after ALPHA-1

----------------------------------------------------------------------------------------------------
1) Create when branch DONE
2) Write when definition DONE
3) Create TypedValue sealed class
4) Create TypedValue state ext
----------------------------------------------------------------------------------------------------
Work on WriterCore
1) MONDAY 17 Write Instance definition
2) TUESDAY 18 Write Object definition
3) WEDNESDAY 19 Write Fun definition
4) THURSDAY 20 Write Enum definition
5) FRIDAY 21 Write Lambda definition
6) SATURDAY 22 Write List definition
7) SUNDAY 23 Write Annotation definition

----------------------------------------------------------------------------------------------------

1) Attempt to make code for Screen, SubPubs and Engine using KSP

----------------------------------------------------------------------------------------------------
ALPHA
1) Experiment with Delegation
        Implement all non inline methods using delegation
        Upgrade bundle to remove the need for inlining
        Use null type on If to eliminate the need for inlining

2) Complete When Definition
3) Refactor the Writer
4) Include project builds for the project

BETA
More types
1) Handle Overrides, Suspend
2) Stricter Class and Fun definitions
3) Typed lists for args, typeArgs and Calls

V1
Create a system for automatic imports using declared types POSTPONE for V1
1) Use qualified name of types for imports (works for simple types without type args) => Tick
2) Use KType for imports (Doesn't work at all) will need a second arg which will contain a list of imports to include
3) Use qualified name of types for imports (does not work for type args)
4) Use KType for imports (Doesn't work for type args)
5) Use references from generated types to use on automatic imports (Doesn't work)
6) Use references to validate whether other code should be shown should use Bundle to blend references and t<T> in args

