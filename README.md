# AutomationPractice

It's an automation framework to automate both api and UI tests based on serenity-bdd using Java.

How to run:

mvn clean -ntp -P useTheForks integration-test -Dserenity.batch.count-1 -Dserenity.batch.number-1 -Dfailsafe.rerunFailingTestsCount=2
