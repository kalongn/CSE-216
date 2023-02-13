#!/bin/bash

ocamlopt -c $1
fileName=${1%.ml}
ocamlopt -o program "$fileName.cmx"

./program

rm *.cmi
rm *.cmx
rm *.o