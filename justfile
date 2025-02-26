
default:
    @just --list

build:
    just build-manual
    ./gradlew shadowJar sourcesJar javadocJar

build-manual:
    make manual/SGE-MANUAL.pdf

lint:
    just lint-typst-all

lint-typst *FILES:
    typstyle --check {{ FILES }}

lint-typst-all:
    typstyle --check format-all manual/

fix:
    just fix-typst-all

fix-typst *FILES:
    typstyle --inplace {{ FILES }}

fix-typst-all:
    typstyle --inplace format-all manual/
