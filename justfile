default:
    @just --list

build:
    just build-typst
    just build-java

build-typst:
    make manual/SGE-MANUAL.pdf

build-java:
    ./gradlew shadowJar sourcesJar javadocJar

lint:
    just lint-all-typst

lint-typst *FILES:
    typstyle --check {{ FILES }}

lint-all-typst:
    typstyle --check format-all manual/

fix:
    just fix-all-just
    just fix-all-typst

fix-typst *FILES:
    typstyle --inplace {{ FILES }}

fix-all-typst:
    typstyle --inplace format-all manual/

fix-just FILE:
    just --unstable --fmt --justfile {{ FILE }}

fix-all-just:
    just --unstable --fmt
