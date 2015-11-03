#!/bin/bash
# -*- ENCODING: UTF-8 -*-
echo "COMPILANDO PROGRAMA CTDS"
java -cp libs/java-cup-11a.jar:class/ ctds $1 $2 $3
if [ -z "$4" ]
  then
  gcc -m32 $2 
else
gcc -m32 $2 $4
fi
echo "COMPILACION TERMINADA"     
exit