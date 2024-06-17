run-dist:
	./app/build/install/app/bin/app -h

report:
	./gradlew jacocoTestReport
