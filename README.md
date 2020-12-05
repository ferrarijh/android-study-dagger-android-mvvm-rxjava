# dagger-android for Android app with RxJava + LiveData + MVVM
* [Google App Architecture Guide for networking](https://developer.android.com/jetpack/guide#addendum)

## sealed class
* Use case is similar with `enum` class - very useful with `when` expression.
(official document [here](https://kotlinlang.org/docs/reference/sealed-classes.html))

States related to network operations should be carefully managed. Using sealed class can be beneficial
for concisely managing such states.

## Injecting Fragments



## Scoping
* Annotate scope on top of Components, Subcomponents (along with methods and classes)
* When using dagger-android and you have multiple activities, annotate scopes on top of
`@ContributesAndroidInjector` module.

### Minor difference in scoping between @Provides and @Binds
* Annotate directly on top of methods with `@Provides` which directly instantiates object in its body.
* For `@Binds`, rather than declaring scopes on top of method returning the class object,
 it's recommended to annotate the class definition.
 (Still, you can annotate scopes on top of functions)

For example, 

when `SingletonComponent` is: 
```kotlin
@Singleton
@Component(modules = [BindsModule::class, ProvidesModule::class])
interface SingletonComponent{
}
```

and the respective modules will look like:

```kotlin
@Module
abstract class BindsModule{
    @Binds  //annotate @Singleton on top of IndirectClass.kt
    fun bindIndirectClass(obj: IndirectClass): IndirectClass
}
```

```kotlin
@Module
abstract class ProvidesModule{
    @Singleton  //annotated on top of function since the instance is directly created in function body
    @Provides
    fun bindIndirectClass() = BindIndirectClass()
}
```

IndirectClass.kt:
```kotlin
@Singleton
class IndirectClass(){
}
```

## Solved Issues

## Unresolved
* Not really necessary to scope ViewModel?