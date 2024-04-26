#!/bin/bash

printf "building ..."
(./build.sh > /dev/null 2>&1)
if [ $? -eq 0 ]; then
    printf " done.\n"
else
    printf " failed!\n"
    exit 1
fi
