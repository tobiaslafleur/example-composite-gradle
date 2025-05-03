# example-composite-gradle

This repo serves as a template for a Gradle monorepo using the composite approach
rather than subprojects.

It also contains a `Dockerfile` in the monolith that uses a layered spring application
for faster startup and better layer caching in Docker.