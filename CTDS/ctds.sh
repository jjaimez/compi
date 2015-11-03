#!/bin/bash
# -*- ENCODING: UTF-8 -*-
echo "COMPILANDO PROGRAMA CTDS"
java -cp libs/java-cup-11a.jar:class/ ctds $1 $2 $3 $4 $5
echo "COMPILACION TERMINADA"     
exit