# Personal Details App Projects

<!-- TOC -->

* [Personal Details App Projects](#personal-details-app-projects)
    * [Introduction](#introduction)
        * [Basis of project](#basis-of-project)
    * [Technologies Used](#technologies-used)
        * [Wait, dependabot?](#wait-dependabot)
    * [Considerations for the future](#considerations-for-the-future)

<!-- TOC -->

## Introduction

This private project consists of projects which are designed to parse
a file for personal details and then send them to
an endpoint to then store them in a database.

This private project consists of the following project(s):

- [Personal Details Console App](console-app/README.md)
- [Personal Details Rest API](rest-api/README.md)

### Basis of project

This project came from the following request:
[Rest Project Task](src/main/resources/Mini%20REST%20Project.docx)

## Technologies Used

- Java 21
- Maven
- Spring Boot 3
- H2 Database (in place of SQL Database)
- Dependabot

### Wait, dependabot?

Dependabot is a free tool on Github that will automatically scan ecosystems like Maven and Github Actions, then will
raise individual PRs if any dependencies are out of date.

This ensures dependencies are secure and up to date without the manual exercise of investigating.

## Things to note

- Both solutions are Spring Boot Applications
    - Ideally these should either be part of the same solution, OR be packaged separately to each other
    - They are packaged together for the convenience of a github repository

## Considerations for the future

- Implement HTTPS across the project
    - Needs to be implemented in the REST API for security
    - Need to add to the calling
    - Has WIP branch for the work
    - Ensure JKS or otherwise is not included in project, for extra security
- Encrypt All Password
    - OR Have them all be secrets and only referenced
- Complete full system testing
    - WIP branch for the work, but get different errors on Bash and IntelliJ
- Introduce OAUTH/API Keys/Alternative
    - Greater security of the solution
- Add validation bean to make postcode mandatory if country is United Kingdom and/or related country