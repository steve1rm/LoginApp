# LoginApp

## In a Nutshell
Shows how to login and authenticate with firebase email and password authentication

## Libraries and Tech Stack
- [x] Jetpack Compose
- [x] Koin dependency injection
- [x] Voyager for navigation
- [x] Firebase authentication
- [x] Clean architecture
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
Uses firebase email, password authentication.
email: steve@mail.com
password: Test12345

* Correct credentials will show a toast message saying you are `successfully logged in` <br/>
* Incorrect credentials will show a toast message saying `Unauthorized`

As there is no logout firebase will cache the credentials. So the App's local cache will have to be cleared if you want to test login with correct and incorrect credentials.

## Screenshots
![Screenshot from 2024-07-21 20-54-52](https://github.com/user-attachments/assets/46f97bce-3930-481d-8746-90748a9ff9d6)
