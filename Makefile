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

run-files:
	"./build/install/app/bin/app" "./build/install/app/bin/file1.json" "./build/install/app/bin/file2.json"

run-files-windows:
	"./build/install/app/bin/app" "c:\idea-workspace\app2\file1.json" "c:\idea-workspace\app2\file2.json"

run-files-error1:
	"./build/install/app/bin/app" "file1.json" "file2.json"

run-files-error2:
	"./build/install/app/bin/app" "c:\idea-workspace\app2\file1.json" "file2.json"

build:
	"./gradlew" :build

.PHONY: build