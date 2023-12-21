JAR = dist/part3.jar
SRC_DIR = src/
BUILD_DIR = more/
TESTS_DIR = tests/
JAVA_FILES = $(wildcard $(SRC_DIR)*.java)
TEST_FILES = $(wildcard $(TESTS_DIR)*.pmp)

.PHONY: default jflex build testing all

default: build

jflex:
	jflex $(SRC_DIR)LexicalAnalyzer.flex

build: jflex
	javac -d $(BUILD_DIR) -cp $(SRC_DIR) $(JAVA_FILES)
	jar cfe $(JAR) Main -C $(BUILD_DIR) .

testing: $(TEST_FILES)
	$(foreach file,$(TEST_FILES),java -jar $(JAR) $(file);)

all: build testing
