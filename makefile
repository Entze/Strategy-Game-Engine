
SGE_MANUAL_IMAGE_FILES := $(wildcard manual/*.png)

manual/SGE-MANUAL.pdf: manual/SGE-MANUAL.typ manual/template.typ $(SGE_MANUAL_IMAGE_FILES)
	typst compile $< $@
