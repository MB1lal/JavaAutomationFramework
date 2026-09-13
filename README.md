# Java Automation Framework
![Framework Image](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgyR-wbRlT-kFKR9ydvuOZ7QOug6X8EDQSDA&usqp=CAU)

[![Java CI with Maven](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/maven.yml/badge.svg)](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/maven.yml)

[![report deployment](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/MB1lal/JavaAutomationFramework/actions/workflows/pages/pages-build-deployment)

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/MB1lal/JavaAutomationFramework/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/MB1lal/JavaAutomationFramework/tree/master)

## What this is

UI + API tests for a couple of public demo sites, written with Serenity BDD,
Cucumber (JUnit4 runner) and RestAssured. The reports Serenity generates are
published to GitHub Pages after every CI run.

Latest report: https://mb1lal.github.io/JavaAutomationFramework/

## What you need

- Java 21 (the project targets release 21)
- Maven 3.9+
- Chrome — tests run headless by default, so no display needed

## Running the tests

The suite runs through Maven Failsafe (`integration-test`), not Surefire,
because the runners are `*TestRunner` / `SlicedTestRunner*` classes:

```bash
# everything, single fork setup
mvn verify

# parallel sliced run (this is what CI does)
mvn -P useTheForks integration-test
```

A few knobs worth knowing:

```bash
# how many parallel forks (default 4)
mvn -P useTheForks integration-test -Dparallel.tests=2

# re-run failures once before giving up
mvn -P useTheForks integration-test -Dfailsafe.rerunFailingTestsCount=2
```

Test config (base URLs, browser switches) lives in
`src/test/resources/serenity.properties` and `serenity.conf`.

## How it's laid out

```
src/test/java/
  runner/        CucumberWithSerenity runners (all / frontend / backend / sliced)
  steps/         glue code — backend/ for API, frontend/ for UI, base/ for shared bits
  pages/         Serenity page objects for the-internet.herokuapp.com
  connectors/    thin RestAssured wrappers around the Petstore API
  models/        request/response POJOs
  utils/         Excel read/write, random data, JSON helpers
  core/          config + environment access
src/test/resources/
  features/      backend/ (pets, store, users) and frontend/ (herokuapp pages)
  data-files/    static payloads, upload file, Excel test data
```

## What's covered

- API — Petstore pets, store orders and users (create / read / update / delete flows)
- UI — the-internet.herokuapp.com: login, checkboxes, dropdown, dynamic
  loading, file upload, frames, JS alerts, hovers, multiple windows,
  notifications

## Notes

- Test data is generated per run where it matters (DataFaker + EasyRandom),
  so tests don't depend on leftover state in the demo APIs.
- The file-download and IMDB/Google scenarios are tagged `@ignore` — the
  former shells out to `wget` and the latter scrapes live Google results,
  neither is reliable enough to keep in the suite. The TestNG sister project
  (`JavaAutomationFramework-TestNG`) reimplements the download check properly
  over plain HTTP and drops the Google scraping on purpose.
