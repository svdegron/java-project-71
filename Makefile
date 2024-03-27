run-dist:
	"./build/install/app/bin/app"

run-h:
	"./build/install/app/bin/app" -h

run-help:
	"./build/install/app/bin/app" --help

run-V:
	"./build/install/app/bin/app" -V

run-version:
	"./build/install/app/bin/app" --version

run-json:
	"./build/install/app/bin/app" "./src/test/resources/file5.json" "./src/test/resources/file6.json"

run-yaml:
	"./build/install/app/bin/app" "./src/test/resources/file7.yml" "./src/test/resources/file8.yml"

run-json-plain:
	"./build/install/app/bin/app" -f plain "./src/test/resources/file5.json" "./src/test/resources/file6.json"

run-yaml-plain:
	"./build/install/app/bin/app" -f plain "./src/test/resources/file7.yml" "./src/test/resources/file8.yml"

run-json-json:
	"./build/install/app/bin/app" -f json "./src/test/resources/file5.json" "./src/test/resources/file6.json"

run-yaml-json:
	"./build/install/app/bin/app" -f json "./src/test/resources/file7.yml" "./src/test/resources/file8.yml"

run-error1:
	"./build/install/app/bin/app" "file1.json" "file2.json"

run-empty-files:
	"./build/install/app/bin/app" "c:/idea-workspace/app2/file9.json" "c:/idea-workspace/app2/file10.json"

run-empty-files-plain:
	"./build/install/app/bin/app" -f plain "c:/idea-workspace/app2/file9.json" "c:/idea-workspace/app2/file10.json"

run-empty-files-json:
	"./build/install/app/bin/app" -f json "c:/idea-workspace/app2/file9.json" "c:/idea-workspace/app2/file10.json"

build:
	cd app/
	ls -la
	./gradlew :build

report:
	"./gradlew" jacocoTestReport

.PHONY: build