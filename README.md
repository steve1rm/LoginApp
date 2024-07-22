# Login App

## In a Nutshell
Shows how to login and authenticate with firebase email and password authentication

## Libraries and Tech Stack
- [x] Jetpack Compose
- [x] Koin dependency injection
- [x] Voyager for navigation
- [x] Firebase authentication
- [x] Clean architecture
- [x] Authentication feature module
- [x] Gradle version catalog
- [x] kotlin coroutines
- [x] Flows
- [x] MVVM
- [x] Material design 3
- [x] Dark and light theme
- [X] kermit for logging
- [X] Unit testing with Junit5
- [X] Mockito

## Instructions
Uses firebase email, password authentication.<br/>
email: steve@mail.com <br/>
password: Test12345<br/>

As there is no logout firebase will cache the credentials. So the App's local cache will have to be cleared if you want to test login with correct and incorrect credentials.

* Correct credentials will show a toast message saying you are `successfully logged in`<br/>
* Incorrect credentials will show a toast message saying `Unauthorized`

## Screenshots
![Screenshot from 2024-07-22 13-22-55](https://github.com/user-attachments/assets/b94e5218-3496-4b53-98fe-e8f61cb8ce30)
