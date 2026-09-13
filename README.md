# Java Automation Framework
![Framework Image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgyR-wbRlT-kFKR9ydvuOZ7QOug6X8EDQSDA&usqp=CAU)

[![Java CI with Maven](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/maven.yml/badge.svg)](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/maven.yml)

[![report deployment](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/pages/pages-build-deployment)

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/MB1lal/JavaAutomationFramework/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/MB1lal/JavaAutomationFramework/tree/master)

## What this is

UI + API tests for a couple of public demo sites, written with Serenity BDD,
Cucumber on the JUnit Platform, and RestAssured. The reports Serenity
generates are published to GitHub Pages after every CI run.

Latest report: https://mb1lal.github.io/JavaAutomationFramework/

## What you need

- Java 21+ (enforced by the build)
- Maven 3.9+ (enforced by the build)
- Chrome — tests run headless by default, so no display needed

## Running the tests

The suite runs through Maven Failsafe (`integration-test`), not Surefire.
`mvn verify` also aggregates the Serenity report at the end:

```bash
# everything
mvn verify

# just the API or UI runners
mvn verify -Dtest=BackendTestRunner
mvn verify -Dtest=FrontendTestRunner

# re-run failures twice before giving up (this is what CI does)
mvn verify -Dfailsafe.rerunFailingTestsCount=2
```

Scenarios run in parallel (4 threads, configured in
`src/test/resources/junit-platform.properties`), each with a fresh browser
and its own generated test data, so they can't trip over each other. The
build also checks code formatting (`spotless:check`) — run
`mvn spotless:apply` if it complains.

Test config (base URLs, browser switches) lives in
`src/test/resources/serenity.properties` and `serenity.conf`.

## How it's laid out

```
src/test/java/
  runner/        JUnit Platform @Suite runners (all / frontend / backend)
  steps/         glue code — backend/ for API, frontend/ for UI, base/ for shared bits
  pages/         Serenity page objects for the-internet.herokuapp.com
  connectors/    thin RestAssured wrappers around the Petstore API
  models/        request/response POJOs
  utils/         Excel read/write, random data, JSON helpers
  core/          TestConfig (lazy config) + ScenarioContext (typed per-scenario state)
src/test/resources/
  features/      backend/ (pets, store, users) and frontend/ (herokuapp pages)
  data-files/    static payloads, upload file, Excel test data
  junit-platform.properties   Cucumber engine parallelism settings
```

## What's covered

- API — Petstore pets, store orders and users (create / read / update / delete flows)
- UI — the-internet.herokuapp.com: login, checkboxes, dropdown, dynamic
  loading, file upload, frames, JS alerts, hovers, multiple windows,
  notifications

## Notes

- Test data is generated fresh per scenario (unique pet/order IDs, random
  users via DataFaker), so parallel runs don't depend on leftover state in
  the demo APIs.
- The IMDB/Google scenarios are tagged `@ignore` — they scrape live Google
  results, which isn't reliable enough to keep in the suite. The TestNG
  sister project (`JavaAutomationFramework-TestNG`) drops that scraping on
  purpose. File download, on the other hand, was reimplemented over plain
  HTTP and runs as part of the suite.
