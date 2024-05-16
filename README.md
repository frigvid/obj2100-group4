# OBJ2100 Exam - 2024Q2

This is the project developed for the 2024Q2 exam in the class Object-Oriented Programming 2100 at the University of South-Eastern Norway.

> :warning: **This is a mixed-language repository.** You may come across usage of Norwegian in places, but primarily the project _should_ be in English.

# Structure

This is a multi-module Maven project. It's split into three primary modules:
- The Client: A JavaFX application that communicates with the server.
- The Server: A socket application that serves as the backend for the client.
- The Shared module: A module that contains shared code, that is included with either the client or the server, when it is built.

As you may note, there is also a `docs` directory. This is to store exam related files, like the rapport, images, files, etc.

Reference implementations of client communication to the server can be found in the server module's tests directory, under `clientTests`. These simulate a client connection. If a `clientTests` test is able to do it, the client can too, regardless of whether the UI is there or not.

# How to build

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Execute `Reload All Maven Projects` (step 2).
4. Under `exam` → `Lifecycle`, run `clean` (step 3) and then `install` (step 4).
   - If you want documentations as well, then `Download Sources and Documentation` (step 5).

![IntelliJMavenSidebarSteps.png](docs%2FIntelliJMavenSidebarSteps.png)

Fair warning, we have had issues running the project from Apple MacOS. Multiple Microsoft Windows machines have worked fine with the steps outlined above, but we simply do not have the time to test enough to find out what the cause is.
