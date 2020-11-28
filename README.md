# Dagger for Android app
* Think of 'component' as service provider and our app as client.
* With dagger android there's no need to inject your activity into components manually.
-- (in `MainActivity`, you don't need to write `component.inject(this)` anymore)

## Setup
build.gradle:
```x
    def dagger_version = '2.xx'
    implementation "com.google.dagger:dagger:$dagger_version"
    implementation "com.google.dagger:dagger-android:$dagger_version"
    implementation "com.google.dagger:dagger-android-support:$dagger_version"
    kapt "com.google.dagger:dagger-compiler:$dagger_version"
    kapt "com.google.dagger:dagger-android-processor:$dagger_version"
```

## AndroidInjector<T>
Now `AppComponent` class implements `AndroidInjector<T>` and T is your base application class - 
Base application of type `T` is injected into `AppComponent`.

(My) AppComponent.kt:
```kotlin
@Component(modules=[])
interface AppComponent: AndroidInjector<BaseApplication>{
}
```

## Dagger with MVVM - needs workaround..
* ViewModel is a good place to inject dependencies into but it turns out it won't get done so easily..

## Solved Issues
* Error ```Map<K, V> cannot be provided without an @Provides-annotated method``` occurred still after
adding `@JvmSuppressWildcards` in front of `Providers<>` parameter
- solved by changing return types of methods annotated `@Provides`/`@Binds` to top parent class from
exact parameter type.

```kotlin
    @Module
    abstract class AuthViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(AuthViewModel::class)
        abstract fun bindAuthViewModel(vm: AuthViewModel): ViewModel    //return type should NOT be AuthViewModel
    }
```