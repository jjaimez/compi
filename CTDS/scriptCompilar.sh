#!/bin/bash
# -*- ENCODING: UTF-8 -*-
echo "COMPILANDO PROYECTO CTDS"
javac -d class -cp src:libs/java-cup-11a.jar src/ctds.java
echo "COMPILACION TERMINADA, CORRA ./ctds NOMBRE_PROGRAMA.ctds para compilar un programa ctds"     
exit