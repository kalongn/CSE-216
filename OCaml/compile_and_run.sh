#!/bin/bash

ocamlopt -c "$1.ml"
ocamlopt -o program "$1.cmx"

./program

rm *.cmi
rm *.cmx
rm *.o