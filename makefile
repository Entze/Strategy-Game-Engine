SGE_MANUAL_IMAGE_FILES := $(wildcard manual/*.png)

manual/SGE-MANUAL.pdf: manual/SGE-MANUAL.typ manual/template.typ manual/versions.yaml $(SGE_MANUAL_IMAGE_FILES)
	typst compile $< $@

manual/versions.yaml: build.gradle
	./gradlew -q :generateVersionsYaml
